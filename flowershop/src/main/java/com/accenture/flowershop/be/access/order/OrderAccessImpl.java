package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderAccessImpl implements OrderAccess {

    private List<Order> orders = new ArrayList<>();

    public EntityManager orderEntityManager = Persistence.createEntityManagerFactory("FLOWERSHOP").createEntityManager();

    public void saveOrder(Order order) {
        orderEntityManager.getTransaction().begin();
        orderEntityManager.merge(order);
        orderEntityManager.getTransaction().commit();
    }

    public void delete(long id){
        orderEntityManager.getTransaction().begin();
        orderEntityManager.remove(get(id));
        orderEntityManager.getTransaction().commit();
    }

    public Order get(long id){
        return orderEntityManager.find(Order.class, id);
    }

    public void update(Order order){
        orderEntityManager.getTransaction().begin();
        orderEntityManager.merge(order);
        orderEntityManager.getTransaction().commit();
    }

    public List<Order> getAll() {
        TypedQuery<Order> namedQuery = orderEntityManager.createNamedQuery("Order.getAll", Order.class);
        orders.addAll(namedQuery.getResultList());
        return orders;
    }
}
