package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface OrderBusinessService {
    Order create(User user, BigDecimal sumPrice);

    void close(Long orderId);

    List<Order> getAll();

    Order get(long id);
}
