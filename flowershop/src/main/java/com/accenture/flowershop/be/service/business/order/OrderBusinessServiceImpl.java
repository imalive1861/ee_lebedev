package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.repository.order.OrderRepository;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.enums.OrderStatus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderBusinessServiceImpl implements OrderBusinessService {

    private UserBusinessService userBusinessService;
    private OrderRepository orderRepository;
    private Logger LOG;

    @Autowired
    public OrderBusinessServiceImpl(UserBusinessService userBusinessService,
                                    OrderRepository orderRepository,
                                    Logger LOG){
        this.userBusinessService = userBusinessService;
        this.orderRepository = orderRepository;
        this.LOG = LOG;
    }

    public Order create(User user, BigDecimal sumPrice){
        sumPrice = userBusinessService.checkScore(user, sumPrice);
        if (sumPrice != null) {
            Order order = new Order(user, new BigDecimal(0.00),
                    new Date(), null, OrderStatus.OPENED);
            orderRepository.save(order);
            order = orderRepository.getOrderByStatusAndUserId(
                    OrderStatus.OPENED,userBusinessService.get(user));
            order.setSumPrice(sumPrice);
            order.setStatus(OrderStatus.PAID);
            orderRepository.saveAndFlush(order);
            LOG.debug("Order with total price = {} date of creation = {} was paid",
                    order.getSumPrice(), order.getDateCreate());
            return order;
        }
        return null;
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
