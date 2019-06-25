package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.Customer;

import java.util.Map;

public interface UserAccess {
    void saveUser(Customer user);
    Map<String, Customer> getAllUsers();
    Customer getByLogin(String login);
}
