package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.access.order.OrderAccess;
import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.be.utils.LoggerUtils;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    private static final String ORDER_OPENED = "OPENED";
    private static final String ORDER_PAID = "PAID";
    private static final String ORDER_CLOSED = "CLOSED";

    private Logger LOG = LoggerUtils.getLOG();

    private OrderAccess orderAccess;
    private UserBusinessService userBusinessService;
    private Map<Long, OrderDTO> orderDTOs;

    @Autowired
    public OrderBusinessServiceImpl(OrderAccess orderAccess,
                                    UserBusinessService userBusinessService){
        this.userBusinessService = userBusinessService;
        this.orderAccess = orderAccess;
        orderDTOs = new TreeMap<>();
        getAllOrderToOrderDTO();
    }

    @Transactional
    public OrderDTO openOrder(UserDTO userDTO){
        List<Order> order = new ArrayList<>();
        OrderDTO orderDTO = null;
        while (order.isEmpty()) {
            order.addAll(orderAccess.getOrderByStatusAndUser(ORDER_OPENED,
                    userBusinessService.getDAO(userDTO.getLogin())));
            if (!order.isEmpty()) {
                for (Order o: order)
                    if (o.getId() != 0 && userBusinessService.get(o.getUserId().getLogin()) == userDTO &&
                            o.getStatus().equals("OPENED")) {
                        orderDTO = toOrderDTO(o);
                        LOG.debug("OrderDTO = {} was upload", orderDTO);
                    }
            } else {
                orderDTO = new OrderDTO(0, userDTO, new BigDecimal(0.00),
                        LocalDate.now(), null, ORDER_OPENED);
                LOG.debug("New OrderDTO = {} was created", orderDTO);
                orderAccess.saveOrder(toOrder(orderDTO));
            }
        }
        LOG.debug("Order with id = {}, date of creation = {} was opened",
                orderDTO.getId(), orderDTO.getDateCreate());
        return orderDTO;
    }

    @Transactional
    public boolean paidOrder(OrderDTO orderDTO, BigDecimal sumPrice){
        sumPrice = userBusinessService.checkScore(orderDTO.getUserId(), sumPrice);
        if (sumPrice != null) {
            orderDTO.setSumPrice(sumPrice);
            orderDTO.setStatus(ORDER_PAID);
            orderAccess.update(toOrder(orderDTO));
            getAllOrderToOrderDTO();
            LOG.debug("Order with total price = {} date of creation = {} was paid",
                    orderDTO.getSumPrice(), orderDTO.getDateCreate());
            return true;
        }
        return false;
    }

    private void getAllOrderToOrderDTO(){
        orderDTOs.clear();
        for (Order u: orderAccess.getAll()){
            OrderDTO orderDTO = toOrderDTO(u);
            orderDTOs.put(orderDTO.getId(), orderDTO);
        }
    }

    @Transactional
    public void closeOrder(OrderDTO orderDTO){
        if (orderDTO.getStatus().equals(ORDER_PAID)) {
            orderDTO.setDateClose(LocalDate.now());
            orderDTO.setStatus(ORDER_CLOSED);
            orderAccess.update(toOrder(orderDTO));
        }
        LOG.debug("Order with total price = {} date of creation = {} was closed = {}",
                orderDTO.getSumPrice(), orderDTO.getDateCreate(), orderDTO.getDateClose());
    }

    private Order toOrder(OrderDTO orderDTO){
        Order order = orderAccess.get(orderDTO.getId());
        if(order != null) {
            order.setSumPrice(orderDTO.getSumPrice());
            order.setDateCreate(orderDTO.getDateCreate());
            order.setDateClose(orderDTO.getDateClose());
            order.setStatus(orderDTO.getStatus());
            return order;
        }
        return new Order(userBusinessService.getDAO(orderDTO.getUserId().getLogin()),
                orderDTO.getSumPrice(),orderDTO.getDateCreate(),
                orderDTO.getDateClose(),orderDTO.getStatus());
    }

    private OrderDTO toOrderDTO(Order order){
        if(orderDTOs.get(order.getId()) != null) {
            return orderDTOs.get(order.getId());
        }
        return new OrderDTO(order.getId(),
                userBusinessService.get(order.getUserId().getLogin()),
                order.getSumPrice(),order.getDateCreate(),
                order.getDateClose(),order.getStatus());
    }

    public Map<Long, OrderDTO> getAll() {
        return orderDTOs;
    }

    public OrderDTO get(long id) {
        if(id != 0) {
            return orderDTOs.get(id);
        }
        return null;
    }

    public Order getDAO(long id){
        return orderAccess.get(id);
    }
}
