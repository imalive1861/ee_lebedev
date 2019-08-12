package com.accenture.flowershop.be.service.business.cart;

import com.accenture.flowershop.fe.dto.CartDTO;
import com.accenture.flowershop.fe.dto.CustomerCartDTO;
import com.accenture.flowershop.fe.dto.UserDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CartBusinessService {
    boolean save(BigDecimal sumPrice,
                 List<CustomerCartDTO> customerCartDTOS, UserDTO userDTO);
    List<CartDTO> getAll();
}
