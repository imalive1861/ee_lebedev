package com.accenture.flowershop.be.service.business.card;

import com.accenture.flowershop.be.access.card.CardAccess;
import com.accenture.flowershop.be.entity.Card;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.service.business.order.OrderBusinessService;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.CardDTO;
import com.accenture.flowershop.fe.dto.CustomerCardDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CardBusinessServiceImpl implements CardBusinessService{

    private CardAccess cardAccess;
    private CardService cardService;
    private OrderBusinessService orderBusinessService;
    private FlowerBusinessService flowerBusinessService;

    private Map<Long, CardDTO> cardDTOs;

    @Autowired
    public CardBusinessServiceImpl(CardAccess cardAccess,
                                   CardService cardService,
                                   OrderBusinessService orderBusinessService,
                                   FlowerBusinessService flowerBusinessService){
        this.cardAccess = cardAccess;
        this.cardService = cardService;
        this.orderBusinessService = orderBusinessService;
        this.flowerBusinessService = flowerBusinessService;

        cardDTOs = new TreeMap<>();
        getAllCardToCardDTO();
    }

    private void getAllCardToCardDTO(){
        cardDTOs.clear();
        for (Card u: cardAccess.getAll()){
            CardDTO cardDTO = toCardDTO(u);
            cardDTOs.put(cardDTO.getId(), cardDTO);
        }
    }

    private void saveCard(CardDTO cardDTO){
        if (cardDTO != null){
            cardAccess.saveCard(toCard(cardDTO));
        }
    }
    @Transactional
    public boolean saveCardToOrder(BigDecimal sumPrice,
                                   List<CustomerCardDTO> customerCardDTOs,
                                   UserDTO userDTO){
        OrderDTO orderDTO = orderBusinessService.openOrder(userDTO);
        if (orderBusinessService.paidOrder(orderDTO, sumPrice)) {
            for (CustomerCardDTO c : customerCardDTOs) {
                CardDTO cardDTO = new CardDTO(orderDTO, c.getFlowerDTO(), c.getNumber());
                flowerBusinessService.updateFlower(c.getFlowerDTO());
                saveCard(cardDTO);
            }
            getAllCardToCardDTO();
            cardService.getCard(userDTO.getLogin()).clear();
            return true;
        }
        return false;
    }

    private Card toCard(CardDTO cardDTO){
        if(cardAccess.get(cardDTO.getId()) != null) {
            return cardAccess.get(cardDTO.getId());
        }
        return new Card(orderBusinessService.getDAO(cardDTO.getOrder().getId()),
                flowerBusinessService.getDAO(cardDTO.getFlower().getId()),
                cardDTO.getNumber());
    }

    private CardDTO toCardDTO(Card card){
        if(cardDTOs.get(card.getId()) != null) {
            return cardDTOs.get(card.getId());
        }
        return new CardDTO(card.getId(),
                orderBusinessService.get(card.getOrder().getId()),
                flowerBusinessService.get(card.getFlower().getId()),
                card.getNumber());
    }

    public Map<Long, CardDTO> getAll() {
        return cardDTOs;
    }
}
