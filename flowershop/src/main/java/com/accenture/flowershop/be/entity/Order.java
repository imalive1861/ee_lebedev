package com.accenture.flowershop.be.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "ORDERS")
@NamedQuery(name = "Order.getAll", query = "SELECT c from Order c")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private BigDecimal sumPrice;

    @Column
    private LocalDate dateCreate;

    @Column
    private LocalDate dateClose;

    @Column
    private String status;

    public Order(){}

    public Order(BigDecimal sumPrice, LocalDate dateCreate, LocalDate dateClose, String status) {
        this.sumPrice = sumPrice;
        this.dateCreate = dateCreate;
        this.dateClose = dateClose;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }
    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }
    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateClose(LocalDate dateClose) {
        this.dateClose = dateClose;
    }
    public LocalDate getDateClose() {
        return dateClose;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
