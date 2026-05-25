package ru.soft.paymentservice.service;

import ru.soft.paymentservice.model.IdempotencyKey;

import java.util.Optional;

public interface IdempotencyService {
    Optional<IdempotencyKey> getByKey(String key);

    void createPendingKey(String key);

    void markAsCompleted(String idempotencyKey, String responseBody, int status);
}
