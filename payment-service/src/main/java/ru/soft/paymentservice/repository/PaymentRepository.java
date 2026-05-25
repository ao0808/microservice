package ru.soft.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.soft.paymentservice.model.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByOrderId(Long orderId);
}
