package com.accenture.flowershop.fe.dto;

import org.springframework.data.annotation.Version;

import java.math.BigDecimal;

/**
 * Класс транспортного уровня, хранящий позиции пользовательской корзины покупок.
 * Свойства: id, number, order, flower, sumPrice.
 */
public class CartDTO {

    /**
     * Поле версии.
     */
    private long version;

    /**
     * Иднтификатор позиции в корзине.
     */
    private long id;

    /**
     * Заказ, в который входит данная позиция.
     */
    private OrderDTO order;

    /**
     * Цветок, добавленный в корзину.
     */
    private FlowerDTO flower;

    /**
     * Количество цветов, добавленных в корзину.
     */
    private int number;

    /**
     * Суммарная цена за количество добавленных цветов.
     */
    private BigDecimal sumPrice;

    public CartDTO(){}

    public long getVersion() {
        return version;
    }
    public void setVersion(long version) {
        this.version = version;
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
