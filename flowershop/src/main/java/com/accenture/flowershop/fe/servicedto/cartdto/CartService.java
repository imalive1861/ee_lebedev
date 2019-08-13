package com.accenture.flowershop.fe.servicedto.cartdto;

import com.accenture.flowershop.fe.dto.CartDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    boolean isAddFlowerToCart(String login, long flower, int number);
    void clear(String login);
    List<CartDTO> setCartFromSession(String login);
    CartDTO getCartById(String login, long flowerId);
    List<CartDTO> getCartByUserLogin(String login);
    BigDecimal getAllSumPrice(int sale, String login);

}
