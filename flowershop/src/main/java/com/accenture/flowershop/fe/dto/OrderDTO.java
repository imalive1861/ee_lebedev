package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderDTO {

    private long id;
    private BigDecimal sumPrice;
    private LocalDate dateCreate;
    private LocalDate dateClose;
    private String status;

    public OrderDTO(){}

    public OrderDTO(long id, BigDecimal sumPrice, LocalDate dateCreate, LocalDate dateClose, String status) {
        this.id = id;
        this.sumPrice = sumPrice;
        this.dateCreate = dateCreate;
        this.dateClose = dateClose;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDateClose() {
        return dateClose;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setDateClose(LocalDate dateClose) {
        this.dateClose = dateClose;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }
}
