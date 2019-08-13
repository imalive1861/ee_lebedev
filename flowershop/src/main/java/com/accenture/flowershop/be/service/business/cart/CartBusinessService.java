package com.accenture.flowershop.be.service.business.cart;

import com.accenture.flowershop.be.entity.Cart;
import com.accenture.flowershop.be.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface CartBusinessService {
    boolean save(BigDecimal sumPrice,
                 List<Cart> carts, User user);
    List<Cart> getAll();
}
