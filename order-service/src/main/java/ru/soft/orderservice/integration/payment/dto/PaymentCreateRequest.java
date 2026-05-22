package ru.soft.orderservice.integration.payment.dto;


public record PaymentCreateRequest(
        Long orderId,
        String status,
        String method,
        String amount
) {
}
