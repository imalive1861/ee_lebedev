package com.accenture.flowershop.be.entity.user;

public class User {
    private int id;
    private String login;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
