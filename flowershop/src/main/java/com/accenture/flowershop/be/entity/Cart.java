package com.accenture.flowershop.be.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Класс, хранящий позиции пользовательской корзины покупок.
 * Свойства: id, number, order, flower, sumPrice.
 */
@Document(collection = "carts")
public class Cart {

    /**
     * Поле версии.
     */
    @Version
    private Long version;

    /**
     * Иднтификатор позиции в корзине.
     */
    @Id
    private String id;

    /**
     * Количество цветов, добавленных в корзину.
     */
    private Integer number;

    /**
     * Заказ, в который входит данная позиция.
     */
    @DBRef(lazy = true)
    private Order order;

    /**
     * Цветок, добавленный в корзину.
     */
    @DBRef(lazy = true)
    private Flower flower;

    /**
     * Суммарная цена за количество добавленных цветов без скидки.
     */
    private BigDecimal sumPriceWithoutDiscount;

    /**
     * Суммарная цена за количество добавленных цветов со скидкой.
     */
    private BigDecimal sumPriceWithDiscount;

    public Cart() {
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
        private Cart newCart;

        public Builder() {
            newCart = new Cart();
        }

        public Builder order(Order order) {
            newCart.order = order;
            return this;
        }

        public Builder flower(Flower flower) {
            newCart.flower = flower;
            return this;
        }

        public Builder number(Integer number) {
            newCart.number = number;
            return this;
        }

        public Builder sumPriceWithoutDiscount(BigDecimal sumPriceWithoutDiscount) {
            newCart.sumPriceWithoutDiscount = sumPriceWithoutDiscount;
            return this;
        }

        public Builder sumPriceWithDiscount(BigDecimal sumPriceWithDiscount) {
            newCart.sumPriceWithDiscount = sumPriceWithDiscount;
            return this;
        }

        public Cart build() {
            return newCart;
        }
    }
}
