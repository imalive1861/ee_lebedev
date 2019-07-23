package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderDTO {

    private long id;
    private UserDTO userId;
    private BigDecimal sumPrice;
    private LocalDate dateCreate;
    private LocalDate dateClose;
    private String status;

    public OrderDTO(){}

    public OrderDTO(long id, UserDTO userId, BigDecimal sumPrice,
                    LocalDate dateCreate, LocalDate dateClose, String status) {
        this.id = id;
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

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }
    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateClose(LocalDate dateClose) {
        this.dateClose = dateClose;
    }
    public LocalDate getDateClose() {
        return dateClose;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
