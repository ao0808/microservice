package ru.soft.deliveryservice.mapper;

import org.springframework.stereotype.Component;
import ru.soft.deliveryservice.dto.DeliveryRequest;
import ru.soft.deliveryservice.dto.DeliveryResponse;
import ru.soft.deliveryservice.model.Delivery;
import ru.soft.deliveryservice.model.DeliveryStatus;

@Component
public class DeliveryMapper {

    public Delivery toEntity(DeliveryRequest request) {
        return Delivery.builder()
                .orderId(request.getOrderId())
                .address(request.getAddress())
                .status(request.getStatus() != null ? request.getStatus() : DeliveryStatus.PENDING)
                .build();
    }

    public void updateEntity(Delivery delivery, DeliveryRequest request) {
        delivery.setOrderId(request.getOrderId());
        delivery.setAddress(request.getAddress());
        if (request.getStatus() != null) {
            delivery.setStatus(request.getStatus());
        }
    }

    public DeliveryResponse toResponse(Delivery delivery) {
        return DeliveryResponse.builder()
                .id(delivery.getId())
                .orderId(delivery.getOrderId())
                .address(delivery.getAddress())
                .status(delivery.getStatus())
                .createdAt(delivery.getCreatedAt())
                .build();
    }
}
