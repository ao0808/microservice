package ru.soft.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.soft.paymentservice.model.IdempotencyKey;

public class IdempotencyRepository implements JpaRepository<IdempotencyKey, String> {
}
