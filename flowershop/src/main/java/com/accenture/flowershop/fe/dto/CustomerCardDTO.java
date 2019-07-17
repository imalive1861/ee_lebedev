package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class CustomerCardDTO {

    private FlowerDTO flowerDTO;
    private int number;
    private BigDecimal sumPrice;

    public CustomerCardDTO(FlowerDTO flowerDTO, int number, BigDecimal sumPrice){
        this.flowerDTO = flowerDTO;
        this.number = number;
        this.sumPrice = sumPrice;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }
    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public FlowerDTO getFlowerDTO() {
        return flowerDTO;
    }
    public void setFlowerDTO(FlowerDTO flowerDTO) {
        this.flowerDTO = flowerDTO;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
