package ru.soft.orderservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.soft.orderservice.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Заказ в ответе API")
public class OrderResponse {

    @Schema(description = "Идентификатор заказа", example = "1")
    private Long id;

    @Schema(description = "Имя клиента", example = "Иван Иванов")
    private String customerName;

    @Schema(description = "Наименование товара", example = "Ноутбук")
    private String productName;

    @Schema(description = "Количество", example = "1")
    private Integer quantity;

    @Schema(description = "Сумма заказа", example = "89990.00")
    private BigDecimal totalAmount;

    @Schema(description = "Статус заказа", example = "CREATED")
    private OrderStatus status;

    @Schema(description = "Дата и время создания")
    private LocalDateTime createdAt;
}
