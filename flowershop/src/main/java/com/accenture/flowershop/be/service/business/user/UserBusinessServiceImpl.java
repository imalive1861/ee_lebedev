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
        User user = userRepository.getByLoginAndPassword(login, password);
        if (user != null){
            LOG.debug("Customer \"{}\" log in.", user.getLogin());
        } else {
            LOG.debug("Customer \"{}\" with password \"{}\" not exist!", login, password);
        }
        return user;
    }

    @Override
    public boolean existsByLogin(String login){
        return userRepository.existsByLogin(login);
    }

    @Override
    @Transactional
    public void save(User user){
        user.setCash(new BigDecimal(2000.00));
        user.setDiscount(0);
        user.setRole(UserRoles.CUSTOMER);
        user = producerTest.saleRequest(user);
        userRepository.saveAndFlush(user);
        LOG.debug("Customer \"{}\" with login \"{}\" was created.", user, user.getLogin());
    }

    @Override
    @Transactional
    public void update(User user) {
        userRepository.saveAndFlush(user);
        LOG.debug("Customer \"{}\" with login \"{}\" was updated.", user, user.getLogin());
    }

    @Override
    public User getByLogin(String login) {
            return userRepository.getByLogin(login);
    }
}
