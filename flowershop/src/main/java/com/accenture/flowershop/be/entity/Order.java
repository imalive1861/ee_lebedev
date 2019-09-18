package com.accenture.flowershop.be.entity;

import com.accenture.flowershop.fe.enums.OrderStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс, хранящий информацию о заказе.
 * Свойства: id, sumPrice, dateCreate, dateClose, status, userId.
 */
@Document(collection = "orders")
public class Order {

    /**
     * Поле версии.
     */
    @Version
    private Long version;

    /**
     * Иднтификатор заказа.
     */
    @Id
    private String id;

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
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /**
     * Покупатель, который создал заказ.
     */
    @DBRef
    private User user;

    /**
     * Позиции корзины, относящиеся к данному заказу.
     */
    @Transient
    private Set<Cart> carts = new HashSet<>();

    public Order() {
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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateClose() {
        return dateClose;
    }

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public static class Builder {
        private Order newOrder;

        public Builder() {
            newOrder = new Order();
        }

        public Builder sumPriceWithoutDiscount(BigDecimal sumPriceWithoutDiscount) {
            newOrder.sumPriceWithoutDiscount = sumPriceWithoutDiscount;
            return this;
        }

        public Builder sumPriceWithDiscount(BigDecimal sumPriceWithDiscount) {
            newOrder.sumPriceWithDiscount = sumPriceWithDiscount;
            return this;
        }

        public Builder dateCreate(Date dateCreate) {
            newOrder.dateCreate = dateCreate;
            return this;
        }

        public Builder dateClose(Date dateClose) {
            newOrder.dateClose = dateClose;
            return this;
        }

        public Builder status(OrderStatus status) {
            newOrder.status = status;
            return this;
        }

        public Builder user(User user) {
            newOrder.user = user;
            return this;
        }

        public Builder carts(Set<Cart> carts) {
            newOrder.carts = carts;
            return this;
        }

        public Order build() {
            return newOrder;
        }
    }
}
