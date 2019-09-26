package com.accenture.flowershop.shop.be.service.marshgalling.user;

import com.accenture.flowershop.shop.be.entity.User;
import com.accenture.flowershop.shop.be.entity.User;

import java.io.IOException;

/**
 * Интерфейс маршалинга сущности User.
 */
public interface UserMarshallingService {
    /**
     * Маршалинг сущности User.
     *
     * @param user - сущность User
     * @throws IOException - если путь некорректен
     */
    void marshallingObjectToXML(User user) throws IOException;

    /**
     * Демаршалинг сущности User.
     *
     * @return сущность User
     * @throws IOException - если путь некорректен
     */
    User marshallingXMLToObject() throws IOException;

    /**
     * Функция установки пути для маршалируемого файла.
     *
     * @param userXmlPath - путь для маршалируемого файла
     */
    void setUserXmlPath(String userXmlPath);
}
