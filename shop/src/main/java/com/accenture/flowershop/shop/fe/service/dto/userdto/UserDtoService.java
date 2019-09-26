package com.accenture.flowershop.shop.fe.service.dto.userdto;

import com.accenture.flowershop.shop.be.entity.User;
import com.accenture.flowershop.shop.fe.dto.UserDTO;

import java.util.Map;

/**
 * Утилитарный класс транспортного уровня для сущности UserDTO.
 */
public interface UserDtoService {
    /**
     * Валидация данных.
     *
     * @param userDTO - объект UserDTO
     * @return карта возможных ошибок валидации
     */
    Map<String, String> dataValidation(UserDTO userDTO);

    /**
     * Переводит сущность User в UserDTO.
     *
     * @param user - объект User
     * @return объект UserDTO
     */
    UserDTO toDto(User user);

    /**
     * Переводит сущность UserDTO в User.
     *
     * @param userDTO - объект UserDTO
     * @return объект User
     */
    User fromDto(UserDTO userDTO);
}
