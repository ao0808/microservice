package ru.soft.deliveryservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.soft.deliveryservice.dto.DeliveryRequest;
import ru.soft.deliveryservice.dto.DeliveryResponse;
import ru.soft.deliveryservice.service.DeliveryService;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
@Tag(name = "Доставки", description = "CRUD по заявкам на доставку и выборка по заказу")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    @Operation(summary = "Список всех доставок")
    @ApiResponse(responseCode = "200", description = "Список доставок")
    public List<DeliveryResponse> getAll() {
        return deliveryService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить доставку по идентификатору")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Доставка найдена"),
            @ApiResponse(responseCode = "404", description = "Доставка не найдена")
    })
    public DeliveryResponse getById(
            @Parameter(description = "Идентификатор доставки", required = true, example = "1") @PathVariable Long id) {
        return deliveryService.findById(id);
    }

    @GetMapping("/order/{orderId}")
    @Operation(summary = "Доставки по идентификатору заказа")
    @ApiResponse(responseCode = "200", description = "Список доставок по заказу (может быть пустым)")
    public List<DeliveryResponse> getByOrderId(
            @Parameter(description = "Идентификатор заказа", required = true, example = "1") @PathVariable Long orderId) {
        return deliveryService.findByOrderId(orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать заявку на доставку")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Доставка создана"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    })
    public DeliveryResponse create(@Valid @RequestBody DeliveryRequest request) {
        return deliveryService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить доставку")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Доставка обновлена"),
            @ApiResponse(responseCode = "404", description = "Доставка не найдена"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    })
    public DeliveryResponse update(
            @Parameter(description = "Идентификатор доставки", required = true) @PathVariable Long id,
            @Valid @RequestBody DeliveryRequest request) {
        return deliveryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить доставку")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Доставка удалена"),
            @ApiResponse(responseCode = "404", description = "Доставка не найдена")
    })
    public void delete(
            @Parameter(description = "Идентификатор доставки", required = true) @PathVariable Long id) {
        deliveryService.delete(id);
    }
}
