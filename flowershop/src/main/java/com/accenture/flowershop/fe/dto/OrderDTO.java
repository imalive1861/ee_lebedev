package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.fe.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     * Суммарная цена за все позиции заказа.
     */
    private BigDecimal sumPrice;

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
    private List<CartDTO> carts = new ArrayList<>();

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

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
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

    public void setCarts(List<CartDTO> carts) {
        this.carts = carts;
    }

    public List<CartDTO> getCarts() {
        return carts;
    }

    public static class Builder {
        private OrderDTO newOrderDTO;

        public Builder() {
            newOrderDTO = new OrderDTO();
        }

        public Builder sumPrice(BigDecimal sumPrice) {
            newOrderDTO.sumPrice = sumPrice;
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

        public Builder userId(UserDTO userId) {
            newOrderDTO.user = userId;
            return this;
        }

        public Builder carts(List<CartDTO> carts) {
            newOrderDTO.carts = carts;
            return this;
        }

        public OrderDTO build() {
            return newOrderDTO;
        }
    }
}
