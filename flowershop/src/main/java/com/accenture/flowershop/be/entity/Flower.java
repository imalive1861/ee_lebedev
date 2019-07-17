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

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column
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

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
}
