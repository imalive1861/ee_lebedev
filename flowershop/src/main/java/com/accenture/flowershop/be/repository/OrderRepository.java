package com.accenture.flowershop.be.repository;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order getOrderByStatusAndUserId(String status, User user);
}
