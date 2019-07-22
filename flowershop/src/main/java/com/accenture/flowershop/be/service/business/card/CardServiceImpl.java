package com.accenture.flowershop.be.service.business.card;

import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.fe.dto.CustomerCardDTO;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.math.RoundingMode.UP;

@Service
class CardServiceImpl implements CardService {

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    private Map<String, List<CustomerCardDTO>> card = new TreeMap<>();

    public CardServiceImpl(){
    }

    public CustomerCardDTO getCardById(String login, long flowerId){
        for (CustomerCardDTO i: card.get(login)) {
            if (i.getFlowerDTO().getId() == flowerId) {
                return i;
            }
        }
        return null;
    }

    public List<CustomerCardDTO> setCardFromSession(String login){
        if (!card.containsKey(login)){
            card.put(login, new ArrayList<>());
        }
        return card.get(login);
    }

    public void addNewFlowerToCard(String login, FlowerDTO flowerDTO, int number, BigDecimal sumPrice){
        if (card.containsKey(login)) {
            CustomerCardDTO customerCardDTO = new CustomerCardDTO(flowerDTO, number, sumPrice);
            flowerDTO.setNumber(flowerDTO.getNumber() - number);
            this.card.get(login).add(customerCardDTO);
        }
    }

    public void editCard(String login, long flowerId, int number, BigDecimal sumPrice){
        CustomerCardDTO i = getCardById(login, flowerId);
        if (i != null) {
            i.setNumber(i.getNumber() + number);
            i.getFlowerDTO().setNumber(i.getFlowerDTO().getNumber() - number);
            i.setSumPrice(i.getSumPrice().add(sumPrice));
        }
    }

    public void clear(String login){
        this.card.get(login).clear();
        flowerBusinessService.getAllFlowerToFlowerDTO();
    }

    public List<CustomerCardDTO> getCard(String login) {
        return card.get(login);
    }

    public BigDecimal getAllSumPrice(int sale, String login){
        BigDecimal sum = new BigDecimal(0.00);
        if (!card.isEmpty()) {
            for (CustomerCardDTO c : card.get(login)) {
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
