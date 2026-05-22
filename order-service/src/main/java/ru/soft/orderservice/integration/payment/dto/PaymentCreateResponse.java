package ru.soft.orderservice.integration.payment.dto;


import java.time.LocalDateTime;

public record PaymentCreateResponse(
        Long id,
        Long orderId,
        String status,
        String method,
        String amount,
        LocalDateTime createdAt
) {
}
