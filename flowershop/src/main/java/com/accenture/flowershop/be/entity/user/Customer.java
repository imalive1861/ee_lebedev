package com.accenture.flowershop.be.entity.user;

public class Customer extends User {
    private String name;
    private String address;
    private String phoneNumber;
    private double score;
    private int sale;

    Customer(String login, String password, String name, String address, String phoneNumber, double score, int sale){
        super.setLogin(login);
        super.setPassword(password);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.score = score;
        this.sale = sale;
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
