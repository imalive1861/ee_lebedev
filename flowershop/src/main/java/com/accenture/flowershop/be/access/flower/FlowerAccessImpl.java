package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.Flower;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlowerAccessImpl implements FlowerAccess {

    private List<Flower> flowers = new ArrayList<>();

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
        flowers.addAll(namedQuery.getResultList());
        return flowers;
    }
}
