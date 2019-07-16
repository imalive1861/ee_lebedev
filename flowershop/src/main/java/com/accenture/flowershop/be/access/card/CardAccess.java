package com.accenture.flowershop.be.access.card;

import com.accenture.flowershop.be.entity.Card;

import java.util.List;

public interface CardAccess {
    void saveCard(Card card);
    void delete(long id);
    void update(Card card);
    Card get(long id);
    List<Card> getAll();
}
