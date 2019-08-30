package com.accenture.flowershop.be.repository.order;

import com.accenture.flowershop.be.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

/**
 * Интерфейс доступа к базе данных для сущности Order.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Сохранить или обновить данные потльзователя.
     * @param order - объект Order
     * @return объект Order
     */
    @Lock(LockModeType.OPTIMISTIC)
    Order saveAndFlush(Order order);
}
