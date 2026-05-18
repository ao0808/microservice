package ru.soft.paymentservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.soft.paymentservice.model.PaymentStatus;

import java.math.BigDecimal;

@Data
public class PaymentRequest {

    @NotNull
    private Long orderId;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

    private PaymentStatus status;
}
