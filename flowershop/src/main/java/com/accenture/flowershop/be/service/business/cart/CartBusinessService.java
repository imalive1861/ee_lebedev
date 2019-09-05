package com.accenture.flowershop.be.service.business.cart;

import com.accenture.flowershop.be.entity.Cart;

/**
 * Интерфейс бизнес логики для сущности Cart.
 */
public interface CartBusinessService {
    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param id - идентификатор позиции в корзине
     * @return объект Cart
     */
    Cart get(Long id);
}
