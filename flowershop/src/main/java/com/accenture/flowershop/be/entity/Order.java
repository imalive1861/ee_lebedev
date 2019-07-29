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

    @ManyToOne
    @JoinColumn(name = "userId_id", nullable = false)
    private User userId;

    @Column
    private BigDecimal sumPrice;

    @Column
    private LocalDate dateCreate;

    @Column
    private LocalDate dateClose;

    @Column
    private String status;

    public Order(){}

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public User getUserId() {
        return userId;
    }
    public void setUserId(User user) {
        this.userId = user;
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
