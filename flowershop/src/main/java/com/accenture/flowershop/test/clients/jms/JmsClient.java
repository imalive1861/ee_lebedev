package com.accenture.flowershop.test.clients.jms;

import com.accenture.flowershop.be.service.marshgalling.user.UserMarshallingService;
import com.accenture.flowershop.services.jms.Discount;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;

import javax.jms.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class JmsClient {
    @Autowired
    private static UserMarshallingService userMarshallingService;

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        try {
            String text = consumer();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
            Document document = builder.parse(input);
            String customerId = document.getDocumentElement().getAttribute("login");
            Random random = new Random();
            Discount discount = new Discount(customerId, random.nextInt(10));
            producer(discount);
        } catch (Exception ej) {
            ej.printStackTrace();
            try {
                producer(null);
            } catch (JMSException ejs) {
                ejs.printStackTrace();
            }
        }
    }

    private static void producer(Discount discount) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("IN_QUEUE");
        MessageProducer producer = session.createProducer(destination);
        ObjectMessage message = session.createObjectMessage();
        message.setObject(discount);
        producer.send(message);
        System.out.println("JCG printing@@ '" + message.getObject() + "'");
        connection.close();
    }
    private static String consumer() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("OUT_QUEUE");
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
