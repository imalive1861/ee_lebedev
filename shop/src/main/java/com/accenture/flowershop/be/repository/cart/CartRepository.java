package com.accenture.flowershop.be.repository.cart;

import com.accenture.flowershop.be.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс доступа к базе данных для сущности Cart.
 */
public interface CartRepository extends JpaRepository<Cart, Long> {
}
