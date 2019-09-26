package com.accenture.flowershop.be.service.business.cart;

import com.accenture.flowershop.be.entity.Cart;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.Order;

import java.math.BigDecimal;
import java.util.Map;

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

    /**
     * Получить карту заказов пользователей.
     *
     * @return карта заказов пользователей
     */
    Map<String, Order> getCart();

    /**
     * Получить текущий заказ пользователя.
     *
     * @param login - логин пользователя
     * @return объект Order
     */
    Order getCartById(String login);

    /**
     * Рассчет суммы позиции с учетом скидки пользователя.
     *
     * @param price  - сумма позиции
     * @param sale   - скидка пользователя
     * @param number - количество заказанных цветов
     * @return сумма позиции с учетом скидки пользователя
     */
    BigDecimal getSumPriceWithDiscount(BigDecimal price, int sale, int number);

    /**
     * Рассчет суммы заказа пользователя.
     *
     * @param order - объект Order
     */
    void countAllSumPrice(Order order);

    /**
     * Добавляет новую позицию в заказе.
     *
     * @param login  - логин пользователя
     * @param flower - объект Flower
     * @param number - количество цветов
     * @return true - если позиция добавлена, false - если позиция не добавлена
     */
    boolean isAddFlowerToCart(String login, Flower flower, int number);

    /**
     * Создает новый временный заказ для пользователя.
     *
     * @param login - логин пользователя
     */
    void setCart(String login);

    /**
     * Очищает текущий заказ пользователя.
     *
     * @param login - логин пользователя
     */
    void clear(String login);

    /**
     * Удаляет позицию из корзины.
     *
     * @param cartId - идентификатор позиции
     * @param login  - логин пользователя
     */
    void deleteFlowerFromCart(Long cartId, String login);
}
