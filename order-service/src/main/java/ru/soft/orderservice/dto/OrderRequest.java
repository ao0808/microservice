package ru.soft.orderservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.soft.orderservice.model.OrderStatus;

import java.math.BigDecimal;

@Data
public class OrderRequest {

    @NotBlank
    private String customerName;

    @NotBlank
    private String productName;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal totalAmount;

    private OrderStatus status;
}
