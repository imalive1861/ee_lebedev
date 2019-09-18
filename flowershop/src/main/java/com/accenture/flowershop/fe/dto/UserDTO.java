package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.fe.enums.UserRoles;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс транспортного уровня, хранящий информацию о пользователе.
 * Свойства: id, login, password, name, address,
 * phoneNumber, cash, discount, role, orders.
 */
public class UserDTO {

    /**
     * Поле версии.
     */
    private Long version;

    /**
     * Иднтификатор пользователя.
     */
    private String id;

    /**
     * Логин для авторизации.
     */
    @NotNull(message = "Login cannot be empty!")
    @Size(min = 4, max = 20, message = "Login length minimum 4, maximum 20!")
    private String login;

    /**
     * Пароль для авторизации.
     */
    @NotNull(message = "Password cannot be empty!")
    @Size(min = 8, max = 20, message = "Password length minimum 8, maximum 20!")
    private String password;

    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Адрес пользователя.
     */
    private String address;

    /**
     * Номер телефона пользователя.
     */
    private String phoneNumber;

    /**
     * Кошелек (количество средств, доступных для покупки товаров) пользователя.
     */
    private BigDecimal cash;

    /**
     * Предоставляемая магазином для пользователя скидка на товар.
     */
    private Integer discount;

    /**
     * Роль пользователя. (ADMIN или CUSTOMER)
     */
    private UserRoles role;

    /**
     * Список заказов, сделанных пользователем.
     */
    private Set<OrderDTO> orders = new HashSet<>();

    public UserDTO() {
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public Set<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderDTO> orders) {
        this.orders = orders;
    }

    public static class Builder {
        private UserDTO newUserDTO;

        public Builder() {
            newUserDTO = new UserDTO();
        }

        public Builder login(String login) {
            newUserDTO.login = login;
            return this;
        }

        public Builder password(String password) {
            newUserDTO.password = password;
            return this;
        }

        public Builder name(String name) {
            newUserDTO.name = name;
            return this;
        }

        public Builder address(String address) {
            newUserDTO.address = address;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            newUserDTO.phoneNumber = phoneNumber;
            return this;
        }

        public Builder cash(BigDecimal cash) {
            newUserDTO.cash = cash;
            return this;
        }

        public Builder discount(Integer discount) {
            newUserDTO.discount = discount;
            return this;
        }

        public Builder role(UserRoles role) {
            newUserDTO.role = role;
            return this;
        }

        public Builder orders(Set<OrderDTO> orders) {
            newUserDTO.orders = orders;
            return this;
        }

        public UserDTO build() {
            return newUserDTO;
        }
    }
}
