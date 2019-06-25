package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAccessImpl implements OrderAccess {
    private List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
    }

    public void delete(Order order) {
        orders.remove(order);
    }

    public List<Order> getAll() {
        return orders;
    }

    public Order getById(Integer id) {
        return orders.get(id);
    }
}
