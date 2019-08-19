package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.repository.order.OrderRepository;
import com.accenture.flowershop.fe.enums.OrderStatus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Реализация интерфейса OrderBusinessService.
 */
@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    /**
     * Ссылка на уровень доступа к базе данных для сущности Order.
     */
    @Autowired
    private OrderRepository orderRepository;
    /**
     * Логгер.
     */
    @Autowired
    private Logger LOG;

    public OrderBusinessServiceImpl(){}

    @Override
    public void close(Order order){
        if (order.getStatus().equals(OrderStatus.PAID)) {
            order.setDateClose(new Date());
            order.setStatus(OrderStatus.CLOSED);
            orderRepository.saveAndFlush(order);
            LOG.debug("Order with id = {} was closed = {}",
                    order.getId(), order.getDateClose());
            return;
        }
        LOG.debug("Order with id = {} not closed", order.getId());
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll(Sort.by("dateCreate"));
    }

    @Override
    public Order get(long id) {
            return orderRepository.getOne(id);
    }
}
