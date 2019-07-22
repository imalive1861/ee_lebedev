package com.accenture.flowershop.be.access.card;

import com.accenture.flowershop.be.entity.Card;
import com.accenture.flowershop.be.utils.EntityManagerUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CardAccessImpl implements CardAccess {

    private List<Card> cards = new ArrayList<>();

    private EntityManager entityManager = EntityManagerUtils.getEntityManager();

    public void saveCard(Card card) {
        entityManager.getTransaction().begin();
        entityManager.merge(card);
        entityManager.getTransaction().commit();
    }

    public void delete(long id){
        entityManager.getTransaction().begin();
        entityManager.remove(get(id));
        entityManager.getTransaction().commit();
    }

    public void update(Card card){
        entityManager.getTransaction().begin();
        entityManager.merge(card);
        entityManager.getTransaction().commit();
    }

    public Card get(long id){
        return entityManager.find(Card.class, id);
    }

    public List<Card> getAll(){
        TypedQuery<Card> namedQuery = entityManager.createNamedQuery("Card.getAll", Card.class);
        cards.addAll(namedQuery.getResultList());
        return cards;
    }
}
