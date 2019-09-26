package com.accenture.flowershop.be.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс, содержащий информацию о цветке.
 * Свойства: id, name, price, number, carts.
 */
@Entity
@Table(name = "FLOWERS")
public class Flower implements Comparable {

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
    private Integer number;

    /**
     * Позиции корзины, в которых собержится ссылка на данный цветок.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flower")
    private Set<Cart> carts = new HashSet<>();

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

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    @Override
    public int compareTo(Object o) {
        Flower flower = (Flower) o;
        return this.name.compareTo(flower.name);
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

        public Builder number(Integer number) {
            newFlower.number = number;
            return this;
        }

        public Flower build() {
            return newFlower;
        }
    }
}
