package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.repository.user.UserRepository;

import com.accenture.flowershop.fe.enums.UserRoles;
import com.accenture.flowershop.services.jms.ProducerTest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class UserBusinessServiceImpl implements UserBusinessService{

    private UserRepository userRepository;
    private ProducerTest producerTest;
    private Logger LOG;

    @Autowired
    public UserBusinessServiceImpl(UserRepository userRepository,
                                   ProducerTest producerTest,
                                   Logger LOG){
        this.userRepository = userRepository;
        this.producerTest = producerTest;
        this.LOG = LOG;
    }

    public User logIn(String login, String password) {
        User user = getByLogin(login);
        if (user != null && user.getPassword().equals(password)){
            LOG.debug("Customer with login = {} name = {} log in",
                    user.getLogin(), user.getName());
            return user;
        }
        return null;
    }

    public boolean existsByLogin(String login){
        return userRepository.existsByLogin(login);
    }

    public void save(User user){
        user.setCash(new BigDecimal(2000.00));
        user.setDiscount(0);
        user.setRole(UserRoles.CUSTOMER);
        user = producerTest.saleRequest(user);
        userRepository.saveAndFlush(user);
        LOG.debug("Customer with login = {} name = {} was created", user.getLogin(), user.getName());
    }

    public void update(User user) {
        userRepository.saveAndFlush(user);
    }

    public User getByLogin(String login) {
            return userRepository.getByLogin(login);
    }

    public User get(User user) {
        return userRepository.getByLogin(user.getLogin());
    }
}
