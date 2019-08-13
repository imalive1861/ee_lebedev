package com.accenture.flowershop.fe.servicedto.customercartdto;

import com.accenture.flowershop.fe.dto.CustomerCartDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    boolean isAddFlowerToCart(String login, long flower, int number);
    void clear(String login);
    List<CustomerCartDTO> setCartFromSession(String login);
    CustomerCartDTO getCartById(String login, long flowerId);
    List<CustomerCartDTO> getCartByUserLogin(String login);
    BigDecimal getAllSumPrice(int sale, String login);

}
