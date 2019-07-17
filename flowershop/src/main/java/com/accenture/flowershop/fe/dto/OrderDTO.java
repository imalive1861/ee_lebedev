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

    public OrderDTO(BigDecimal sumPrice, LocalDate dateCreate, LocalDate dateClose, String status) {
        this.sumPrice = sumPrice;
        this.dateCreate = dateCreate;
        this.dateClose = dateClose;
        this.status = status;
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
