package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface UserBusinessService {

    UserDTO logIn(String login, String password);

    boolean uniqueLogin(UserDTO userDTO);

    void saveNewUser(String login, String password, String name,
                     String address, String phoneNumber);

    UserDTO get(String login);
    User get(UserDTO userDTO);

    BigDecimal checkScore(UserDTO userDTO, BigDecimal sumPrice);
}
