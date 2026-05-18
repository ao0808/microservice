package ru.soft.paymentservice.mapper;

import org.springframework.stereotype.Component;
import ru.soft.paymentservice.dto.PaymentRequest;
import ru.soft.paymentservice.dto.PaymentResponse;
import ru.soft.paymentservice.model.Payment;
import ru.soft.paymentservice.model.PaymentStatus;

@Component
public class PaymentMapper {

    public Payment toEntity(PaymentRequest request) {
        return Payment.builder()
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .status(request.getStatus() != null ? request.getStatus() : PaymentStatus.PENDING)
                .build();
    }

    public void updateEntity(Payment payment, PaymentRequest request) {
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        if (request.getStatus() != null) {
            payment.setStatus(request.getStatus());
        }
    }

    public PaymentResponse toResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .createdAt(payment.getCreatedAt())
                .build();
    }
}
