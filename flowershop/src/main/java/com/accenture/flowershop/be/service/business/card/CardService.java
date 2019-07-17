package com.accenture.flowershop.be.service.business.card;

import com.accenture.flowershop.fe.dto.CustomerCardDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CardService {
    void addNewFlowerToCard(long flowerId, String flowerName, int number, BigDecimal sumPrice);
    void editCard(long flowerId, int number, BigDecimal sumPrice);
    void clear();
    List<CustomerCardDTO> getCard();
    CustomerCardDTO getCardById(long flowerId);
    BigDecimal getAllSumPrice();

}
