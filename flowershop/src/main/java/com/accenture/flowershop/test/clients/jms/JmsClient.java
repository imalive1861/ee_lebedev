package com.accenture.flowershop.test.clients.jms;

public class JmsClient {

    public static void main(String[] args) {
        ConsumerStartThread thread = new ConsumerStartThread();
        thread.start();
    }
}