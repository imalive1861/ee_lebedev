package com.accenture.flowershop.be.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Класс, хранящий позиции пользовательской корзины покупок.
 * Свойства: id, number, order, flower, sumPrice.
 */
@Entity
@Table(name = "CARTS")
public class Cart {

    /**
     * Иднтификатор позиции в корзине.
     */
    @Id
    @SequenceGenerator( name = "cartsSeq", sequenceName = "CARTS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartsSeq")
    private long id;

    /**
     * Количество цветов, добавленных в корзину.
     */
    private int number;

    /**
     * Заказ, в который входит данная позиция.
     */
    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    /**
     * Цветок, добавленный в корзину.
     */
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="flower_id", nullable = false)
    private Flower flower;

    /**
     * Суммарная цена за количество добавленных цветов.
     */
    @Column(name = "sum_price")
    private BigDecimal sumPrice;

    public Cart(){}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
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

    public BigDecimal getSumPrice() {
        return sumPrice;
    }
    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public static class Builder {
        private Cart newCart;

        public Builder() {
            newCart = new Cart();
        }

        public Builder order(Order order){
            newCart.order = order;
            return this;
        }

        public Builder flower(Flower flower){
            newCart.flower = flower;
            return this;
        }

        public Builder number(int number){
            newCart.number = number;
            return this;
        }

        public Builder sumPrice(BigDecimal sumPrice){
            newCart.sumPrice = sumPrice;
            return this;
        }

        public Cart build(){
            return newCart;
        }
    }
}
