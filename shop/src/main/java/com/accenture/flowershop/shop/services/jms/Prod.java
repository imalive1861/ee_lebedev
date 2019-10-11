package com.accenture.flowershop.shop.services.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Класс, использующийся для отправки сообщения через JMS.
 */
@Component
public class Prod {

    @Autowired
    public Prod() {
    }

    /**
     * Отправляет сообщение по JMS.
     *
     * @throws JMSException - возникает из-за ошибки в отправке сообщения
     */
    void producer(String xml) throws JMSException {
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("OUT_QUEUE");
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage(xml);
        producer.send(message);
        System.out.println("JCG printing@@ '" + message.getText() + "'");
        connection.close();
    }
}
