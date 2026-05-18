package ru.soft.orderservice.dto;

import lombok.Builder;
import lombok.Data;
import ru.soft.orderservice.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponse {

    private Long id;
    private String customerName;
    private String productName;
    private Integer quantity;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;
}
