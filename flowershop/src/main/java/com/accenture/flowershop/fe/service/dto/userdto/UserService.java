package com.accenture.flowershop.fe.service.dto.userdto;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.dto.UserDTO;

import java.util.Map;

public interface UserService {
    String loginValidation(UserDTO userDTO);
    String passwordValidation(UserDTO userDTO);
    Map<String, String> dataValidation(UserDTO userDTO);
    UserDTO toDto(User user);
    User fromDto(UserDTO userDTO);
}
