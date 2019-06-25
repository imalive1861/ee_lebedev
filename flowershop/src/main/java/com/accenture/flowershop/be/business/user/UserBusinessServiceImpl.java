package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserAccess;
import com.accenture.flowershop.be.entity.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
