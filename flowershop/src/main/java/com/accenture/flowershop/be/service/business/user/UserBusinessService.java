package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;

import java.math.BigDecimal;

public interface UserBusinessService {
    User logIn(String login, String password);
    void update(User user);
    void createOrder(User user, BigDecimal allSum, Order order);
    boolean isUniqueLogin(User user);
    void save(User user);
    User getByLogin(String login);
    User get(User user);
    boolean checkCash(User user, BigDecimal sumPrice);
}
