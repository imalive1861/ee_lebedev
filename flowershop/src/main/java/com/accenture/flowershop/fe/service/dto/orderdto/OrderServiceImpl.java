package com.accenture.flowershop.fe.service.dto.orderdto;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.dto.OrderDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private Mapper mapper;

    @Override
    public OrderDTO toDto(Order order) {
        return mapper.map(order,OrderDTO.class);
    }

    @Override
    public Order fromDto(OrderDTO orderDTO) {
        return mapper.map(orderDTO,Order.class);
    }

    @Override
    public List<OrderDTO> toDtoList(List<Order> orders) {
        return orders.stream().map(this::toDto).collect(Collectors.toList());
    }
}
