package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.utils.EntityManagerUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderAccessImpl implements OrderAccess {

    private List<Order> orders = new ArrayList<>();

    private EntityManager entityManager = EntityManagerUtils.getEntityManager();

    public void saveOrder(Order order) {
        entityManager.getTransaction().begin();
        entityManager.merge(order);
        entityManager.getTransaction().commit();
    }

    public void delete(long id){
        entityManager.getTransaction().begin();
        entityManager.remove(get(id));
        entityManager.getTransaction().commit();
    }

    public Order get(long id){
        return entityManager.find(Order.class, id);
    }

    public Order getOrderByStatus(String status){
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o where o.status = ?1", Order.class);
        query.setParameter(1, status);
        return query.getSingleResult();
    }

    public void update(Order order){
        entityManager.getTransaction().begin();
        entityManager.merge(order);
        entityManager.getTransaction().commit();
    }

    public List<Order> getAll() {
        TypedQuery<Order> namedQuery = entityManager.createNamedQuery("Order.getAll", Order.class);
        orders.addAll(namedQuery.getResultList());
        return orders;
    }


}
