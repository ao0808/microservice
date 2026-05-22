package ru.soft.paymentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.soft.paymentservice.model.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Платёж в ответе API")
public class PaymentResponse {

    @Schema(description = "Идентификатор платежа", example = "1")
    private Long id;

    @Schema(description = "Идентификатор заказа", example = "1")
    private Long orderId;

    @Schema(description = "Сумма", example = "89990.00")
    private BigDecimal amount;

    @Schema(description = "Статус платежа", example = "PAID")
    private PaymentStatus status;

    @Schema(description = "Время создания")
    private LocalDateTime createdAt;
}
