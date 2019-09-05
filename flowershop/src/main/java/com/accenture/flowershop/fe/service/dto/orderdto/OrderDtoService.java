package com.accenture.flowershop.fe.service.dto.orderdto;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.dto.OrderDTO;

import java.util.List;

public interface OrderDtoService {
    OrderDTO toDto(Order order);

    Order fromDto(OrderDTO orderDTO);

    List<OrderDTO> toDtoList(List<Order> orders);
}