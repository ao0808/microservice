package ru.soft.orderservice.integration.payment;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.soft.orderservice.integration.payment.dto.PaymentCreateRequest;
import ru.soft.orderservice.integration.payment.dto.PaymentCreateResponse;
import io.github.resilience4j.retry.annotation.Retry;

@Slf4j
@RequiredArgsConstructor
@Component
public class PaymentClientAdapter {
    private final PaymentServiceClient paymentServiceClient;

    @Retry(name = "paymentServiceRetry")
    @CircuitBreaker(name = "paymentServiceCircuitBreaker")
    public PaymentCreateResponse createPayment(PaymentCreateRequest orderPaymentRequest) {
        String idOrder = orderPaymentRequest.orderId().toString();
        log.info("Calling payment-service, order-id={}", idOrder);
        return paymentServiceClient.createPayment(idOrder, orderPaymentRequest);
    }
}
