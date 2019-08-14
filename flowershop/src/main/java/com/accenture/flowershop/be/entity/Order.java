package com.accenture.flowershop.be.entity;

import com.accenture.flowershop.fe.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @SequenceGenerator( name = "ordersSeq", sequenceName = "ORDERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordersSeq")
    private long id;
    @Column(name = "sum_price")
    private BigDecimal sumPrice;
    @Column(name = "date_create")
    private Date dateCreate;
    @Column(name = "date_close")
    private Date dateClose;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<Cart> carts;

    public Order(){}

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
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

    public User getUserId() {
        return userId;
    }
    public void setUserId(User user) {
        this.userId = user;
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

        public Builder sumPrice(BigDecimal sumPrice){
            newOrder.sumPrice = sumPrice;
            return this;
        }

        public Builder dateCreate(Date dateCreate){
            newOrder.dateCreate = dateCreate;
            return this;
        }

        public Builder dateClose(Date dateClose){
            newOrder.dateClose = dateClose;
            return this;
        }

        public Builder status(OrderStatus status){
            newOrder.status = status;
            return this;
        }

        public Builder userId(User userId){
            newOrder.userId = userId;
            return this;
        }

        public Builder carts(List<Cart> carts){
            newOrder.carts = carts;
            return this;
        }

        public Order build(){
            return newOrder;
        }
    }
}
