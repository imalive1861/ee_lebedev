package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.be.entity.User;

import java.math.BigDecimal;

public interface UserBusinessService {

    User logIn(String login, String password);

    boolean isUniqueLogin(User user);

    void save(String login, String password, String name,
              String address, String phoneNumber);

    User getByLogin(String login);
    User get(User user);

    BigDecimal checkScore(User user, BigDecimal sumPrice);
}
