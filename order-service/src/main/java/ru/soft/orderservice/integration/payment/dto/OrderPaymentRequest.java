package ru.soft.orderservice.integration.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Запрос на создание платежа для существующего заказа (поля передаются в платёжный сервис)")
public class OrderPaymentRequest {

    @Schema(description = "Сумма (числовое значение)", example = "1500")
    private Long sum;

    @Schema(description = "Способ оплаты", example = "CARD")
    private String method;

    @Schema(description = "Статус (строка, как ожидает интеграция)", example = "PENDING")
    private String status;

    @Schema(description = "Сумма в строковом виде", example = "1500.00")
    private String amount;
}
