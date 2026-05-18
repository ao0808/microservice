package ru.soft.deliveryservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.soft.deliveryservice.model.DeliveryStatus;

@Data
public class DeliveryRequest {

    @NotNull
    private Long orderId;

    @NotBlank
    private String address;

    private DeliveryStatus status;
}
