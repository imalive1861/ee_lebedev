package com.accenture.flowershop.services.jms;

import com.accenture.flowershop.fe.enums.JmsQueueNames;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Класс, использующийся для отправки сообщения через JMS.
 */
@Component
public class Prod {
    /**
     * Путь расположения XML файла отправляемого объекта.
     */
    private String destinationName;

    @Autowired
    public Prod(String destinationName) {
        this.destinationName = destinationName;
    }

    /**
     * Отправляет сообщение по JMS.
     *
     * @throws JMSException - возникает из-за ошибки в отправке сообщения
     */
    void producer() throws JMSException {
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(JmsQueueNames.OUT_QUEUE.name());
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage(readXML());
        producer.send(message);
        System.out.println("JCG printing@@ '" + message.getText() + "'");
        connection.close();
    }

    /**
     * Читает XML файл.
     *
     * @return текст, прочитанный из файла
     */
    private String readXML() {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(destinationName), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line);
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(text);
        return String.valueOf(text);
    }
}
