package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserAccessImpl implements UserAccess {

    private Map<String, Customer> users = new HashMap<>();

    public UserAccessImpl() {
        Customer admin = new Customer();
        admin.setId(1);
        admin.setLogin("admin");
        admin.setPassword("admin123");
        users.put("admin", admin);
        Customer user1 = new Customer();
        user1.setLogin("user1");
        user1.setPassword("user123");
        user1.setAddress("ABC");
        user1.setId(2);
        user1.setName("Vasia");
        user1.setPhoneNumber("123123123");
        user1.setSale(3);
        user1.setScore(2000.00);
        users.put("user1", user1);
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