package com.accenture.flowershop.be.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, содержащий информацию о цветке.
 * Свойства: id, name, price, number, carts.
 */
@Entity
@Table(name = "FLOWERS")
public class Flower {

    /**
     * Поле версии.
     */
    @Version
    private Long version;

    /**
     * Иднтификатор цветка.
     */
    @Id
    @SequenceGenerator(name = "flowersSeq", sequenceName = "FLOWERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flowersSeq")
    private Long id;

    /**
     * Название цветка.
     */
    private String name;

    /**
     * Цена цветка.
     */
    private BigDecimal price;

    /**
     * Количество цветов.
     */
    private int number;

    /**
     * Позиции корзины, в которых собержится ссылка на данный цветок.
     */
    @OneToMany(mappedBy = "flower")
    private List<Cart> carts = new ArrayList<>();

    /**
     * Заказы, в которых содержится цветок.
     */
    @ManyToMany
    private List<Order> orders;

    public Flower() {
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public static class Builder {
        private Flower newFlower;

        public Builder() {
            newFlower = new Flower();
        }

        public Builder name(String name) {
            newFlower.name = name;
            return this;
        }

        public Builder price(BigDecimal price) {
            newFlower.price = price;
            return this;
        }

        public Builder number(int number) {
            newFlower.number = number;
            return this;
        }

        public Builder carts(List<Cart> carts) {
            newFlower.carts = carts;
            return this;
        }

        public Flower build() {
            return newFlower;
        }
    }
}
