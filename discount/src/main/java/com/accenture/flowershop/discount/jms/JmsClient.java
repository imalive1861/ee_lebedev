package com.accenture.flowershop.discount.jms;

public class JmsClient {

    public static void main(String[] args) {
        ConsumerStartThread thread = new ConsumerStartThread();
        thread.start();
    }
}