package com.accenture.flowershop.be.entity;

import javax.persistence.*;

@Entity
@Table(name = "CARD")
@NamedQuery(name = "Card.getAll", query = "SELECT c from Card c")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="orderId", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="flowerId", nullable = false)
    private Flower flower;

    @Column
    private int number;

    public Card(){}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
