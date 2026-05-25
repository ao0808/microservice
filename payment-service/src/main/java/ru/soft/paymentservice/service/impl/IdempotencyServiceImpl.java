package ru.soft.paymentservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.soft.paymentservice.enums.IdempotencyKeyStatus;
import ru.soft.paymentservice.exception.IdempotencyKeyExistsException;
import ru.soft.paymentservice.model.IdempotencyKey;
import ru.soft.paymentservice.repository.IdempotencyRepository;
import ru.soft.paymentservice.service.IdempotencyService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IdempotencyServiceImpl implements IdempotencyService {

    private final IdempotencyRepository idempotencyRepository;
    @Override
    public Optional<IdempotencyKey> getByKey(String key) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public void createPendingKey(String key) {
        try {
            idempotencyRepository.save(new IdempotencyKey(key, IdempotencyKeyStatus.PENDING));
        } catch (DataIntegrityViolationException e) {
            throw new IdempotencyKeyExistsException("Key already exists", e);
        }
    }

    @Override
    @Transactional
    public void markAsCompleted(String key, String response, int statusCode) {
        IdempotencyKey idempotencyKey = idempotencyRepository.findById(key)
                .orElseThrow(() -> new EntityNotFoundException("Idempotency key not found: " + key));
        idempotencyKey.setStatus(IdempotencyKeyStatus.COMPLETED);
        idempotencyKey.setResponse(response);
        idempotencyKey.setStatusCode(statusCode);
        idempotencyRepository.save(idempotencyKey);
    }
}
