package com.accenture.flowershop.be.repository.order;

import com.accenture.flowershop.be.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс доступа к базе данных для сущности Order.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
