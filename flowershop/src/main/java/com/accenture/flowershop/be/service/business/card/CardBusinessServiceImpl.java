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
    private UserBusinessService userBusinessService;
    private OrderBusinessService orderBusinessService;
    private FlowerBusinessService flowerBusinessService;

    private Map<Long, CardDTO> cardDTOs;

    @Autowired
    public CardBusinessServiceImpl(CardAccess cardAccess,
                                   UserBusinessService userBusinessService,
                                   OrderBusinessService orderBusinessService,
                                   FlowerBusinessService flowerBusinessService){
        this.cardAccess = cardAccess;
        this.userBusinessService = userBusinessService;
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
    public boolean saveCardToOrder(OrderDTO orderDTO, BigDecimal sumPrice,
                                   List<CustomerCardDTO> customerCardDTOs, UserDTO userDTO){
        sumPrice = userBusinessService.checkScore(userDTO, sumPrice);
        if (sumPrice != null) {
            orderBusinessService.paidOrder(orderDTO, sumPrice);
            for (CustomerCardDTO c : customerCardDTOs) {
                CardDTO cardDTO = new CardDTO(userDTO, orderDTO, c.getFlowerDTO(), c.getNumber());
                flowerBusinessService.updateFlower(c.getFlowerDTO());
                saveCard(cardDTO);
            }
            getAllCardToCardDTO();
            return true;
        }
        return false;
    }

    private Card toCard(CardDTO cardDTO){
        if(cardAccess.get(cardDTO.getId()) != null) {
            return cardAccess.get(cardDTO.getId());
        }
        return new Card(userBusinessService.getDAO(cardDTO.getUser().getLogin()),
                orderBusinessService.getDAO(cardDTO.getOrder().getId()),
                flowerBusinessService.getDAO(cardDTO.getFlower().getId()),
                cardDTO.getNumber());
    }

    private CardDTO toCardDTO(Card card){
        if(cardDTOs.get(card.getId()) != null) {
            return cardDTOs.get(card.getId());
        }
        return new CardDTO(card.getId(),
                userBusinessService.get(card.getUser().getLogin()),
                orderBusinessService.get(card.getOrder().getId()),
                flowerBusinessService.get(card.getFlower().getId()),
                card.getNumber());
    }

    public Map<Long, CardDTO> getAll() {
        return cardDTOs;
    }
}
