package com.accenture.flowershop.shop.fe.service.dto.cartdto;

import com.accenture.flowershop.shop.be.entity.Cart;
import com.accenture.flowershop.shop.fe.dto.CartDTO;
import com.accenture.flowershop.shop.be.entity.Cart;
import com.accenture.flowershop.shop.fe.dto.CartDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Реализация интерфейса CartDtoService.
 * Свойства: mapper.
 */
@Service
public class CartDtoServiceImpl implements CartDtoService {
    /**
     * Маппер.
     */
    @Autowired
    private Mapper mapper;

    @Override
    public CartDTO toDto(Cart cart) {
        return mapper.map(cart, CartDTO.class);
    }

    @Override
    public Cart fromDto(CartDTO cartDTO) {
        return mapper.map(cartDTO, Cart.class);
    }
}