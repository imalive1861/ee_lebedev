package com.accenture.flowershop.be.service.business.cart;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.dto.CartDTO;
import com.accenture.flowershop.fe.dto.CustomerCartDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CartBusinessService {
    boolean save(BigDecimal sumPrice,
                 List<CustomerCartDTO> customerCartDTOS, User user);
    List<CartDTO> getAll();
}
