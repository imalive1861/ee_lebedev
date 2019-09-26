package com.accenture.flowershop.shop.be.entity;

import com.accenture.flowershop.shop.fe.enums.OrderStatus;import com.accenture.flowershop.shop.fe.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
     * Суммарная цена за все позиции заказа со скидкой.
     */
    @Column(name = "sum_price_without_discount")
    private BigDecimal sumPriceWithoutDiscount;

    /**
     * Суммарная цена за все позиции заказа без скидки.
     */
    @Column(name = "sum_price_with_discount")
    private BigDecimal sumPriceWithDiscount;

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
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Позиции корзины, относящиеся к данному заказу.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private Set<Cart> carts = new HashSet<>();

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
