package ru.soft.deliveryservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.soft.deliveryservice.model.DeliveryStatus;

@Data
@Schema(description = "Тело запроса на создание или обновление доставки")
public class DeliveryRequest {

    @NotNull
    @Schema(description = "Идентификатор заказа", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long orderId;

    @NotBlank
    @Schema(description = "Адрес доставки", example = "Москва, ул. Примерная, д. 1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String address;

    @Schema(description = "Статус; по умолчанию PENDING", example = "PENDING")
    private DeliveryStatus status;
}
