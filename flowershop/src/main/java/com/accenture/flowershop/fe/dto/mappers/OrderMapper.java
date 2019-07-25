package com.accenture.flowershop.fe.dto.mappers;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.dto.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO orderToOrderDto(Order order);
    Order orderDtoToOrder(OrderDTO orderDTO);
}
