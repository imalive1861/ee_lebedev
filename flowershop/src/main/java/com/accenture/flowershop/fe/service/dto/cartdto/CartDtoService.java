package com.accenture.flowershop.fe.service.dto.cartdto;

import com.accenture.flowershop.be.entity.Cart;
import com.accenture.flowershop.fe.dto.CartDTO;

/**
 * Класс транспортного уровня, который хранит и обрабатывает текущий заказ пользователя.
 */
public interface CartDtoService {
    CartDTO toDto(Cart cart);
    Cart fromDto(CartDTO cartDTO);
}
