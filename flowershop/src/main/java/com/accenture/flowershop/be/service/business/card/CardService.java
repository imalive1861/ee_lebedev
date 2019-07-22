package com.accenture.flowershop.be.service.business.card;

import com.accenture.flowershop.fe.dto.CustomerCardDTO;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface CardService {
    void addNewFlowerToCard(String login, FlowerDTO flowerDTO, int number, BigDecimal sumPrice);
    void editCard(String login, long flowerId, int number, BigDecimal sumPrice);
    void clear(String login);
    List<CustomerCardDTO> setCardFromSession(String login);
    CustomerCardDTO getCardById(String login, long flowerId);
    List<CustomerCardDTO> getCard(String login);
    BigDecimal getAllSumPrice(int sale, String login);

}
