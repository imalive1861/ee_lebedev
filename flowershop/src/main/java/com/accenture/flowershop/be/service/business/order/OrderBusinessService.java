package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.dto.OrderDTO;

import java.math.BigDecimal;
import java.util.List;

public interface OrderBusinessService {
    OrderDTO create(User user, BigDecimal sumPrice);

    void close(OrderDTO orderDTO);

    List<OrderDTO> getAll();

    OrderDTO get(long id);
}
