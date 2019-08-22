package com.accenture.flowershop.be.repository.user;

import com.accenture.flowershop.be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс доступа к базе данных для сущности User.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Найти пользователя по логину.
     * @param login - логин пользователя
     * @return объект User если найден, null если не найден
     */
    User getByLogin(String login);
    /**
     * Проверить, существует ли пользователь с данным логином.
     * @param login - логин потльзователя
     * @return true если существует, false если не существует
     */
    boolean existsByLogin(String login);
    /**
     * Найти пользователя по комбинации логина и пароля.
     * @param login - логин пользователя
     * @param password - парольпользователя
     * @return объект User если найден, null если не найден
     */
    User getByLoginAndPassword(String login, String password);
}
