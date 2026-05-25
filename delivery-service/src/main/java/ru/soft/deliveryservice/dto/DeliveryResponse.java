package ru.soft.deliveryservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.soft.deliveryservice.model.DeliveryStatus;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Доставка в ответе API")
public class DeliveryResponse {

    @Schema(description = "Идентификатор заявки на доставку", example = "1")
    private Long id;

    @Schema(description = "Идентификатор заказа", example = "1")
    private Long orderId;

    @Schema(description = "Адрес доставки")
    private String address;

    @Schema(description = "Статус доставки", example = "IN_TRANSIT")
    private DeliveryStatus status;

    @Schema(description = "Время создания")
    private LocalDateTime createdAt;
}
