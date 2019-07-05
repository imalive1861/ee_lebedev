package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class FlowerDTO {

    private long id;
    private String name;
    private BigDecimal price;
    private int number;

    public FlowerDTO(){}

    public FlowerDTO(long id, String name, BigDecimal price, int number) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
