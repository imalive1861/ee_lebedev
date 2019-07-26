package com.accenture.flowershop.be.access.card;

import com.accenture.flowershop.be.entity.Card;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CardAccessImpl implements CardAccess {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveCard(Card card) {
        entityManager.merge(card);
    }

    public void delete(long id){
        entityManager.remove(get(id));
    }

    public void update(Card card){
        entityManager.merge(card);
    }

    public Card get(long id){
        return entityManager.find(Card.class, id);
    }

    public List<Card> getAll(){
        TypedQuery<Card> namedQuery = entityManager.createNamedQuery("Card.getAll", Card.class);
        return namedQuery.getResultList();
    }
}
