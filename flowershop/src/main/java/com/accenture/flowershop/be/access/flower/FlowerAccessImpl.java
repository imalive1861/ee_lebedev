package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlowerAccessImpl implements FlowerAccess {

    private List<Flower> flowers = new ArrayList<>();

    private EntityManager flowersEntityManager = Persistence.createEntityManagerFactory("FLOWERSHOP").createEntityManager();

    public void saveFlower(Flower flower) {
        flowersEntityManager.getTransaction().begin();
        flowersEntityManager.merge(flower);
        flowersEntityManager.getTransaction().commit();
    }

    public void delete(long id){
        flowersEntityManager.getTransaction().begin();
        flowersEntityManager.remove(get(id));
        flowersEntityManager.getTransaction().commit();
    }

    public void update(Flower flower){
        flowersEntityManager.getTransaction().begin();
        flowersEntityManager.merge(flower);
        flowersEntityManager.getTransaction().commit();
    }

    public Flower get(long id){
        return flowersEntityManager.find(Flower.class, id);
    }

    public List<Flower> getAll(){
        TypedQuery<Flower> namedQuery = flowersEntityManager.createNamedQuery("Flower.getAll", Flower.class);
        flowers.addAll(namedQuery.getResultList());
        return flowers;
    }
}
