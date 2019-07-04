package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserBusinessService {

    UserDTO getUserDTO(String login, String password);

    boolean uniqueLogin(String login);

    void saveNewUser(String login, String password, String name,
                     String address, String phoneNumber);
}
