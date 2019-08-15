package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.entity.Order;

import java.util.List;

public interface OrderBusinessService {
    void save(Order order);
    void close(Long orderId);
    List<Order> getAll();
    Order get(long id);
}
