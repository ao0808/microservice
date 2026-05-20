package ru.soft.orderservice.integration.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Запрос на создание платежа для существующего заказа")
public class OrderPaymentRequest {
    Long sum;
    //TODO по идее можно сделать в дальнейшем отлельные справочники и сделать связь
    String method;
    String status;
    String amount;
}
