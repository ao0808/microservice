package ru.soft.paymentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import ru.soft.paymentservice.model.PaymentStatus;

import java.math.BigDecimal;

@Data
@Schema(description = "Тело запроса на создание или обновление платежа")
public class PaymentRequest {

    @NotNull
    @Schema(description = "Идентификатор заказа", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long orderId;

    @NotNull
    @DecimalMin(value = "0.01")
    @Schema(description = "Сумма платежа", example = "89990.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal amount;

    @Schema(description = "Статус; по умолчанию PENDING", example = "PENDING")
    private PaymentStatus status;
}
