package com.accenture.flowershop.be.service.business.cart;

import com.accenture.flowershop.be.entity.Cart;

import java.util.List;

public interface CartBusinessService {
    List<Cart> getAll();
    Cart get(long id);
}
