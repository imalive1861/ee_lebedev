package com.accenture.flowershop.fe.servicedto.userdto;

import com.accenture.flowershop.fe.dto.UserDTO;

public interface UserService {
    String loginValidation(UserDTO userDTO);
    String passwordValidation(UserDTO userDTO);
}
