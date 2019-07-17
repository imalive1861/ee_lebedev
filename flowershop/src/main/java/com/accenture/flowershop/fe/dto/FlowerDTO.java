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
}
