package ru.soft.paymentservice.controller;

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
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public List<PaymentResponse> getAll() {
        log.info("Get all payments");
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public PaymentResponse getById(@PathVariable Long id) {
        log.info("Get payment by id: {}", id);
        return paymentService.findById(id);
    }

    @GetMapping("/order/{orderId}")
    public List<PaymentResponse> getByOrderId(@PathVariable Long orderId) {
        log.info("Get payment by order id: {}", orderId);
        return paymentService.findByOrderId(orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse create(@Valid @RequestBody PaymentRequest request) {
        log.info("Create payment: {}", request);
        return paymentService.create(request);
    }

    @PutMapping("/{id}")
    public PaymentResponse update(@PathVariable Long id, @Valid @RequestBody PaymentRequest request) {
        log.info("Update payment: {}", request);
        return paymentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Delete payment: {}", id);
        paymentService.delete(id);
    }
}
