package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;

import java.util.List;

public interface OrderAccess {
    void save(Order order);

    void delete(Order order);

    List<Order> getAll();

    Order getById(Integer id);
}
