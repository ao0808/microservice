package ru.soft.deliveryservice.dto;

import lombok.Builder;
import lombok.Data;
import ru.soft.deliveryservice.model.DeliveryStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class DeliveryResponse {

    private Long id;
    private Long orderId;
    private String address;
    private DeliveryStatus status;
    private LocalDateTime createdAt;
}
