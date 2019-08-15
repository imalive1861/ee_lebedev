package com.accenture.flowershop.fe.servicedto.cartdto;

import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;

import java.math.BigDecimal;

public interface CartService {
    boolean isAddFlowerToCart(String login, FlowerDTO flowerDTO, int number);
    void clear(String login);
    OrderDTO setCartFromSession(String login);
    BigDecimal getAllSumPrice(int sale, String login);

}
