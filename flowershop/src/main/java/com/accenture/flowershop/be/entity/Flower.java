package com.accenture.flowershop.be.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "FLOWERS")
@NamedQuery(name = "Flower.getAll", query = "SELECT c from Flower c")
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "number")
    private int number;

    public Flower(){}

    public Flower(String name, BigDecimal price, int number){
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
