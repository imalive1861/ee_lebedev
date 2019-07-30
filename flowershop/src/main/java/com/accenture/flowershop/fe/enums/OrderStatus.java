package com.accenture.flowershop.fe.enums;

public enum OrderStatus {
    OPENED ("OPENED"),
    PAID ("PAID"),
    CLOSED ("CLOSED");

    private String title;

    OrderStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
