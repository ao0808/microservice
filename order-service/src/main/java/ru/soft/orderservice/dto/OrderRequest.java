package ru.soft.orderservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import ru.soft.orderservice.model.OrderStatus;

import java.math.BigDecimal;

@Data
@Schema(description = "Тело запроса на создание или обновление заказа")
public class OrderRequest {

    @NotBlank
    @Schema(description = "Имя клиента", example = "Иван Иванов", requiredMode = Schema.RequiredMode.REQUIRED)
    private String customerName;

    @NotBlank
    @Schema(description = "Наименование товара", example = "Ноутбук", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productName;

    @NotNull
    @Min(1)
    @Schema(description = "Количество", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantity;

    @NotNull
    @DecimalMin(value = "0.01")
    @Schema(description = "Сумма заказа", example = "89990.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal totalAmount;

    @Schema(description = "Статус заказа; по умолчанию CREATED", example = "CREATED")
    private OrderStatus status;
}
