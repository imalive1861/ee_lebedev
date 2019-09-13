package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
    private Set<CartDTO> carts = new HashSet<>();

    public FlowerDTO() {
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

    public void setCarts(Set<CartDTO> carts) {
        this.carts = carts;
    }

    public Set<CartDTO> getCarts() {
        return carts;
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

        public Builder carts(Set<CartDTO> carts) {
            newFlowerDTO.carts = carts;
            return this;
        }

        public FlowerDTO build() {
            return newFlowerDTO;
        }
    }
}
