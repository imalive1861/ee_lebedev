package com.accenture.flowershop.be.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Класс, содержащий информацию о цветке.
 * Свойства: id, name, price, number, carts.
 */
@Entity
@Document(collection = "flowers")
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
    private String id;

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

    public Flower() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
