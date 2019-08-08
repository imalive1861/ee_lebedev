package com.accenture.flowershop.be.repository.user;

import com.accenture.flowershop.be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByLogin(String login);
}
