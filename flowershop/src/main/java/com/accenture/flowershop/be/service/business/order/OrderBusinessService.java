package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderBusinessService {
    void saveOrder(OrderDTO orderDTO);

    void closeOrder(OrderDTO orderDTO);

    List<Order> getAll();

    Order get(long id);
}
