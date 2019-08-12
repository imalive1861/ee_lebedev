package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.fe.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDTO {

    private long id;
    private UserDTO userId;
    private BigDecimal sumPrice;
    private Date dateCreate;
    private Date dateClose;
    private OrderStatus status;

    public OrderDTO(){}

    public OrderDTO(UserDTO userId, BigDecimal sumPrice,
                    Date dateCreate, Date dateClose, OrderStatus status) {
        this.userId = userId;
        this.sumPrice = sumPrice;
        this.dateCreate = dateCreate;
        this.dateClose = dateClose;
        this.status = status;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public UserDTO getUserId() {
        return userId;
    }
    public void setUserId(UserDTO user) {
        this.userId = user;
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
}
