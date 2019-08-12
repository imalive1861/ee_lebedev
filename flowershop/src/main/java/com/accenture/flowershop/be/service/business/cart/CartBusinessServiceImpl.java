package com.accenture.flowershop.be.service.business.cart;

import com.accenture.flowershop.be.repository.cart.CartRepository;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.service.business.order.OrderBusinessService;
import com.accenture.flowershop.fe.dto.CartDTO;
import com.accenture.flowershop.fe.dto.CustomerCartDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.dto.mappers.CartMapper;
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

    @Autowired
    public CartBusinessServiceImpl(CartRepository cartRepository,
                                   CartService cartService,
                                   CartMapper cartMapper,
                                   OrderBusinessService orderBusinessService,
                                   FlowerBusinessService flowerBusinessService){
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.cartMapper = cartMapper;
        this.orderBusinessService = orderBusinessService;
        this.flowerBusinessService = flowerBusinessService;
    }

    public boolean save(BigDecimal sumPrice,
                        List<CustomerCartDTO> customerCartDTOS,
                        UserDTO userDTO){
        OrderDTO orderDTO = orderBusinessService.create(userDTO, sumPrice);
        if (orderDTO != null) {
            for (CustomerCartDTO c : customerCartDTOS) {
                CartDTO cartDTO = new CartDTO(orderDTO, c.getFlowerDTO(), c.getNumber());
                flowerBusinessService.update(c.getFlowerDTO());
                cartRepository.save(cartMapper.cartDtoToCart(cartDTO));
            }
            cartService.getCartByUserLogin(userDTO.getLogin()).clear();
            return true;
        }
        return false;
    }
    @Override
    public List<CartDTO> getAll() {
        return cartMapper.cartToCartDtos(cartRepository.findAll());
    }
}
