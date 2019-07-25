package com.accenture.flowershop.be.service.business.card;

import com.accenture.flowershop.be.access.card.CardAccess;
import com.accenture.flowershop.be.entity.Card;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.service.business.order.OrderBusinessService;
import com.accenture.flowershop.fe.dto.CardDTO;
import com.accenture.flowershop.fe.dto.CustomerCardDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.dto.mappers.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CardBusinessServiceImpl implements CardBusinessService{

    private CardAccess cardAccess;
    private CardService cardService;
    private CardMapper cardMapper;
    private OrderBusinessService orderBusinessService;
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    public CardBusinessServiceImpl(CardAccess cardAccess,
                                   CardService cardService,
                                   CardMapper cardMapper,
                                   OrderBusinessService orderBusinessService,
                                   FlowerBusinessService flowerBusinessService){
        this.cardAccess = cardAccess;
        this.cardService = cardService;
        this.cardMapper = cardMapper;
        this.orderBusinessService = orderBusinessService;
        this.flowerBusinessService = flowerBusinessService;
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
                cardAccess.saveCard(cardMapper.cardDtoToCard(cardDTO));
            }
            cardService.getCard(userDTO.getLogin()).clear();
            return true;
        }
        return false;
    }
    @Override
    public List<CardDTO> getAll() {
        Map<Long, CardDTO> getAll = new TreeMap<>();
        for (Card f : cardAccess.getAll()) {
            getAll.put(f.getId(), cardMapper.cardToCardDto(f));
        }
        return new ArrayList<>(getAll.values());
    }
}
