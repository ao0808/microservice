package ru.soft.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.soft.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
