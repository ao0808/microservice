package ru.soft.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.soft.paymentservice.dto.PaymentRequest;
import ru.soft.paymentservice.dto.PaymentResponse;
import ru.soft.paymentservice.exception.PaymentNotFoundException;
import ru.soft.paymentservice.mapper.PaymentMapper;
import ru.soft.paymentservice.model.Payment;
import ru.soft.paymentservice.repository.PaymentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Transactional(readOnly = true)
    public List<PaymentResponse> findAll() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public PaymentResponse findById(Long id) {
        return paymentMapper.toResponse(getPayment(id));
    }

    @Transactional(readOnly = true)
    public List<PaymentResponse> findByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId).stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    @Transactional
    public PaymentResponse create(PaymentRequest request) {
        Payment payment = paymentRepository.save(paymentMapper.toEntity(request));
        return paymentMapper.toResponse(payment);
    }

    @Transactional
    public PaymentResponse update(Long id, PaymentRequest request) {
        Payment payment = getPayment(id);
        paymentMapper.updateEntity(payment, request);
        return paymentMapper.toResponse(paymentRepository.save(payment));
    }

    @Transactional
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

    private Payment getPayment(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));
    }
}
