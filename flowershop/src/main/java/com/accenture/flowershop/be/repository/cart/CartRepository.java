package com.accenture.flowershop.be.repository.cart;

import com.accenture.flowershop.be.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Интерфейс доступа к базе данных для сущности Cart.
 */
public interface CartRepository extends MongoRepository<Cart, Long> {
}
