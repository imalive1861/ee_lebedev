package com.accenture.flowershop.be.service.business.card;

import com.accenture.flowershop.fe.dto.CardDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
class CardBusinessServiceImpl implements CardBusinessService {

    private List<CardDTO> card = new ArrayList<>();

    public CardBusinessServiceImpl(){
    }

    public CardDTO getCardById(long flowerId){
        for (CardDTO i: card) {
            if (i.getFlowerId() == flowerId) {
                return i;
            }
        }
        return null;
    }

    public void addNewFlowerToCard(long flowerId, String flowerName, int number, BigDecimal sumPrice){
        CardDTO cardDTO = new CardDTO(flowerId, flowerName, number, sumPrice);
        this.card.add(cardDTO);
    }

    public void editCard(long flowerId, int number, BigDecimal sumPrice){
        CardDTO i = getCardById(flowerId);
        if (i != null) {
            i.setNumber(i.getNumber() + number);
            i.setSumPrice(i.getSumPrice().add(sumPrice));
        }
    }

    public void clear(){
        this.card.clear();
    }

    public List<CardDTO> getCard() {
        return card;
    }

    public BigDecimal getAllSumPrice(){
        BigDecimal sum = new BigDecimal(0.00);
        if (!card.isEmpty()) {
            for (CardDTO c : card) {
                sum = sum.add(c.getSumPrice());
            }
        }
        return sum;
    }
}
