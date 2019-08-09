package com.accenture.flowershop.be.service.business.cart;

import com.accenture.flowershop.fe.dto.CustomerCartDTO;
import com.accenture.flowershop.fe.dto.FlowerDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    void addNewFlowerToCart(String login, FlowerDTO flowerDTO, int number, BigDecimal sumPrice);
    void editCart(String login, long flowerId, int number, BigDecimal sumPrice);
    void clear(String login);
    List<CustomerCartDTO> setCartFromSession(String login);
    CustomerCartDTO getCartById(String login, long flowerId);
    List<CustomerCartDTO> getCart(String login);
    BigDecimal getAllSumPrice(int sale, String login);

}
