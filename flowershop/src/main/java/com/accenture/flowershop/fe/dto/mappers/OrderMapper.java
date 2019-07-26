package com.accenture.flowershop.fe.dto.mappers;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.dto.OrderDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface OrderMapper {
    OrderDTO orderToOrderDto(Order order);
    Order orderDtoToOrder(OrderDTO orderDTO);
    List<OrderDTO> orderToOrderDtos(List<Order> orders);
}
