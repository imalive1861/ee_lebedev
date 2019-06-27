package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.Customer;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserBusinessService {
    void saveUser(Customer user);

    Map<String, Customer> getAllUsers();

    Customer getByLogin(String login);

    Map<String, UserDTO> getAllUserAsUserDTO();

    UserDTO getUserDTO(String login, String password);

    boolean uniqueLogin(String login);

    int setMaxId();

    void setNewUser(UserDTO userDTO);
}
