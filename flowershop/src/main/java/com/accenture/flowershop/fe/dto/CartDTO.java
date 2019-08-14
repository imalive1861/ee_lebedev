package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class CartDTO {

    private long id;
    private OrderDTO order;
    private FlowerDTO flower;
    private int number;
    private BigDecimal sumPrice;

    public CartDTO(){}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public FlowerDTO getFlower() {
        return flower;
    }
    public void setFlower(FlowerDTO flower) {
        this.flower = flower;
    }

    public OrderDTO getOrder() {
        return order;
    }
    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }
    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public static class Builder {
        private CartDTO newCartDTO;

        public Builder() {
            newCartDTO = new CartDTO();
        }

        public Builder order(OrderDTO order){
            newCartDTO.order = order;
            return this;
        }
        public Builder flower(FlowerDTO flower){
            newCartDTO.flower = flower;
            return this;
        }
        public Builder number(int number){
            newCartDTO.number = number;
            return this;
        }
        public Builder sumPrice(BigDecimal sumPrice){
            newCartDTO.sumPrice = sumPrice;
            return this;
        }

        public CartDTO build(){
            return newCartDTO;
        }
    }
}
