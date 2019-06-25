package com.accenture.flowershop.be.entity.user;

public class User {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    void setLogin(String login) {
        this.login = login;
    }

    void setPassword(String password) {
        this.password = password;
    }
}
