package com.accenture.flowershop.be.service.business.card;

import com.accenture.flowershop.fe.dto.CustomerCardDTO;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface CardService {
    void addNewFlowerToCard(FlowerDTO flowerDTO, int number, BigDecimal sumPrice);
    void editCard(long flowerId, int number, BigDecimal sumPrice);
    void clear();
    List<CustomerCardDTO> getCard();
    CustomerCardDTO getCardById(long flowerId);
    BigDecimal getAllSumPrice();

}
