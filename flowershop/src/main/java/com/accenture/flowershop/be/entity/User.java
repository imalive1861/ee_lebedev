package com.accenture.flowershop.be.entity;

import com.accenture.flowershop.fe.enums.UserRoles;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @SequenceGenerator( name = "usersSeq", sequenceName = "USERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersSeq")
    private long id;
    private String login;
    private String password;
    private String name;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    private BigDecimal cash;
    private int discount;
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    public User(){}

    public User(String login, String password, String name,
                String address, String phoneNumber, BigDecimal cash, int discount, UserRoles role){
        this.login = login;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cash = cash;
        this.discount = discount;
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

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }
    public BigDecimal getCash() {
        return cash;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public int getDiscount() {
        return discount;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
    public UserRoles getRole() {
        return role;
    }
}
