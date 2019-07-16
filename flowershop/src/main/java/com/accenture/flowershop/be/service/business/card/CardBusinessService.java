package com.accenture.flowershop.be.service.business.card;

import com.accenture.flowershop.fe.dto.CardDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CardBusinessService {
    void addNewFlowerToCard(long flowerId, String flowerName, int number, BigDecimal sumPrice);
    void editCard(long flowerId, int number, BigDecimal sumPrice);
    void clear();
    List<CardDTO> getCard();
    CardDTO getCardById(long flowerId);
    BigDecimal getAllSumPrice();

}
