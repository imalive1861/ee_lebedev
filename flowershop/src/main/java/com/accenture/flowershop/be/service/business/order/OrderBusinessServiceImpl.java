package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.access.order.OrderAccess;
import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    private static final String ORDER_OPENED = "OPENED";
    private static final String ORDER_PAID = "PAID";
    private static final String ORDER_CLOSED = "CLOSED";

    private static final Logger LOG = LoggerFactory.getLogger(OrderBusinessServiceImpl.class);

    private OrderAccess orderAccess;

    private Map<Long, OrderDTO> orderDTOs;

    @Autowired
    public OrderBusinessServiceImpl(OrderAccess orderAccess){
        this.orderAccess = orderAccess;
        orderDTOs = new TreeMap<>();
        getAllOrderToOrderDTO();
    }

    public OrderDTO openOrder(){
        Order order = null;
        OrderDTO orderDTO = null;
        while (order == null) {
            try{
                order = orderAccess.getOrderByStatus("OPENED");
                if (order != null) {
                    orderDTO = toOrderDTO(order);
                }
            } catch (NoResultException e) {
                e.printStackTrace();
                if (order == null) {
                    orderDTO = new OrderDTO(0, new BigDecimal(0.00),
                            LocalDate.now(), null, ORDER_OPENED);
                    orderAccess.saveOrder(toOrder(orderDTO));
                }
            }
        }
        return orderDTO;
    }

    public void paidOrder(OrderDTO orderDTO, BigDecimal sumPrice){
        orderDTO.setSumPrice(sumPrice);
        orderDTO.setStatus(ORDER_PAID);
        orderAccess.update(toOrder(orderDTO));
        getAllOrderToOrderDTO();
    }

    private void getAllOrderToOrderDTO(){
        orderDTOs.clear();
        for (Order u: orderAccess.getAll()){
            OrderDTO orderDTO = toOrderDTO(u);
            orderDTOs.put(orderDTO.getId(), orderDTO);
        }
    }

    public void closeOrder(OrderDTO orderDTO){
        if (orderDTO.getStatus().equals(ORDER_PAID)) {
            orderDTO.setDateClose(LocalDate.now());
            orderDTO.setStatus(ORDER_CLOSED);
            Order order = toOrder(orderDTO);
            orderAccess.update(order);
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
        return new Order(orderDTO.getSumPrice(),orderDTO.getDateCreate(),
                orderDTO.getDateClose(),orderDTO.getStatus());
    }

    private OrderDTO toOrderDTO(Order order){
        if(orderDTOs.get(order.getId()) != null) {
            return orderDTOs.get(order.getId());
        }
        return new OrderDTO(order.getId(),order.getSumPrice(),order.getDateCreate(),
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
