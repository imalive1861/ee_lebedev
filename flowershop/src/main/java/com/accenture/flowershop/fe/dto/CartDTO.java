package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

/**
 * Класс транспортного уровня, хранящий позиции пользовательской корзины покупок.
 * Свойства: id, number, order, flower, sumPrice.
 */
public class CartDTO {

    /**
     * Поле версии.
     */
    private Long version;

    /**
     * Иднтификатор позиции в корзине.
     */
    private Long id;

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
    private Integer number;

    /**
     * Суммарная цена за количество добавленных цветов без скидки.
     */
    private BigDecimal sumPriceWithoutDiscount;

    /**
     * Суммарная цена за количество добавленных цветов со скидкой.
     */
    private BigDecimal sumPriceWithDiscount;

    public CartDTO() {
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getSumPriceWithoutDiscount() {
        return sumPriceWithoutDiscount;
    }

    public void setSumPriceWithoutDiscount(BigDecimal sumPriceWithoutDiscount) {
        this.sumPriceWithoutDiscount = sumPriceWithoutDiscount;
    }

    public BigDecimal getSumPriceWithDiscount() {
        return sumPriceWithDiscount;
    }

    public void setSumPriceWithDiscount(BigDecimal sumPriceWithDiscount) {
        this.sumPriceWithDiscount = sumPriceWithDiscount;
    }

    public static class Builder {
        private CartDTO newCartDTO;

        public Builder() {
            newCartDTO = new CartDTO();
        }

        public Builder order(OrderDTO order) {
            newCartDTO.order = order;
            return this;
        }

        public Builder flower(FlowerDTO flower) {
            newCartDTO.flower = flower;
            return this;
        }

        public Builder number(Integer number) {
            newCartDTO.number = number;
            return this;
        }

        public Builder sumPriceWithoutDiscount(BigDecimal sumPriceWithoutDiscount) {
            newCartDTO.sumPriceWithoutDiscount = sumPriceWithoutDiscount;
            return this;
        }

        public Builder sumPriceWithDiscount(BigDecimal sumPriceWithDiscount) {
            newCartDTO.sumPriceWithDiscount = sumPriceWithDiscount;
            return this;
        }

        public CartDTO build() {
            return newCartDTO;
        }
    }
}
