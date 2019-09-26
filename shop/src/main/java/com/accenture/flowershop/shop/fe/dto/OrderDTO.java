package com.accenture.flowershop.shop.fe.dto;

import com.accenture.flowershop.shop.fe.enums.OrderStatus;
import com.accenture.flowershop.shop.fe.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс транспортного уровня, хранящий информацию о заказе.
 * Свойства: id, sumPrice, dateCreate, dateClose, status, userId.
 */
public class OrderDTO {

    /**
     * Поле версии.
     */
    private Long version;

    /**
     * Иднтификатор заказа.
     */
    private Long id;

    /**
     * Покупатель, который создал заказ.
     */
    private UserDTO user;

    /**
     * Суммарная цена за все позиции заказа со скидкой.
     */
    private BigDecimal sumPriceWithoutDiscount;

    /**
     * Суммарная цена за все позиции заказа без скидки.
     */
    private BigDecimal sumPriceWithDiscount;

    /**
     * Дата создания заказа покупателем.
     */
    private Date dateCreate;

    /**
     * Дата закрытия заказа администратором.
     */
    private Date dateClose;

    /**
     * Статус заказа.
     */
    private OrderStatus status;

    /**
     * Позиции корзины, относящиеся к данному заказу.
     */
    private Set<CartDTO> carts = new HashSet<>();

    public OrderDTO() {
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public void setSumPriceWithoutDiscount(BigDecimal sumPriceWithoutDiscount) {
        this.sumPriceWithoutDiscount = sumPriceWithoutDiscount;
    }

    public BigDecimal getSumPriceWithoutDiscount() {
        return sumPriceWithoutDiscount;
    }

    public void setSumPriceWithDiscount(BigDecimal sumPriceWithDiscount) {
        this.sumPriceWithDiscount = sumPriceWithDiscount;
    }

    public BigDecimal getSumPriceWithDiscount() {
        return sumPriceWithDiscount;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }

    public Date getDateClose() {
        return dateClose;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setCarts(Set<CartDTO> carts) {
        this.carts = carts;
    }

    public Set<CartDTO> getCarts() {
        return carts;
    }

    public static class Builder {
        private OrderDTO newOrderDTO;

        public Builder() {
            newOrderDTO = new OrderDTO();
        }

        public Builder sumPriceWithoutDiscount(BigDecimal sumPriceWithoutDiscount) {
            newOrderDTO.sumPriceWithoutDiscount = sumPriceWithoutDiscount;
            return this;
        }

        public Builder sumPriceWithDiscount(BigDecimal sumPriceWithDiscount) {
            newOrderDTO.sumPriceWithDiscount = sumPriceWithDiscount;
            return this;
        }

        public Builder dateCreate(Date dateCreate) {
            newOrderDTO.dateCreate = dateCreate;
            return this;
        }

        public Builder dateClose(Date dateClose) {
            newOrderDTO.dateClose = dateClose;
            return this;
        }

        public Builder status(OrderStatus status) {
            newOrderDTO.status = status;
            return this;
        }

        public Builder user(UserDTO user) {
            newOrderDTO.user = user;
            return this;
        }

        public Builder carts(Set<CartDTO> carts) {
            newOrderDTO.carts = carts;
            return this;
        }

        public OrderDTO build() {
            return newOrderDTO;
        }
    }
}
