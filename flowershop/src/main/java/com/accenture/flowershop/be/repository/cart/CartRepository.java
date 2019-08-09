package com.accenture.flowershop.be.repository.cart;

import com.accenture.flowershop.be.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
