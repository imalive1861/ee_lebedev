package com.accenture.flowershop.fe.dto.mappers;

import com.accenture.flowershop.be.entity.Card;
import com.accenture.flowershop.fe.dto.CardDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardDTO cardToCardDto(Card card);
    Card cardDtoToCard(CardDTO cardDTO);
}
