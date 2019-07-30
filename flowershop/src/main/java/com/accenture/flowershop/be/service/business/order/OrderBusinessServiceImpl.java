package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.access.order.OrderAccess;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.be.utils.LoggerUtils;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.dto.mappers.OrderMapper;
import com.accenture.flowershop.fe.enums.OrderStatus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderBusinessServiceImpl implements OrderBusinessService {

    private Logger LOG = LoggerUtils.getLOG();

    private OrderAccess orderAccess;
    private UserBusinessService userBusinessService;
    private OrderMapper orderMapper;

    @Autowired
    public OrderBusinessServiceImpl(OrderAccess orderAccess,
                                    UserBusinessService userBusinessService,
                                    OrderMapper orderMapper){
        this.userBusinessService = userBusinessService;
        this.orderAccess = orderAccess;
        this.orderMapper = orderMapper;
    }

    public OrderDTO openOrder(UserDTO userDTO){
        OrderDTO orderDTO = new OrderDTO(userDTO, new BigDecimal(0.00),
                        new Date(), null, OrderStatus.OPENED.getTitle());
        orderAccess.saveOrder(orderMapper.orderDtoToOrder(orderDTO));
        orderDTO = orderMapper.orderToOrderDto(orderAccess.getOrderByStatusAndUser(
                OrderStatus.OPENED.getTitle(),userBusinessService.get(userDTO)));
        return orderDTO;
    }

    public boolean paidOrder(OrderDTO orderDTO, BigDecimal sumPrice){
        sumPrice = userBusinessService.checkScore(orderDTO.getUserId(), sumPrice);
        if (sumPrice != null) {
            orderDTO.setSumPrice(sumPrice);
            orderDTO.setStatus(OrderStatus.PAID.getTitle());
            orderAccess.update(orderMapper.orderDtoToOrder(orderDTO));
            LOG.debug("Order with total price = {} date of creation = {} was paid",
                    orderDTO.getSumPrice(), orderDTO.getDateCreate());
            return true;
        }
        return false;
    }

    public void closeOrder(OrderDTO orderDTO){
        if (orderDTO.getStatus().equals(OrderStatus.PAID.getTitle())) {
            orderDTO.setDateClose(new Date());
            orderDTO.setStatus(OrderStatus.CLOSED.getTitle());
            orderAccess.update(orderMapper.orderDtoToOrder(orderDTO));
        }
        LOG.debug("Order with total price = {} date of creation = {} was closed = {}",
                orderDTO.getSumPrice(), orderDTO.getDateCreate(), orderDTO.getDateClose());
    }
    @Override
    public List<OrderDTO> getAll() {
        return orderMapper.orderToOrderDtos(orderAccess.getAll());
    }

    public OrderDTO get(long id) {
        if(id != 0) {
            return orderMapper.orderToOrderDto(orderAccess.get(id));
        }
        return null;
    }
}
