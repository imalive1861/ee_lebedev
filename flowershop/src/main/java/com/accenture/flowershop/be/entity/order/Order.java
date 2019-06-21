package com.accenture.flowershop.be.entity.order;

import java.util.Date;

public class Order {
    private double sumPrice;
    private Date dateCreate;
    private Date dateClose;
    private boolean status;

    Order(double sumPrice, Date dateCreate, Date dateClose, boolean status){
        this.sumPrice = sumPrice;
        this.dateCreate = dateCreate;
        this.dateClose = dateClose;
        this.status = status;
    }

    public Date getDateClose() {
        return dateClose;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public boolean getStatus() {
        return status;
    }
}
