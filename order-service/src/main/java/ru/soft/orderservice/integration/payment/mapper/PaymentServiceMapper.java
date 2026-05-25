package ru.soft.orderservice.integration.payment.mapper;

import org.springframework.stereotype.Component;
import ru.soft.orderservice.integration.payment.dto.OrderPaymentDetails;
import ru.soft.orderservice.integration.payment.dto.OrderPaymentRequest;
import ru.soft.orderservice.integration.payment.dto.PaymentCreateRequest;
import ru.soft.orderservice.integration.payment.dto.PaymentCreateResponse;
import ru.soft.orderservice.model.Order;

@Component
public class PaymentServiceMapper {

    public PaymentCreateRequest toPaymentCreateRequest(Order order, OrderPaymentRequest request){
        return new PaymentCreateRequest(order.getId(), request.getStatus(), request.getMethod(), request.getAmount());
    }

    public OrderPaymentDetails toOrderPaymentDetails(PaymentCreateResponse payment) {
        return new OrderPaymentDetails(payment.id(), payment.orderId(), payment.status(), payment.method(),
                payment.amount(), payment.createdAt());
    }
}
