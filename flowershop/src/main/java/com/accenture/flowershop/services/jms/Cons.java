package com.accenture.flowershop.services.jms;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class Cons {

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "IN_QUEUE";

    public Cons() {
    }

    public Object consumer() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(subject);
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
