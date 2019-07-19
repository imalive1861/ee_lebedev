package com.accenture.flowershop.be.service.business.card;

import com.accenture.flowershop.fe.dto.CardDTO;
import com.accenture.flowershop.fe.dto.CustomerCardDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public interface CardBusinessService {
    boolean saveCardToOrder(OrderDTO orderDTO, BigDecimal sumPrice, List<CustomerCardDTO> customerCardDTOs, UserDTO userDTO);
    Map<Long, CardDTO> getAll();
}
