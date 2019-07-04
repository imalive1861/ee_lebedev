package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.access.order.OrderAccess;
import com.accenture.flowershop.be.entity.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderBusinessServiceImpl.class);

    @Autowired
    private OrderAccess orderAccess;

    public void saveNewOrder(Order order) {
        if(order!=null) {
            orderAccess.saveOrder(order.getSumPrice());
        }
        LOG.debug("Order with total price = {} date of creation = {} was created", order.getSumPrice(), order.getDateCreate());
    }

    public void closeOrder(Order order){
        orderAccess.closeOrder(order.getId());
        LOG.debug("Order with total price = {} date of creation = {} was closed = {}", order.getSumPrice(), order.getDateCreate(), order.getDateClose());
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
