package com.accenture.flowershop.be.repository.order;

import com.accenture.flowershop.be.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
