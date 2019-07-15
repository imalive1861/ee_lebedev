package com.accenture.flowershop.be.service.business.card;

import com.accenture.flowershop.fe.dto.CardDTO;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
class CardBusinessServiceImpl implements CardBusinessService {

    private List<CardDTO> card = new ArrayList<>();

    public CardBusinessServiceImpl(){
    }

    public void addFlowerToCard(long flowerId, String flowerName, int number, BigDecimal sumPrice){
        CardDTO cardDTO = new CardDTO(flowerId, flowerName, number, sumPrice);
        this.card.add(cardDTO);

    }

    public void clear(){
        this.card.clear();
    }

    public List<CardDTO> getCard() {
        return card;
    }
}
