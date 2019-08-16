package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;

public interface UserBusinessService {
    User logIn(String login, String password);
    void update(User user);
    boolean isOrderCreated(User user, Order order);
    boolean isUniqueLogin(User user);
    void save(User user);
    User getByLogin(String login);
    User get(User user);
}
