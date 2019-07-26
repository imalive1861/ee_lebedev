package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserAccess {
    void saveUser(User user);
    void delete(String login);
    User get(String login);
    void update(User user);
    List<User> getAll();
}
