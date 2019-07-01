package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderAccessImpl implements OrderAccess {

    private List<Order> orders = new ArrayList<>();

    public void saveOrder(double sumPrice) {
        orders.add(new Order(setNextId(), sumPrice, new Date(), false));
    }

    public void closeOrder(int id){
        Order order = orders.get(id);
        order.setDateClose(new Date());
        order.setStatus(true);

    }

    private int setNextId(){
        int i = 0;
        for (Order user: orders){
            if (user.getId() > i){
                i = user.getId();
            }
        }
        return i;
    }

    public void delete(Order order) {
        orders.remove(order);
    }

    public List<Order> getAll() {
        return orders;
    }

    public Order getById(Integer id) {
        return orders.get(id);
    }
}
