package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderAccess {
    void saveOrder(Order order);

    void delete(long id);

    Order get(long id);

    Order getOrderByStatus(String status);

    void update(Order order);

    List<Order> getAll();
}
