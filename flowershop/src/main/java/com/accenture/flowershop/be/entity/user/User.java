package com.accenture.flowershop.be.entity.user;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "USERS")
@NamedQuery(name = "User.getAll", query = "SELECT c from User c")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "score")
    private BigDecimal score;

    @Column(name = "sale")
    private int sale;

    @Column(name = "role")
    private String role;

    public User(){
    }

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

    public BigDecimal getScore() {
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

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
