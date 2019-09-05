package com.accenture.flowershop.be.entity;

import com.accenture.flowershop.fe.enums.UserRoles;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, хранящий информацию о пользователе.
 * Свойства: id, login, password, name, address,
 * phoneNumber, cash, discount, role, orders.
 */
@Entity
@Table(name = "USERS")
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
    @SequenceGenerator(name = "usersSeq", sequenceName = "USERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersSeq")
    private Long id;

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
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * Кошелек (количество средств, доступных для покупки товаров) пользователя.
     */
    private BigDecimal cash;

    /**
     * Предоставляемая магазином для пользователя скидка на товар.
     */
    private int discount;

    /**
     * Роль пользователя. (ADMIN или CUSTOMER)
     */
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    /**
     * Список заказов, сделанных пользователем.
     */
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public User() {
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
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

        public Builder discount(int discount) {
            newUser.discount = discount;
            return this;
        }

        public Builder role(UserRoles role) {
            newUser.role = role;
            return this;
        }

        public Builder orders(List<Order> orders) {
            newUser.orders = orders;
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}
