package com.accenture.flowershop.be.entity;

import com.accenture.flowershop.fe.enums.UserRoles;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс, хранящий информацию о пользователе.
 * Свойства: id, login, password, name, address,
 * phoneNumber, cash, discount, role, orders.
 */
@Document(collection = "users")
public class User implements Serializable {

    /**
     * Поле версии.
     */
    @Version
    private Long version;

    /**
     * Иднтификатор пользователя.
     */
    @Id
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
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    /**
     * Список заказов, сделанных пользователем.
     */
    @Transient
    private Set<Order> orders = new HashSet<>();

    public User() {
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public static class Builder {
        private User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder login(String login) {
            newUser.login = login;
            return this;
        }

        public Builder password(String password) {
            newUser.password = password;
            return this;
        }

        public Builder name(String name) {
            newUser.name = name;
            return this;
        }

        public Builder address(String address) {
            newUser.address = address;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            newUser.phoneNumber = phoneNumber;
            return this;
        }

        public Builder cash(BigDecimal cash) {
            newUser.cash = cash;
            return this;
        }

        public Builder discount(Integer discount) {
            newUser.discount = discount;
            return this;
        }

        public Builder role(UserRoles role) {
            newUser.role = role;
            return this;
        }

        public Builder orders(Set<Order> orders) {
            newUser.orders = orders;
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}
