package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.utils.EntityManagerUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlowerAccessImpl implements FlowerAccess {

    private List<Flower> flowers = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager = EntityManagerUtils.getEntityManager();

    public void saveFlower(Flower flower) {
        entityManager.getTransaction().begin();
        entityManager.merge(flower);
        entityManager.getTransaction().commit();
    }

    public void delete(long id){
        entityManager.getTransaction().begin();
        entityManager.remove(get(id));
        entityManager.getTransaction().commit();
    }

    public void update(Flower flower){
        entityManager.getTransaction().begin();
        entityManager.merge(flower);
        entityManager.getTransaction().commit();
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
