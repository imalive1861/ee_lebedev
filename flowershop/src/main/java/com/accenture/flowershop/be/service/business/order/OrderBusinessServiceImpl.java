package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.repository.order.OrderRepository;
import com.accenture.flowershop.fe.enums.OrderStatus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderBusinessServiceImpl implements OrderBusinessService {

    private OrderRepository orderRepository;
    private Logger LOG;

    @Autowired
    public OrderBusinessServiceImpl(OrderRepository orderRepository,
                                    Logger LOG){
        this.orderRepository = orderRepository;
        this.LOG = LOG;
    }

    public void save(Order order) {
        orderRepository.saveAndFlush(order);
    }

    public void close(Long orderId){
        Order order = get(orderId);
        if (order.getStatus().equals(OrderStatus.PAID)) {
            order.setDateClose(new Date());
            order.setStatus(OrderStatus.CLOSED);
            orderRepository.saveAndFlush(order);
        }
        LOG.debug("Order with total price = {} date of creation = {} was closed = {}",
                order.getSumPrice(), order.getDateCreate(), order.getDateClose());
    }
    @Override
    public List<Order> getAll() {
        return orderRepository.findAll(Sort.by("dateCreate"));
    }

    public Order get(long id) {
            return orderRepository.getOne(id);
    }
}
