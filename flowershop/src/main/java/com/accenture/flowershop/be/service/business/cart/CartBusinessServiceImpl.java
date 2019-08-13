package com.accenture.flowershop.be.service.business.cart;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.repository.cart.CartRepository;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.service.business.order.OrderBusinessService;
import com.accenture.flowershop.fe.dto.CartDTO;
import com.accenture.flowershop.fe.dto.CustomerCartDTO;
import com.accenture.flowershop.fe.dto.mappers.CartMapper;
import com.accenture.flowershop.fe.dto.mappers.FlowerMapper;
import com.accenture.flowershop.fe.dto.mappers.OrderMapper;
import com.accenture.flowershop.fe.servicedto.customercartdto.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class CartBusinessServiceImpl implements CartBusinessService {

    private CartRepository cartRepository;
    private CartService cartService;
    private CartMapper cartMapper;
    private OrderBusinessService orderBusinessService;
    private FlowerBusinessService flowerBusinessService;
    private OrderMapper orderMapper;
    private FlowerMapper flowerMapper;

    @Autowired
    public CartBusinessServiceImpl(CartRepository cartRepository,
                                   CartService cartService,
                                   CartMapper cartMapper,
                                   OrderBusinessService orderBusinessService,
                                   FlowerBusinessService flowerBusinessService,
                                   OrderMapper orderMapper,
                                   FlowerMapper flowerMapper){
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.cartMapper = cartMapper;
        this.orderBusinessService = orderBusinessService;
        this.flowerBusinessService = flowerBusinessService;
        this.orderMapper = orderMapper;
        this.flowerMapper = flowerMapper;
    }

    public boolean save(BigDecimal sumPrice,
                        List<CustomerCartDTO> customerCartDTOS,
                        User user){
        Order order = orderBusinessService.create(user, sumPrice);
        if (order != null) {
            for (CustomerCartDTO c : customerCartDTOS) {
                CartDTO cartDTO = new CartDTO(orderMapper.orderToOrderDto(order), c.getFlowerDTO(), c.getNumber());
                flowerBusinessService.update(flowerMapper.flowerDtoToFlower(c.getFlowerDTO()));
                cartRepository.save(cartMapper.cartDtoToCart(cartDTO));
            }
            cartService.getCartByUserLogin(user.getLogin()).clear();
            return true;
        }
        return false;
    }
    @Override
    public List<CartDTO> getAll() {
        return cartMapper.cartToCartDtos(cartRepository.findAll());
    }
}
