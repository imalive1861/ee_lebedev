package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.access.order.OrderAccess;
import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    private static final String ORDER_OPENED = "OPENED";
    private static final String ORDER_CLOSED = "CLOSED";

    private static final Logger LOG = LoggerFactory.getLogger(OrderBusinessServiceImpl.class);

    private OrderAccess orderAccess;

    private Map<Long, OrderDTO> orderDTOs;

    @Autowired
    public OrderBusinessServiceImpl(OrderAccess orderAccess){
        this.orderAccess = orderAccess;
        orderDTOs = new TreeMap<>();
        for (Order u: orderAccess.getAll()){
            OrderDTO orderDTO = toOrderDTO(u);
            orderDTOs.put(orderDTO.getId(), orderDTO);
        }
    }

    public void saveOrder(OrderDTO orderDTO) {
        if(orderDTO!=null) {
            orderAccess.saveOrder(toOrder(orderDTO));
            LOG.debug("Order with total price = {} date of creation = {} was created",
                    orderDTO.getSumPrice(), orderDTO.getDateCreate());
        }
    }

    public OrderDTO createNewOrder(BigDecimal sumPrice){
        OrderDTO orderDTO = new OrderDTO(sumPrice,LocalDate.now(),null,ORDER_OPENED);
        saveOrder(orderDTO);
        orderDTOs.put(orderDTO.getId(), orderDTO);
        LOG.debug("Order with total price = {} was create = {}",
                orderDTO.getSumPrice(), orderDTO.getDateCreate());
        return orderDTO;
    }

    public void closeOrder(OrderDTO orderDTO){
        if (orderDTO.getStatus().equals(ORDER_OPENED)) {
            orderDTO.setDateClose(LocalDate.now());
            orderDTO.setStatus(ORDER_CLOSED);
            Order order = toOrder(orderDTO);
            orderAccess.update(order);
        }
        LOG.debug("Order with total price = {} date of creation = {} was closed = {}",
                orderDTO.getSumPrice(), orderDTO.getDateCreate(), orderDTO.getDateClose());
    }

    private Order toOrder(OrderDTO orderDTO){
        if(orderAccess.get(orderDTO.getId()) != null) {
            return orderAccess.get(orderDTO.getId());
        }
        return new Order(orderDTO.getSumPrice(),orderDTO.getDateCreate(),
                orderDTO.getDateClose(),orderDTO.getStatus());
    }

    private OrderDTO toOrderDTO(Order order){
        if(orderDTOs.get(order.getId()) != null) {
            return orderDTOs.get(order.getId());
        }
        return new OrderDTO(order.getSumPrice(),order.getDateCreate(),
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
