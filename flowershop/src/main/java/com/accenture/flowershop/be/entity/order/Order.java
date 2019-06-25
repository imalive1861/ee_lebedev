package com.accenture.flowershop.be.entity.order;

import java.util.Date;

public class Order {
    private Integer id;
    private double sumPrice;
    private Date dateCreate;
    private Date dateClose;
    private boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }
}
