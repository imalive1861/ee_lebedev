package com.accenture.flowershop.shop.fe.service.dto.cartdto;

import com.accenture.flowershop.shop.be.entity.Cart;
import com.accenture.flowershop.shop.fe.dto.CartDTO;
import com.accenture.flowershop.shop.be.entity.Cart;
import com.accenture.flowershop.shop.fe.dto.CartDTO;

/**
 * Утилитарный класс транспортного уровня для сущности CartDTO.
 */
public interface CartDtoService {
    /**
     * Переводит сущность Cart в CartDTO.
     *
     * @param cart - объект Cart
     * @return объект CartDTO
     */
    CartDTO toDto(Cart cart);

    /**
     * Переводит CartDTO в сущность Cart.
     *
     * @param cartDTO - объект CartDTO
     * @return объект Cart
     */
    Cart fromDto(CartDTO cartDTO);
}