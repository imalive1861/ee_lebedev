package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserAccess;
import com.accenture.flowershop.be.entity.user.Customer;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserBusinessServiceImpl implements UserBusinessService{

    @Autowired
    private UserAccess userAccess;

    public void saveUser(Customer user) {
        if (user != null) {
            Map<String,Customer> users = userAccess.getAllUsers();
            if (!users.isEmpty()) {
                userAccess.saveUser(user);
            }
        }
    }

    public Map<String, Customer> getAllUsers(){
        return userAccess.getAllUsers();
    }

    public Customer getByLogin(String login){
        if (login != null) {
            return userAccess.getByLogin(login);
        }
        return null;
    }

    private Map<String, UserDTO> userDTOs;

    public Map<String, UserDTO> getAllUserAsUserDTO() {
        userDTOs = new HashMap<>();

        for(Customer user : userAccess.getAllUsers().values()) {
            userDTOs.put(user.getLogin() ,constructUserDTO(user));
        }

        return userDTOs;
    }

    private UserDTO constructUserDTO(Customer user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(user.getLogin());
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setAddress(user.getAddress());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setScore(user.getScore());
        userDTO.setSale(user.getSale());
        userAccess.userAccessImpl123();
        return userDTO;
    }

    public UserDTO getUserDTObyLogin(String login) {

        return userDTOs.get(login);
    }
}
