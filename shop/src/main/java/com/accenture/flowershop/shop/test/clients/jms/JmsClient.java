package com.accenture.flowershop.shop.test.clients.jms;

public class JmsClient {

    public static void main(String[] args) {
        ConsumerStartThread thread = new ConsumerStartThread();
        thread.start();
    }
}