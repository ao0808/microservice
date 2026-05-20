package ru.soft.orderservice.integration.payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.soft.orderservice.integration.payment.dto.*;


@FeignClient(name = "payment-service-client", url = "${clients.payment-service.url}")
public interface PaymentServiceClient {

    @PostMapping("/api/payments")
    PaymentCreateResponse createPayment(
            @RequestHeader() String id,
            @RequestBody PaymentCreateRequest paymentCreateRequest
    );
}
