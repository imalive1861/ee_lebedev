package com.accenture.flowershop.shop.be.service.business.order;

import com.accenture.flowershop.shop.be.entity.Order;
import com.accenture.flowershop.shop.be.repository.order.OrderRepository;
import com.accenture.flowershop.shop.fe.enums.OrderStatus;
import com.accenture.flowershop.shop.be.entity.Order;
import com.accenture.flowershop.shop.be.repository.order.OrderRepository;
import com.accenture.flowershop.shop.fe.enums.OrderStatus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public OrderBusinessServiceImpl() {
    }

    @Override
    @Transactional
    public void close(Order order) {
        if (order.getStatus().equals(OrderStatus.PAID)) {
            order.setDateClose(new Date());
            order.setStatus(OrderStatus.CLOSED);
            update(order);
            LOG.debug("Order \"{}\" was closed {}", order.getId(), order.getDateClose());
            return;
        }
        LOG.debug("Order \"{}\" not closed", order.getId());
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll(Sort.by("dateCreate"));
    }

    @Override
    public Order get(Long id) {
        return orderRepository.getOne(id);
    }

    @Override
    @Transactional
    public void update(Order order) {
        orderRepository.saveAndFlush(order);
    }
}
