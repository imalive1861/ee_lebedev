package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.enums.UserRoles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private long id;
    private String login;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
    private BigDecimal cash;
    private int discount;
    private UserRoles role;
    private List<OrderDTO> orders = new ArrayList<>();

    public UserDTO(){}

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

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
    public List<OrderDTO> getOrders() {
        return orders;
    }

    public static class Builder {
        private UserDTO newUserDTO;

        public Builder() {
            newUserDTO = new UserDTO();
        }

        public Builder login(String login){
            newUserDTO.login = login;
            return this;
        }

        public Builder password(String password){
            newUserDTO.password = password;
            return this;
        }
        public Builder name(String name){
            newUserDTO.name = name;
            return this;
        }
        public Builder address(String address){
            newUserDTO.address = address;
            return this;
        }
        public Builder phoneNumber(String phoneNumber){
            newUserDTO.phoneNumber = phoneNumber;
            return this;
        }
        public Builder cash(BigDecimal cash){
            newUserDTO.cash = cash;
            return this;
        }
        public Builder discount(int discount){
            newUserDTO.discount = discount;
            return this;
        }
        public Builder role(UserRoles role){
            newUserDTO.role = role;
            return this;
        }
        public Builder orders(List<OrderDTO> orders){
            newUserDTO.orders = orders;
            return this;
        }

        public UserDTO build(){
            return newUserDTO;
        }
    }
}
