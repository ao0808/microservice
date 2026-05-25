package ru.soft.paymentservice.model;

import jakarta.persistence.*;
import lombok.*;
import ru.soft.paymentservice.enums.IdempotencyKeyStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "idempotency_keys")
@NoArgsConstructor
@EqualsAndHashCode(of = "key")
@Data
public class IdempotencyKey {

    @Id
    @Column(name = "key")
    private String key;

    @Enumerated(EnumType.STRING)
    private IdempotencyKeyStatus status;

    @Lob
    private String response;

    private int statusCode;

    private LocalDateTime createdAt;

    public IdempotencyKey(String key, IdempotencyKeyStatus idempotencyKeyStatus) {
        this.key = key;
        this.status = idempotencyKeyStatus;
        this.createdAt = LocalDateTime.now();
    }
}
