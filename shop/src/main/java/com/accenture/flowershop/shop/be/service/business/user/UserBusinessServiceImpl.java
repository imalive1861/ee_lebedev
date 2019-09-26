package com.accenture.flowershop.shop.be.service.business.user;

import com.accenture.flowershop.shop.be.entity.User;
import com.accenture.flowershop.shop.be.repository.user.UserRepository;
import com.accenture.flowershop.shop.fe.enums.UserRoles;
import com.accenture.flowershop.shop.services.jms.ProducerTest;
import com.accenture.flowershop.shop.be.entity.User;import com.accenture.flowershop.shop.be.repository.user.UserRepository;import com.accenture.flowershop.shop.fe.enums.UserRoles;import com.accenture.flowershop.shop.services.jms.ProducerTest;import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Реализация интерфейса UserBusinessService.
 */
@Service
public class UserBusinessServiceImpl implements UserBusinessService {

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

    public UserBusinessServiceImpl() {
    }

    @Override
    public User logIn(String login, String password) {
        User user = userRepository.getByLoginAndPassword(login, password);
        if (user != null) {
            LOG.debug("Customer \"{}\" log in.", user.getLogin());
        } else {
            LOG.debug("Customer \"{}\" with password \"{}\" not exist!", login, password);
        }
        return user;
    }

    @Override
    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setCash(new BigDecimal(2000.00));
        user.setDiscount(0);
        user.setRole(UserRoles.CUSTOMER);
        userRepository.save(user);
        LOG.debug("Customer \"{}\" have {}% discount before JMS.", user, user.getDiscount());
        DiscountThread thread = new DiscountThread(user);
        thread.start();
        LOG.debug("Customer \"{}\" with login \"{}\" was created.", user, user.getLogin());
    }

    /**
     * Класс, использующийся для отправки запроса на скидку через JMS.
     */
    private class DiscountThread extends Thread {

        User user;

        DiscountThread(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            user = producerTest.saleRequest(user);
            LOG.debug("Customer \"{}\" have {}% discount after JMS.", user, user.getDiscount());
            userRepository.saveAndFlush(user);
        }
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.getByLogin(login);
    }
}
