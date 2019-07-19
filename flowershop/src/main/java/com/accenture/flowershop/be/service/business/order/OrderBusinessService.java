package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public interface OrderBusinessService {
    OrderDTO openOrder();

    void paidOrder(OrderDTO orderDTO, BigDecimal sumPrice);

    void closeOrder(OrderDTO orderDTO);

    Map<Long, OrderDTO> getAll();

    OrderDTO get(long id);

    Order getDAO(long id);
}
