package ru.soft.orderservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.soft.orderservice.dto.OrderRequest;
import ru.soft.orderservice.dto.OrderResponse;
import ru.soft.orderservice.integration.payment.dto.OrderPaymentDetails;
import ru.soft.orderservice.integration.payment.dto.OrderPaymentRequest;
import ru.soft.orderservice.service.OrderService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Заказы", description = "CRUD по заказам и создание платежа по заказу")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "Список всех заказов")
    @ApiResponse(responseCode = "200", description = "Список заказов")
    public List<OrderResponse> getAll() {
        log.info("Get all orders");
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить заказ по идентификатору")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Заказ найден"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден",
                    content = @Content(schema = @Schema(implementation = Map.class)))
    })
    public OrderResponse getById(
            @Parameter(description = "Идентификатор заказа", required = true, example = "1")
            @PathVariable Long id) {
        log.info("Get order by id {}", id);
        return orderService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать заказ")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Заказ создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    })
    public OrderResponse create(@Valid @RequestBody OrderRequest request) {
        log.info("Create order {}", request);
        return orderService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить заказ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Заказ обновлён"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    })
    public OrderResponse update(
            @Parameter(description = "Идентификатор заказа", required = true) @PathVariable Long id,
            @Valid @RequestBody OrderRequest request) {
        log.info("Update order {}", request);
        return orderService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить заказ")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Заказ удалён"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден")
    })
    public void delete(
            @Parameter(description = "Идентификатор заказа", required = true) @PathVariable Long id) {
        log.info("Delete order {}", id);
        orderService.delete(id);
    }

    @PostMapping("/{id}/paymant")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать платёж по заказу", description = "Исторический путь `/paymant` (опечатка в URL сохранена).")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Платёж создан"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден")
    })
    public OrderPaymentDetails createPayment(
            @Parameter(description = "Идентификатор заказа", required = true) @PathVariable Long id,
            @RequestBody OrderPaymentRequest orderPaymentRequest) {
        return orderService.createPayment(id, orderPaymentRequest);
    }

}
