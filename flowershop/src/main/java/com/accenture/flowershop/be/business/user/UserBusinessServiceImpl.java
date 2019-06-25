package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.Customer;

import java.util.Map;

public class UserBusinessServiceImpl implements UserBusinessService{

    private UserBusinessService userBusinessService = new UserBusinessServiceImpl();
    public void saveUser(Customer user) {
        if (user != null) {
            Map<String,Customer> users = userBusinessService.getAllUsers();
            if (!users.isEmpty()) {
                userBusinessService.saveUser(user);
            }
        }
    }

    public Map<String, Customer> getAllUsers(){
        return userBusinessService.getAllUsers();
    }

    public Customer getByLogin(String login){
        if (login != null) {
            return userBusinessService.getByLogin(login);
        }
        return null;
    }
}
