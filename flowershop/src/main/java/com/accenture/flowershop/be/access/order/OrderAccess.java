package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderAccess {
    void saveOrder(double sumPrice);

    void closeOrder(int id);

    void delete(Order order);

    List<Order> getAll();

    Order getById(Integer id);
}
