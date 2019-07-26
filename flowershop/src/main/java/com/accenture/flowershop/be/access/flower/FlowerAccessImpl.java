package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.Flower;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class FlowerAccessImpl implements FlowerAccess {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveFlower(Flower flower) {
        entityManager.merge(flower);
    }

    public void delete(long id){
        entityManager.remove(get(id));
    }

    public void update(Flower flower){
        entityManager.merge(flower);
    }

    public Flower get(long id){
        return entityManager.find(Flower.class, id);
    }

    public List<Flower> getAll(){
        TypedQuery<Flower> namedQuery = entityManager.createNamedQuery("Flower.getAll", Flower.class);
        return namedQuery.getResultList();
    }

    public List<Flower> getFlowerByName(String name){
        TypedQuery<Flower> query = entityManager.createQuery(
                "select f from Flower f where f.name = ?1", Flower.class);
        query.setParameter(1, name);
        return query.getResultList();
    }

    public List<Flower> getFlowerByPrice(BigDecimal min, BigDecimal max){
        TypedQuery<Flower> query = entityManager.createQuery(
                "select f from Flower f where f.price >= ?1 and f.price <= ?2", Flower.class);
        query.setParameter(1, min);
        query.setParameter(2, max);
        return query.getResultList();
    }
}
