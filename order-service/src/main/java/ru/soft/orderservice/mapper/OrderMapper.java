package ru.soft.orderservice.mapper;

import org.springframework.stereotype.Component;
import ru.soft.orderservice.dto.OrderRequest;
import ru.soft.orderservice.dto.OrderResponse;
import ru.soft.orderservice.model.Order;
import ru.soft.orderservice.model.OrderStatus;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequest request) {
        return Order.builder()
                .customerName(request.getCustomerName())
                .productName(request.getProductName())
                .quantity(request.getQuantity())
                .totalAmount(request.getTotalAmount())
                .status(request.getStatus() != null ? request.getStatus() : OrderStatus.CREATED)
                .build();
    }

    public void updateEntity(Order order, OrderRequest request) {
        order.setCustomerName(request.getCustomerName());
        order.setProductName(request.getProductName());
        order.setQuantity(request.getQuantity());
        order.setTotalAmount(request.getTotalAmount());
        if (request.getStatus() != null) {
            order.setStatus(request.getStatus());
        }
    }

    public OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .productName(order.getProductName())
                .quantity(order.getQuantity())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
