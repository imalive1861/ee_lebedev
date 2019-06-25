package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserAccessImpl implements UserAccess {

    private Map<String, Customer> users = new HashMap<>();

    UserAccessImpl() {
        Customer admin = new Customer();
        admin.setLogin("admin");
        admin.setPassword("admin123");
        users.put("admin", admin);
    }

    public void saveUser(Customer user){
        users.put(user.getLogin(), user);
    }

    public Map<String, Customer> getAllUsers(){
        return users;
    }

    public Customer getByLogin(String login){
        return users.get(login);
    }
}