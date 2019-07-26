package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderAccessImpl implements OrderAccess {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveOrder(Order order) {
        entityManager.merge(order);
    }

    public void delete(long id){
        entityManager.remove(get(id));
    }

    public Order get(long id){
        return entityManager.find(Order.class, id);
    }

    public Order getOrderByStatusAndUser(String status, User user){
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o where o.status = ?1 and o.userId = ?2", Order.class);
        query.setParameter(1, status);
        query.setParameter(2, user);
        return query.getSingleResult();
    }

    public void update(Order order){
        entityManager.merge(order);
    }

    public List<Order> getAll() {
        TypedQuery<Order> namedQuery = entityManager.createNamedQuery("Order.getAll", Order.class);
        return namedQuery.getResultList();
    }


}
