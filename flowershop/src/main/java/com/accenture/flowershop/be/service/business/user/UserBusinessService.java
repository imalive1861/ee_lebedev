package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.be.entity.User;

/**
 * Интерфейс бизнес логики для сущности User.
 */
public interface UserBusinessService {
    /**
     * Авторизация пользователя.
     *
     * @param login    - логин
     * @param password - пароль
     * @return объект User
     */
    User logIn(String login, String password);

    /**
     * Обновить информацию о пользователе.
     *
     * @param user - объект User
     */
    void update(User user);

    /**
     * Проверить, существует ли пользователь с данным логином.
     *
     * @param login - логин потльзователя
     * @return true если существует, false если не существует
     */
    boolean existsByLogin(String login);

    /**
     * Сохранить нового пользователя.
     *
     * @param user - объект User
     */
    void save(User user);

    /**
     * Найти пользователя по логину.
     *
     * @param login - логин потльзователя
     * @return объект User если найден, null если не найден
     */
    User getByLogin(String login);
}
