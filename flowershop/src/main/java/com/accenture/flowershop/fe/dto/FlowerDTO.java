package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс транспортного уровня, содержащий информацию о цветке.
 * Свойства: id, name, price, number, carts.
 */
public class FlowerDTO {

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
    private int number;

    /**
     * Позиции корзины, в которых собержится ссылка на данный цветок.
     */
    private List<CartDTO> carts = new ArrayList<>();

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

    public void setCarts(List<CartDTO> carts) {
        this.carts = carts;
    }

    public List<CartDTO> getCarts() {
        return carts;
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

        public Builder carts(List<CartDTO> carts) {
            newFlowerDTO.carts = carts;
            return this;
        }

        public FlowerDTO build() {
            return newFlowerDTO;
        }
    }
}
