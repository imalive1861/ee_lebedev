package com.accenture.flowershop.fe.dto.mappers;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CartMapper.class})
public interface OrderMapper {

    @Mapping(target = "carts", source = "carts", qualifiedByName = "cartDTOList")
    OrderDTO orderToOrderDto(Order order);
    Order orderDtoToOrder(OrderDTO orderDTO);
    List<OrderDTO> orderToOrderDtos(List<Order> orders);
}
