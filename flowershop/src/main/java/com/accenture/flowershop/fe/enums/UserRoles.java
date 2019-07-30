package com.accenture.flowershop.fe.enums;

public enum UserRoles {
    ADMIN ("ADMIN"),
    CUSTOMER ("CUSTOMER");

    private String title;

    UserRoles(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}