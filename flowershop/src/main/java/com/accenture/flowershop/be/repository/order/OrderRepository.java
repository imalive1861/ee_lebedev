package com.accenture.flowershop.be.repository.order;

import com.accenture.flowershop.be.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Интерфейс доступа к базе данных для сущности Order.
 */
public interface OrderRepository extends MongoRepository<Order, Long> {
}
