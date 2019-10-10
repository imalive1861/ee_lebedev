package com.accenture.flowershop.shop.services.jms;

import com.accenture.flowershop.shop.be.entity.User;
import com.accenture.flowershop.shop.be.service.marshgalling.user.UserMarshallingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.io.IOException;

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
            userMarshallingService.marshallingObjectToXML(user);
            prod.producer();
            Discount discount = cons.consumer();
            if (user.getLogin().equals(discount.getCustomerId())) {
                user.setDiscount(discount.getDiscount());
            }
        } catch (IOException | JMSException e) {
            e.printStackTrace();
        }
        return user;
    }
}
