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

    public static class Builder {
        private User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder login(String login){
            newUser.login = login;
            return this;
        }

        public Builder password(String password){
            newUser.password = password;
            return this;
        }
        public Builder name(String name){
            newUser.name = name;
            return this;
        }
        public Builder address(String address){
            newUser.address = address;
            return this;
        }
        public Builder phoneNumber(String phoneNumber){
            newUser.phoneNumber = phoneNumber;
            return this;
        }
        public Builder cash(BigDecimal cash){
            newUser.cash = cash;
            return this;
        }
        public Builder discount(int discount){
            newUser.discount = discount;
            return this;
        }
        public Builder role(UserRoles role){
            newUser.role = role;
            return this;
        }

        public User build(){
            return newUser;
        }
    }
}
