package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

/**
 * Класс транспортного уровня, содержащий информацию о цветке.
 * Свойства: id, name, price, number, carts.
 */
public class FlowerDTO implements Comparable {

    /**
     * Поле версии.
     */
    private Long version;

    /**
     * Иднтификатор цветка.
     */
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

    public FlowerDTO() {
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
        FlowerDTO flower = (FlowerDTO) o;
        return this.name.compareTo(flower.name);
    }

    public static class Builder {
        private FlowerDTO newFlowerDTO;

        public Builder() {
            newFlowerDTO = new FlowerDTO();
        }

        public Builder name(String name) {
            newFlowerDTO.name = name;
            return this;
        }

        public Builder price(BigDecimal price) {
            newFlowerDTO.price = price;
            return this;
        }

        public Builder number(Integer number) {
            newFlowerDTO.number = number;
            return this;
        }

        public FlowerDTO build() {
            return newFlowerDTO;
        }
    }
}
