package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class CardDTO {

    private long flowerId;
    private String flowerName;
    private int number;
    private BigDecimal sumPrice;

    public CardDTO(long flowerId, String flowerName, int number, BigDecimal sumPrice){
        this.flowerId = flowerId;
        this.flowerName = flowerName;
        this.number = number;
        this.sumPrice = sumPrice;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }
    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public long getFlowerId() {
        return flowerId;
    }
    public void setFlowerId(long flowerId) {
        this.flowerId = flowerId;
    }

    public String getFlowerName() {
        return flowerName;
    }
    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
