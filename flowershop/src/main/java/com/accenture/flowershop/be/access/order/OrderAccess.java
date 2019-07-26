package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderAccess {
    void saveOrder(Order order);

    void delete(long id);

    Order get(long id);

    Order getOrderByStatusAndUser(String status, User user);

    void update(Order order);

    List<Order> getAll();
}
