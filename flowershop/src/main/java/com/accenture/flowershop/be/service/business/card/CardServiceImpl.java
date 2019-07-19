package com.accenture.flowershop.be.service.business.card;

import com.accenture.flowershop.fe.dto.CustomerCardDTO;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.RoundingMode.UP;

@Service
class CardServiceImpl implements CardService {

    private List<CustomerCardDTO> card = new ArrayList<>();

    public CardServiceImpl(){
    }

    public CustomerCardDTO getCardById(long flowerId){
        for (CustomerCardDTO i: card) {
            if (i.getFlowerDTO().getId() == flowerId) {
                return i;
            }
        }
        return null;
    }

    public void addNewFlowerToCard(FlowerDTO flowerDTO, int number, BigDecimal sumPrice){
        CustomerCardDTO customerCardDTO = new CustomerCardDTO(flowerDTO, number, sumPrice);
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

    public BigDecimal getAllSumPrice(int sale){
        BigDecimal sum = new BigDecimal(0.00);
        if (!card.isEmpty()) {
            for (CustomerCardDTO c : card) {
                sum = sum.add(c.getSumPrice());
            }
            BigDecimal newSale = new BigDecimal(sale).setScale(2, UP);
            newSale = newSale.divide(new BigDecimal(100.00), UP).setScale(2, UP);
            newSale = newSale.multiply(sum).setScale(2, UP);
            sum = sum.subtract(newSale).setScale(2, UP);
        }
        return sum;
    }
}
