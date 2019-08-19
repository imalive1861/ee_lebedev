package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.repository.user.UserRepository;

import com.accenture.flowershop.fe.enums.UserRoles;
import com.accenture.flowershop.services.jms.ProducerTest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Реализация интерфейса UserBusinessService.
 */
@Service
public class UserBusinessServiceImpl implements UserBusinessService{

    /**
     * Ссылка на уровень доступа к базе данных для сущности Cart.
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * Ссылка на JMS.
     */
    @Autowired
    private ProducerTest producerTest;
    /**
     * Логгер.
     */
    @Autowired
    private Logger LOG;

    public UserBusinessServiceImpl() {}

    @Override
    public User logIn(String login, String password) {
        User user = getByLogin(login);
        if (user != null && user.getPassword().equals(password)){
            LOG.debug("Customer with login = {} name = {} log in",
                    user.getLogin(), user.getName());
            return user;
        }
        return null;
    }

    @Override
    public boolean existsByLogin(String login){
        return userRepository.existsByLogin(login);
    }

    @Override
    public void save(User user){
        user.setCash(new BigDecimal(2000.00));
        user.setDiscount(0);
        user.setRole(UserRoles.CUSTOMER);
        user = producerTest.saleRequest(user);
        userRepository.saveAndFlush(user);
        LOG.debug("Customer with login = {} name = {} was created", user.getLogin(), user.getName());
    }

    @Override
    public void update(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getByLogin(String login) {
            return userRepository.getByLogin(login);
    }
}
