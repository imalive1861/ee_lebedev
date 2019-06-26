package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.Customer;
import com.accenture.flowershop.fe.dto.UserDTO;

import java.util.Map;

public interface UserBusinessService {
    void saveUser(Customer user);

    Map<String, Customer> getAllUsers();

    Customer getByLogin(String login);

    Map<String, UserDTO> getAllUserAsUserDTO();

    public UserDTO getUserDTObyLogin(String login);
}
