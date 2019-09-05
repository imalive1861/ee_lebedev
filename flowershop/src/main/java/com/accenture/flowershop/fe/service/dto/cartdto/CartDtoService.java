package com.accenture.flowershop.fe.service.dto.cartdto;

import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;

/**
 * Класс транспортного уровня, который хранит и обрабатывает текущий заказ пользователя.
 */
public interface CartDtoService {
    /**
     * Добавляет новую позицию в заказе.
     *
     * @param userDTO   - объект UserDTO
     * @param flowerDTO - объект FlowerDTO
     * @param number    - количество цветов
     * @return true - если позиция добавлена, false - если позиция не добавлена
     */
    boolean isAddFlowerToCart(UserDTO userDTO, FlowerDTO flowerDTO, int number);

    /**
     * Очищает текущий заказ пользователя.
     *
     * @param login - логин пользователя
     * @return объект OrderDTO
     */
    OrderDTO clear(String login);

    /**
     * Создает новый временный заказ для пользователя.
     *
     * @param login - логин пользователя
     * @return объект OrderDTO
     */
    OrderDTO setCart(String login);
}
