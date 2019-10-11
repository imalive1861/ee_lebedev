package com.accenture.flowershop.discount.service.business;

import com.accenture.flowershop.discount.entity.Discount;
import com.accenture.flowershop.discount.service.jms.Consumer;
import com.accenture.flowershop.discount.service.marshalling.DiscountMarshallingService;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class ConsumerStartThread extends Thread {

    @Override
    public void run() {
        try {
            String text = Consumer.consumer();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
            Document document = builder.parse(input);
            document.getDocumentElement().normalize();

            Random random = new Random();
            int d = random.nextInt(10);
            String customerId = document.getElementsByTagName("login").item(0).getTextContent();
            Discount discount = new Discount(customerId, d);
            System.out.println("Received object '" + discount + "' " +
                    " with login: " + customerId + " and discount: " + d);
            String xml = DiscountMarshallingService.marshaller(discount);
            ProducerStartThread thread = new ProducerStartThread(xml);
            thread.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
