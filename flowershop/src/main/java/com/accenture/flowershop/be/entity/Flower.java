package com.accenture.flowershop.be.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "FLOWERS")
public class Flower {

    @Id
    @SequenceGenerator( name = "flowersSeq", sequenceName = "FLOWERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flowersSeq")
    private long id;
    private String name;
    private BigDecimal price;
    private int number;

    public Flower(){}

    public void setId(long id) {
        this.id = id;
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

    public static class Builder {
        private Flower newFlower;

        public Builder() {
            newFlower = new Flower();
        }

        public Builder name(String name){
            newFlower.name = name;
            return this;
        }

        public Builder price(BigDecimal price){
            newFlower.price = price;
            return this;
        }

        public Builder number(int number){
            newFlower.number = number;
            return this;
        }

        public Flower build(){
            return newFlower;
        }
    }
}
