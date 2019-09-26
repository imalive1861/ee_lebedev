package com.accenture.flowershop.services.jms;

import com.accenture.flowershop.fe.enums.JmsQueueNames;
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
    Object consumer() throws JMSException {
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(JmsQueueNames.IN_QUEUE.name());
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive();
        Object obj = null;
        if (message instanceof ObjectMessage) {
            ObjectMessage objMessage = (ObjectMessage) message;
            obj = objMessage.getObject();
            System.out.println("Received message '" + objMessage.getObject() + "'");
        }
        connection.close();
        return obj;
    }
}
