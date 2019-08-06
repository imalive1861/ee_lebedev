package com.accenture.flowershop.services.jms;

import java.io.Serializable;

public class Sale implements Serializable {
    private String customerId;
    private int sale;

    public Sale() {
    }

    public Sale(String customerId, int sale){
        this.customerId = customerId;
        this.sale = sale;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerId() {
        return customerId;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }
    public int getSale() {
        return sale;
    }
}
