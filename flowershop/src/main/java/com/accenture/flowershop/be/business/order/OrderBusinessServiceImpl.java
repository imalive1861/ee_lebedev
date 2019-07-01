package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.order.OrderAccess;
import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    @Autowired
    private OrderAccess orderAccess;

    public void save(Order order) {
        if(order!=null) {
            List<Order> orders = orderAccess.getAll();
            if(!orders.isEmpty()) {
                Order lastOrder = orders.get(orders.size() - 1);
                order.setId(lastOrder.getId()+1);
                //orderAccess.save(order);
            }
        }
    }

    public void delete(Order order) {
        if(order!=null) {
            orderAccess.delete(order);
        }
    }

    public List<Order> getAll() {
        return orderAccess.getAll();
    }

    public Order getById(Integer id) {
        if(id!=null) {
            return orderAccess.getById(id);
        }
        return null;
    }
}
