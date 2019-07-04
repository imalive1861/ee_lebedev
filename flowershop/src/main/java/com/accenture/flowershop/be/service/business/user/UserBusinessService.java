package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface UserBusinessService {
    void saveUser(String login, String password, String name, String address,
                  String phoneNumber, BigDecimal score, int sale, String role);

    UserDTO getUserDTO(String login, String password);

    boolean uniqueLogin(String login);

    void setNewUser(String login, String password, String name,
                    String address, String phoneNumber);
}
