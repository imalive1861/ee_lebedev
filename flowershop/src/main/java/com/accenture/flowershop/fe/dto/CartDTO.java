package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class CartDTO {

    private long id;
    private OrderDTO order;
    private FlowerDTO flower;
    private int number;
    private BigDecimal sumPrice;

    public CartDTO(){}

    public CartDTO(OrderDTO order, FlowerDTO flower, int number){
        this.order = order;
        this.flower = flower;
        this.number = number;
    }

    public CartDTO(OrderDTO order, FlowerDTO flower, int number, BigDecimal sumPrice){
        this.order = order;
        this.flower = flower;
        this.number = number;
        this.sumPrice = sumPrice;
    }

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
}
