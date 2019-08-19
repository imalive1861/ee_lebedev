package com.accenture.flowershop.be.service.marshgalling.user;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.utils.marshalling.XMLConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Реализация интерфейса UserMarshallingService.
 */
@Service
public class UserMarshallingServiceImpl implements UserMarshallingService {

    /**
     * Путь для маршалируемого файла.
     */
    private String userXmlPath;

    /**
     * Ссылка на конвертер для маршалинга в/из XML.
     */
    private XMLConverter converter;

    /**
     * Конструктор - создание нового объекта с определенными значениями.
     * @param converter - ссылка на конвертер для маршалинга в/из XML
     */
    @Autowired
    public UserMarshallingServiceImpl(XMLConverter converter) {
        this.converter = converter;
    }

    @Override
    public void marshallingObjectToXML(User user) throws IOException {
        converter.convertFromObjectToXML(user, userXmlPath);
    }

    @Override
    public User marshallingXMLToObject() throws IOException {
        return (User) converter.convertFromXMLToObject(userXmlPath);
    }

    @Override
    public void setUserXmlPath(String userXmlPath) {
        this.userXmlPath = userXmlPath;
    }
}
