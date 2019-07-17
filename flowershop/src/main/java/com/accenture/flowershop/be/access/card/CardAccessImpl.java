package com.accenture.flowershop.be.access.card;

import com.accenture.flowershop.be.entity.Card;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CardAccessImpl implements CardAccess {

    private List<Card> cards = new ArrayList<>();

    private EntityManager cardEntityManager =
            Persistence.createEntityManagerFactory("FLOWERSHOP").createEntityManager();

    public void saveCard(Card card) {
        cardEntityManager.getTransaction().begin();
        cardEntityManager.merge(card);
        cardEntityManager.getTransaction().commit();
    }

    public void delete(long id){
        cardEntityManager.getTransaction().begin();
        cardEntityManager.remove(get(id));
        cardEntityManager.getTransaction().commit();
    }

    public void update(Card card){
        cardEntityManager.getTransaction().begin();
        cardEntityManager.merge(card);
        cardEntityManager.getTransaction().commit();
    }

    public Card get(long id){
        return cardEntityManager.find(Card.class, id);
    }

    public List<Card> getAll(){
        TypedQuery<Card> namedQuery = cardEntityManager.createNamedQuery("Card.getAll", Card.class);
        cards.addAll(namedQuery.getResultList());
        return cards;
    }
}
