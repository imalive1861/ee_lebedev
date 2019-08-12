package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.dto.UserDTO;

import java.math.BigDecimal;

public interface UserBusinessService {

    UserDTO logIn(String login, String password);

    boolean isUniqueLogin(UserDTO userDTO);

    void save(String login, String password, String name,
              String address, String phoneNumber);

    UserDTO get(String login);
    User get(UserDTO userDTO);

    BigDecimal checkScore(UserDTO userDTO, BigDecimal sumPrice);
}
