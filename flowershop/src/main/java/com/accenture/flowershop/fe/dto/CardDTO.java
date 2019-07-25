package com.accenture.flowershop.fe.dto;

public class CardDTO {

    private long id;
    private OrderDTO order;
    private FlowerDTO flower;
    private int number;

    public CardDTO(){}

    public CardDTO(OrderDTO order, FlowerDTO flower, int number){
        this.order = order;
        this.flower = flower;
        this.number = number;
    }

    public long getId() {
        return id;
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
}
