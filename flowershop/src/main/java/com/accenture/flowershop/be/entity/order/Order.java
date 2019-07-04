package com.accenture.flowershop.be.entity.order;

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

    @Column(name = "sumPrice")
    private BigDecimal sumPrice;

    @Column(name = "dateCreate")
    private LocalDate dateCreate;

    @Column(name = "dateClose")
    private LocalDate dateClose;

    @Column(name = "status")
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

    public LocalDate getDateClose() {
        return dateClose;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setDateClose(LocalDate dateClose) {
        this.dateClose = dateClose;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }
}
