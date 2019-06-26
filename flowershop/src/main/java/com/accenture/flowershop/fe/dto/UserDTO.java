package com.accenture.flowershop.fe.dto;

public class UserDTO {
    private int id;
    private String login;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
    private double score;
    private int sale;

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

    public double getScore() {
        return score;
    }

    public int getSale() {
        return sale;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
