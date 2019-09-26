package com.accenture.flowershop.fe.service.dto.orderdto;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.dto.OrderDTO;

import java.util.List;

/**
 * Утилитарный класс транспортного уровня для сущности OrderDTO.
 */
public interface OrderDtoService {
    /**
     * Переводит сущность Order в OrderDTO.
     *
     * @param order - объект Order
     * @return объект OrderDTO
     */
    OrderDTO toDto(Order order);

    /**
     * Переводит OrderDTO в сущность Order.
     *
     * @param orderDTO - объект OrderDTO
     * @return объект Order
     */
    Order fromDto(OrderDTO orderDTO);

    /**
     * Переводит список Order в список OrderDTO.
     *
     * @param orders - список Order
     * @return список OrderDTO
     */
    List<OrderDTO> toDtoList(List<Order> orders);
}