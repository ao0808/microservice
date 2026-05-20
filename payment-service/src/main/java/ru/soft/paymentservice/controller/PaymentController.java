package ru.soft.paymentservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.soft.paymentservice.dto.PaymentRequest;
import ru.soft.paymentservice.dto.PaymentResponse;
import ru.soft.paymentservice.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Платежи", description = "CRUD по платежам и выборка по заказу")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    @Operation(summary = "Список всех платежей")
    @ApiResponse(responseCode = "200", description = "Список платежей")
    public List<PaymentResponse> getAll() {
        log.info("Get all payments");
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить платёж по идентификатору")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Платёж найден"),
            @ApiResponse(responseCode = "404", description = "Платёж не найден")
    })
    public PaymentResponse getById(
            @Parameter(description = "Идентификатор платежа", required = true, example = "1") @PathVariable Long id) {
        log.info("Get payment by id: {}", id);
        return paymentService.findById(id);
    }

    @GetMapping("/order/{orderId}")
    @Operation(summary = "Платежи по идентификатору заказа")
    @ApiResponse(responseCode = "200", description = "Список платежей по заказу (может быть пустым)")
    public List<PaymentResponse> getByOrderId(
            @Parameter(description = "Идентификатор заказа", required = true, example = "1") @PathVariable Long orderId) {
        log.info("Get payment by order id: {}", orderId);
        return paymentService.findByOrderId(orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать платёж")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Платёж создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    })
    public PaymentResponse create(@Valid @RequestBody PaymentRequest request) {
        log.info("Create payment: {}", request);
        return paymentService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить платёж")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Платёж обновлён"),
            @ApiResponse(responseCode = "404", description = "Платёж не найден"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    })
    public PaymentResponse update(
            @Parameter(description = "Идентификатор платежа", required = true) @PathVariable Long id,
            @Valid @RequestBody PaymentRequest request) {
        log.info("Update payment: {}", request);
        return paymentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить платёж")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Платёж удалён"),
            @ApiResponse(responseCode = "404", description = "Платёж не найден")
    })
    public void delete(
            @Parameter(description = "Идентификатор платежа", required = true) @PathVariable Long id) {
        log.info("Delete payment: {}", id);
        paymentService.delete(id);
    }
}
