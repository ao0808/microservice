package ru.soft.orderservice.integration.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Сводка по созданному платежу для заказа")
public record OrderPaymentDetails(
        @Schema(description = "Идентификатор платежа") Long id,
        @Schema(description = "Идентификатор заказа") Long orderId,
        @Schema(description = "Статус платежа") String status,
        @Schema(description = "Способ оплаты") String method,
        @Schema(description = "Сумма") String amount,
        @Schema(description = "Время создания") LocalDateTime createdAt
) {
}