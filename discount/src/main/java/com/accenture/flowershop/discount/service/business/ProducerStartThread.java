package com.accenture.flowershop.discount.service.business;

import com.accenture.flowershop.discount.service.jms.Producer;

import javax.jms.JMSException;

public class ProducerStartThread extends Thread {

    private String xml;

    ProducerStartThread(String xml) {
        this.xml = xml;
    }

    @Override
    public void run() {
        try {
            Producer.producer(xml);
            ConsumerStartThread thread = new ConsumerStartThread();
            thread.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
