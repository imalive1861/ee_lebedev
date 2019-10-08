package com.accenture.flowershop.discount.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.w3c.dom.Document;

import javax.jms.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class ConsumerStartThread extends Thread {

    @Override
    public void run() {
        try {
            String text = consumer();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
            Document document = builder.parse(input);
            String customerId = document.getDocumentElement().getAttribute("login");
            Random random = new Random();
            ProducerStartThread thread =
                    new ProducerStartThread(customerId, String.valueOf(random.nextInt(10)));
            thread.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String consumer() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
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
