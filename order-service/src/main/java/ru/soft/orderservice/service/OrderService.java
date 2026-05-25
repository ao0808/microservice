package ru.soft.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.soft.orderservice.dto.OrderRequest;
import ru.soft.orderservice.dto.OrderResponse;
import ru.soft.orderservice.exception.OrderNotFoundException;
import ru.soft.orderservice.exception.ResourceNotFoundException;
import ru.soft.orderservice.integration.payment.PaymentClientAdapter;
import ru.soft.orderservice.integration.payment.dto.OrderPaymentDetails;
import ru.soft.orderservice.integration.payment.dto.OrderPaymentRequest;
import ru.soft.orderservice.integration.payment.mapper.PaymentServiceMapper;
import ru.soft.orderservice.mapper.OrderMapper;
import ru.soft.orderservice.model.Order;
import ru.soft.orderservice.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final PaymentClientAdapter paymentClientAdapter;
    private final PaymentServiceMapper paymentServiceMapper;

    @Transactional(readOnly = true)
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrderResponse findById(Long id) {
        return orderMapper.toResponse(getOrder(id));
    }

    @Transactional
    public OrderResponse create(OrderRequest request) {
        Order order = orderRepository.save(orderMapper.toEntity(request));
        return orderMapper.toResponse(order);
    }

    @Transactional
    public OrderResponse update(Long id, OrderRequest request) {
        Order order = getOrder(id);
        orderMapper.updateEntity(order, request);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Transactional
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public OrderPaymentDetails createPayment(Long id, OrderPaymentRequest orderPaymentRequest) {
        if (id == null || orderPaymentRequest == null) {
            throw new IllegalArgumentException("Order Payment Request is null");
        }

        Order order = getOrder(id);

        return paymentServiceMapper.toOrderPaymentDetails(paymentClientAdapter.createPayment(
                paymentServiceMapper.toPaymentCreateRequest(order, orderPaymentRequest)));
    }

    private Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id " + orderId + " was not found"));
    }
}
