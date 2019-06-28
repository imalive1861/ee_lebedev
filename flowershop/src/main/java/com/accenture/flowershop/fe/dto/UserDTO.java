package com.accenture.flowershop.fe.dto;

public class UserDTO {
    private String login;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
    private double score;
    private int sale;
    private String role;

    public UserDTO(){
    }

    public UserDTO(String login, String password, String name,
                   String address, String phoneNumber, double score, int sale, String role){
        this.login = login;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.score = score;
        this.sale = sale;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
