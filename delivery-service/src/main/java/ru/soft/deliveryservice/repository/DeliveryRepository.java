package ru.soft.deliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.soft.deliveryservice.model.Delivery;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findByOrderId(Long orderId);
}
