package com.accenture.flowershop.discount;

import com.accenture.flowershop.discount.service.business.ConsumerStartThread;

public class StartApplication {

    public static void main(String[] args) {
        ConsumerStartThread thread = new ConsumerStartThread();
        thread.start();
    }
}