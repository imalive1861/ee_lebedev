package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserAccess {
    void saveUser(User user);
    void delete(long id);
    User get(long id);
    void update(User user);
    Map<String, User> getAll();
}
