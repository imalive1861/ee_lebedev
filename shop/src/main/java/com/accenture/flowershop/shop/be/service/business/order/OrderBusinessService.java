package com.accenture.flowershop.shop.be.service.business.order;

import com.accenture.flowershop.shop.be.entity.Order;import com.accenture.flowershop.shop.be.entity.Order;

import java.util.List;

/**
 * Интерфейс бизнес логики для сущности Order.
 */
public interface OrderBusinessService {
    /**
     * Закрыть заказ (присвоить статус CLOSED и добавить дату закрытия заказа).
     *
     * @param order - объект Order
     */
    void close(Order order);

    /**
     * Выбрать все заказы из базы данных
     *
     * @return список всех заказов
     */
    List<Order> getAll();

    /**
     * Найти заказ по идентификатору.
     *
     * @param id - идентификатор заказа
     * @return объект Order
     */
    Order get(Long id);

    /**
     * Обновить информацию о заказе.
     *
     * @param order - объект Order
     */
    void update(Order order);
}