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

    @Autowired
    public CartBusinessServiceImpl(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart get(long id) {
        return cartRepository.getOne(id);
    }
}
