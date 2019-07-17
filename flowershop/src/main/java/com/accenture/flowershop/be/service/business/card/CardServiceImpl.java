package com.accenture.flowershop.be.service.business.card;

import com.accenture.flowershop.fe.dto.CustomerCardDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
class CardServiceImpl implements CardService {

    private List<CustomerCardDTO> card = new ArrayList<>();

    public CardServiceImpl(){
    }

    public CustomerCardDTO getCardById(long flowerId){
        for (CustomerCardDTO i: card) {
            if (i.getFlowerId() == flowerId) {
                return i;
            }
        }
        return null;
    }

    public void addNewFlowerToCard(long flowerId, String flowerName, int number, BigDecimal sumPrice){
        CustomerCardDTO customerCardDTO = new CustomerCardDTO(flowerId, flowerName, number, sumPrice);
        this.card.add(customerCardDTO);
    }

    public void editCard(long flowerId, int number, BigDecimal sumPrice){
        CustomerCardDTO i = getCardById(flowerId);
        if (i != null) {
            i.setNumber(i.getNumber() + number);
            i.setSumPrice(i.getSumPrice().add(sumPrice));
        }
    }

    public void clear(){
        this.card.clear();
    }

    public List<CustomerCardDTO> getCard() {
        return card;
    }

    public BigDecimal getAllSumPrice(){
        BigDecimal sum = new BigDecimal(0.00);
        if (!card.isEmpty()) {
            for (CustomerCardDTO c : card) {
                sum = sum.add(c.getSumPrice());
            }
        }
        return sum;
    }
}
