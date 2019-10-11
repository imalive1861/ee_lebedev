package com.accenture.flowershop.discount.service.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
    public static void producer(String xml) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("IN_QUEUE");
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage(xml);
        producer.send(message);
        System.out.println("JCG printing@@ '" + message.getText() + "'");
        connection.close();
    }
}
