package com.accenture.flowershop.shop.be.entity;

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
     * Поле версии.
     */
    @Version
    private Long version;

    /**
     * Иднтификатор позиции в корзине.
     */
    @Id
    @SequenceGenerator(name = "cartsSeq", sequenceName = "CARTS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartsSeq")
    private Long id;

    /**
     * Количество цветов, добавленных в корзину.
     */
    private Integer number;

    /**
     * Заказ, в который входит данная позиция.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * Цветок, добавленный в корзину.
     */
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "flower_id", nullable = false)
    private Flower flower;

    /**
     * Суммарная цена за количество добавленных цветов без скидки.
     */
    @Column(name = "sum_price_without_discount")
    private BigDecimal sumPriceWithoutDiscount;

    /**
     * Суммарная цена за количество добавленных цветов со скидкой.
     */
    @Column(name = "sum_price_with_discount")
    private BigDecimal sumPriceWithDiscount;

    public Cart() {
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
