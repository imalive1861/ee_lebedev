package com.accenture.flowershop.be.service.business.cart;

import com.accenture.flowershop.be.entity.Cart;
import com.accenture.flowershop.be.repository.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Реализация интерфейса CartBusinessService.
 */
@Service
public class CartBusinessServiceImpl implements CartBusinessService {

    /**
     * Ссылка на уровень доступа к базе данных для сущности Cart.
     */
    @Autowired
    private CartRepository cartRepository;

    public CartBusinessServiceImpl() {
    }

    @Override
    public Cart get(Long id) {
        return cartRepository.getOne(id);
    }
}