package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class FlowerDTO {

    private long id;
    private String name;
    private BigDecimal price;
    private int number;

    public FlowerDTO(){}

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
        private FlowerDTO newFlowerDTO;

        public Builder() {
            newFlowerDTO = new FlowerDTO();
        }

        public Builder name(String name){
            newFlowerDTO.name = name;
            return this;
        }

        public Builder price(BigDecimal price){
            newFlowerDTO.price = price;
            return this;
        }

        public Builder number(int number){
            newFlowerDTO.number = number;
            return this;
        }

        public FlowerDTO build(){
            return newFlowerDTO;
        }
    }
}
