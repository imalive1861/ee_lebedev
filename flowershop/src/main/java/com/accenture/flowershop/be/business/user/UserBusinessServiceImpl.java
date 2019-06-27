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

    private UserAccess userAccess;

    @Autowired
    public UserBusinessServiceImpl(UserAccess userAccess){
        this.userAccess = userAccess;
    }

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

        return userDTO;
    }

    public UserDTO getUserDTO(String login, String password) {
        UserDTO userDTO = userDTOs.get(login);
        if (userDTO.getPassword().equals(password)){
            return userDTO;
        }
        return null;
    }

    public boolean uniqueLogin(String login){
        return userDTOs.containsKey(login);
    }

    public int setMaxId(){
        int i = 0;
        for (UserDTO user: userDTOs.values()){
            if (user.getId() > i){
                i = user.getId();
            }
        }
        return i;
    }
    public void setNewUser(UserDTO userDTO){
        Customer customer = new Customer();
        customer.setLogin(userDTO.getLogin());
        customer.setId(userDTO.getId());
        customer.setName(userDTO.getName());
        customer.setAddress(userDTO.getAddress());
        customer.setPhoneNumber(userDTO.getPhoneNumber());
        customer.setScore(userDTO.getScore());
        customer.setSale(userDTO.getSale());
        userAccess.saveUser(customer);
        userDTOs.put(userDTO.getLogin(), userDTO);

    }
}
