package ru.soft.deliveryservice.controller;

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
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    public List<DeliveryResponse> getAll() {
        return deliveryService.findAll();
    }

    @GetMapping("/{id}")
    public DeliveryResponse getById(@PathVariable Long id) {
        return deliveryService.findById(id);
    }

    @GetMapping("/order/{orderId}")
    public List<DeliveryResponse> getByOrderId(@PathVariable Long orderId) {
        return deliveryService.findByOrderId(orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryResponse create(@Valid @RequestBody DeliveryRequest request) {
        return deliveryService.create(request);
    }

    @PutMapping("/{id}")
    public DeliveryResponse update(@PathVariable Long id, @Valid @RequestBody DeliveryRequest request) {
        return deliveryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deliveryService.delete(id);
    }
}
