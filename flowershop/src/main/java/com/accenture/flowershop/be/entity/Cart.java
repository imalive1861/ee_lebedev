package com.accenture.flowershop.be.entity;

import javax.persistence.*;

@Entity
@Table(name = "CARTS")
public class Cart {

    @Id
    @SequenceGenerator( name = "cartsSeq", sequenceName = "USERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    private long id;
    private int number;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="flower_id", nullable = false)
    private Flower flower;

    public Cart(){}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public Flower getFlower() {
        return flower;
    }
    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
}
