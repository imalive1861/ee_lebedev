package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.entity.order.Order;

import java.util.List;

public interface OrderBusinessService {
    void saveNewOrder(Order order);

    List<Order> getAll();

    Order getById(Integer id);

    void closeOrder(Order order);

}
