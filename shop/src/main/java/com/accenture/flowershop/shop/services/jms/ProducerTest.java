package com.accenture.flowershop.shop.services.jms;

import com.accenture.flowershop.shop.be.entity.User;
import com.accenture.flowershop.shop.be.service.marshgalling.user.UserMarshallingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.jms.JMSException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Класс, использующийся для контроля отправляемых и получаемых сообщений через JMS.
 */
@Component
public class ProducerTest {
    /**
     * Ссылка на класс маршалинга сущности User.
     */
    private UserMarshallingService userMarshallingService;
    /**
     * Ссылка на класс, управляющий получением сообщений через JMS.
     */
    private Prod prod;
    /**
     * Ссылка на класс, управляющий отправкой сообщений через JMS.
     */
    private Cons cons;

    @Autowired
    public ProducerTest(UserMarshallingService marshallingService,
                        Prod prod, Cons cons) {
        this.userMarshallingService = marshallingService;
        this.prod = prod;
        this.cons = cons;
    }

    /**
     * Запрашивает скидку для пользователя.
     *
     * @param user - объект User как запрос
     * @return объект User как ответ (уже с заданной скидкой)
     */
    public User saleRequest(User user) {
        try {
            //userMarshallingService.marshallingObjectToXML(user);
            String xml = userMarshallingService.convertFromObjectToXmlString(user);
            prod.producer(xml);
            String text = cons.consumer();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
            Document document = builder.parse(input);
            String customerId = document.getElementsByTagName("customerId").item(0).getTextContent();
            String discount = document.getElementsByTagName("discount").item(0).getTextContent();
            document.getDocumentElement().normalize();
            System.out.println(customerId + " : " + discount);
            if (user.getLogin().equals(customerId)) {
                user.setDiscount(Integer.parseInt(discount));
            }
        } catch (IOException | JMSException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return user;
    }
}
