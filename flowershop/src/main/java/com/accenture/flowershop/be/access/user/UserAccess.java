package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.Customer;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserAccess {
    void saveUser(Customer user);
    Map<String, Customer> getAllUsers();
    Customer getByLogin(String login);
}
