package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.be.entity.User;

import java.math.BigDecimal;

public interface UserBusinessService {

    User logIn(String login, String password);

    boolean isUniqueLogin(User user);

    void save(User user);

    User getByLogin(String login);
    User get(User user);

    BigDecimal checkScore(User user, BigDecimal sumPrice);
}
