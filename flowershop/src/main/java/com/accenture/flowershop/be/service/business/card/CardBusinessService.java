package com.accenture.flowershop.be.service.business.card;

import com.accenture.flowershop.fe.dto.CardDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CardBusinessService {
    void addFlowerToCard(long flowerId, String flowerName, int number, BigDecimal sumPrice);
    void clear();
    List<CardDTO> getCard();
}
