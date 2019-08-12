package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.repository.order.OrderRepository;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.dto.mappers.OrderMapper;
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
    private OrderMapper orderMapper;
    private Logger LOG;

    @Autowired
    public OrderBusinessServiceImpl(UserBusinessService userBusinessService,
                                    OrderRepository orderRepository,
                                    OrderMapper orderMapper,
                                    Logger LOG){
        this.userBusinessService = userBusinessService;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.LOG = LOG;
    }

    public OrderDTO create(UserDTO userDTO, BigDecimal sumPrice){
        sumPrice = userBusinessService.checkScore(userDTO, sumPrice);
        if (sumPrice != null) {
            OrderDTO orderDTO = new OrderDTO(userDTO, new BigDecimal(0.00),
                    new Date(), null, OrderStatus.OPENED);
            orderRepository.save(orderMapper.orderDtoToOrder(orderDTO));
            orderDTO = orderMapper.orderToOrderDto(orderRepository.getOrderByStatusAndUserId(
                    OrderStatus.OPENED,userBusinessService.get(userDTO)));
            orderDTO.setSumPrice(sumPrice);
            orderDTO.setStatus(OrderStatus.PAID);
            orderRepository.saveAndFlush(orderMapper.orderDtoToOrder(orderDTO));
            LOG.debug("Order with total price = {} date of creation = {} was paid",
                    orderDTO.getSumPrice(), orderDTO.getDateCreate());
            return orderDTO;
        }
        return null;
    }

    public void close(OrderDTO orderDTO){
        if (orderDTO.getStatus().equals(OrderStatus.PAID)) {
            orderDTO.setDateClose(new Date());
            orderDTO.setStatus(OrderStatus.CLOSED);
            orderRepository.saveAndFlush(orderMapper.orderDtoToOrder(orderDTO));
        }
        LOG.debug("Order with total price = {} date of creation = {} was closed = {}",
                orderDTO.getSumPrice(), orderDTO.getDateCreate(), orderDTO.getDateClose());
    }
    @Override
    public List<OrderDTO> getAll() {
        return orderMapper.orderToOrderDtos(orderRepository.findAll(Sort.by("dateCreate")));
    }

    public OrderDTO get(long id) {
        if(id != 0) {
            return orderMapper.orderToOrderDto(orderRepository.getOne(id));
        }
        return null;
    }
}
