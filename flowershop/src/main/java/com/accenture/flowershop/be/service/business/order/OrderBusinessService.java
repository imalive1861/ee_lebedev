package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.fe.dto.OrderDTO;

import java.util.List;

public interface OrderBusinessService {
    void saveOrder(OrderDTO orderDTO);

    void closeOrder(OrderDTO orderDTO);

    List<Order> getAll();

    Order get(long id);
}
