package com.accenture.flowershop.shop.services.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Класс, использующийся для получения сообщения через JMS.
 */
@Component
public class Cons {

    public Cons() {
    }

    /**
     * Получает сообщение от JMS.
     *
     * @return - полученный объект
     * @throws JMSException - возникает из-за ошибки в получении сообщения
     */
    String consumer() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("IN_QUEUE");
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive();
        String text = null;
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            text = textMessage.getText();
            System.out.println("Received message '" + textMessage.getText() + "'");
        }
        connection.close();
        return text;
    }
}
