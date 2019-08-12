package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;

import java.math.BigDecimal;
import java.util.List;

public interface OrderBusinessService {
    OrderDTO create(UserDTO userDTO, BigDecimal sumPrice);

    void close(OrderDTO orderDTO);

    List<OrderDTO> getAll();

    OrderDTO get(long id);
}
