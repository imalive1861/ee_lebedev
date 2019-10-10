package com.accenture.flowershop.discount.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ProducerStartThread extends Thread {

    private Discount discount;

    ProducerStartThread(Discount discount) {
        this.discount = discount;
    }

    @Override
    public void run() {
        try {
            producer(discount);
            ConsumerStartThread thread = new ConsumerStartThread();
            thread.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private static void producer(Discount discount) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
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
}
