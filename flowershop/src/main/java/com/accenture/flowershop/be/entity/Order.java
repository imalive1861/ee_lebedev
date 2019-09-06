package com.accenture.flowershop.be.entity;

import com.accenture.flowershop.fe.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Класс, хранящий информацию о заказе.
 * Свойства: id, sumPrice, dateCreate, dateClose, status, userId.
 */
@Entity
@Table(name = "ORDERS")
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
    @SequenceGenerator(name = "ordersSeq", sequenceName = "ORDERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordersSeq")
    private Long id;

    /**
     * Суммарная цена за все позиции заказа.
     */
    @Column(name = "sum_price")
    private BigDecimal sumPrice;

    /**
     * Дата создания заказа покупателем.
     */
    @Column(name = "date_create")
    private Date dateCreate;

    /**
     * Дата закрытия заказа администратором.
     */
    @Column(name = "date_close")
    private Date dateClose;

    /**
     * Статус заказа.
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /**
     * Покупатель, который создал заказ.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Позиции корзины, относящиеся к данному заказу.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Cart> carts = new ArrayList<>();

    public Order() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public static class Builder {
        private Order newOrder;

        public Builder() {
            newOrder = new Order();
        }

        public Builder sumPrice(BigDecimal sumPrice) {
            newOrder.sumPrice = sumPrice;
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

        public Builder carts(List<Cart> carts) {
            newOrder.carts = carts;
            return this;
        }

        public Order build() {
            return newOrder;
        }
    }
}
