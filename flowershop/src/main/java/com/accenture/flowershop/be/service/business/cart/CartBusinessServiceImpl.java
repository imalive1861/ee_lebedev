package com.accenture.flowershop.be.service.business.cart;

import com.accenture.flowershop.be.entity.Cart;
import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.repository.cart.CartRepository;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.service.business.order.OrderBusinessService;
import com.accenture.flowershop.fe.servicedto.cartdto.CartService;
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
    private OrderBusinessService orderBusinessService;
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    public CartBusinessServiceImpl(CartRepository cartRepository,
                                   CartService cartService,
                                   OrderBusinessService orderBusinessService,
                                   FlowerBusinessService flowerBusinessService){
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.orderBusinessService = orderBusinessService;
        this.flowerBusinessService = flowerBusinessService;
    }

    public boolean save(BigDecimal sumPrice,
                        List<Cart> carts,
                        User user){
        Order order = orderBusinessService.create(user, sumPrice);
        if (order != null) {
            for (Cart c : carts) {
                Cart cart = new Cart(order, c.getNumber(), c.getFlower(), c.getSumPrice());
                flowerBusinessService.update(c.getFlower());
                cartRepository.save(cart);
            }
            cartService.getCartByUserLogin(user.getLogin()).clear();
            return true;
        }
        return false;
    }
    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }
}
