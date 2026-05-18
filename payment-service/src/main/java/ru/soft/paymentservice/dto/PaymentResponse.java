package ru.soft.paymentservice.dto;

import lombok.Builder;
import lombok.Data;
import ru.soft.paymentservice.model.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponse {

    private Long id;
    private Long orderId;
    private BigDecimal amount;
    private PaymentStatus status;
    private LocalDateTime createdAt;
}
