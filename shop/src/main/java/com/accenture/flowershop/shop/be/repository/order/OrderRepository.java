package com.accenture.flowershop.shop.be.repository.order;

import com.accenture.flowershop.shop.be.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

/**
 * Интерфейс доступа к базе данных для сущности Order.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Сохранить или обновить данные потльзователя.
     *
     * @param order - объект Order
     * @return объект Order
     */
    @Lock(LockModeType.PESSIMISTIC_READ)
    Order saveAndFlush(Order order);
}
