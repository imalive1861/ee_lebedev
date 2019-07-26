package com.accenture.flowershop.be.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "USERS")
@NamedQuery(name = "User.getAll", query = "SELECT c from User c")
public class User {

    @Id
    private long id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @Column
    private BigDecimal score;

    @Column
    private int sale;

    @Column
    private String role;

    public User(){}

    public User(String login, String password, String name,
                String address, String phoneNumber, BigDecimal score, int sale, String role){
        this.login = login;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.score = score;
        this.sale = sale;
        this.role = role;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
    public BigDecimal getScore() {
        return score;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }
    public int getSale() {
        return sale;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}