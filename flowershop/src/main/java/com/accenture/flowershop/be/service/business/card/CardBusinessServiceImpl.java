package com.accenture.flowershop.be.service.business.card;

import com.accenture.flowershop.be.repository.card.CardRepository;
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
import java.util.List;

@Service
@Transactional
public class CardBusinessServiceImpl implements CardBusinessService{

    private CardRepository cardRepository;
    private CardService cardService;
    private CardMapper cardMapper;
    private OrderBusinessService orderBusinessService;
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    public CardBusinessServiceImpl(CardRepository cardRepository,
                                   CardService cardService,
                                   CardMapper cardMapper,
                                   OrderBusinessService orderBusinessService,
                                   FlowerBusinessService flowerBusinessService){
        this.cardRepository = cardRepository;
        this.cardService = cardService;
        this.cardMapper = cardMapper;
        this.orderBusinessService = orderBusinessService;
        this.flowerBusinessService = flowerBusinessService;
    }

    public boolean saveCardToOrder(BigDecimal sumPrice,
                                   List<CustomerCardDTO> customerCardDTOs,
                                   UserDTO userDTO){
        OrderDTO orderDTO = orderBusinessService.paidOrder(userDTO, sumPrice);
        if (orderDTO != null) {
            for (CustomerCardDTO c : customerCardDTOs) {
                CardDTO cardDTO = new CardDTO(orderDTO, c.getFlowerDTO(), c.getNumber());
                flowerBusinessService.updateFlower(c.getFlowerDTO());
                cardRepository.save(cardMapper.cardDtoToCard(cardDTO));
            }
            cardService.getCard(userDTO.getLogin()).clear();
            return true;
        }
        return false;
    }
    @Override
    public List<CardDTO> getAll() {
        return cardMapper.cardToCardDtos(cardRepository.findAll());
    }
}
