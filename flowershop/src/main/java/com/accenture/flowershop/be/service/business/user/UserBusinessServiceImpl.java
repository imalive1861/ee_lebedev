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
import java.math.RoundingMode;

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

    public boolean isUniqueLogin(User user){
        return userRepository.getByLogin(user.getLogin()) != null;
    }

    public void save(String login, String password, String name,
                     String address, String phoneNumber){
        User user = new User(login, password, name, address, phoneNumber,
                new BigDecimal(2000.00),0, UserRoles.CUSTOMER);
        user = producerTest.saleRequest(user);
        userRepository.saveAndFlush(user);
        LOG.debug("Customer with login = {} name = {} was created", user.getLogin(), user.getName());
    }

    public User getByLogin(String login) {
        if (login != null) {
            return userRepository.getByLogin(login);
        }
        return null;
    }

    public User get(User user) {
        return userRepository.getByLogin(user.getLogin());
    }

    public BigDecimal checkScore(User user, BigDecimal sumPrice){
        BigDecimal score = user.getCash();
        if (sumPrice.compareTo(score) < 0) {
            user.setCash(score.subtract(sumPrice).setScale(2, RoundingMode.UP));
            userRepository.saveAndFlush(user);
            return sumPrice.setScale(2, RoundingMode.UP);
        }
        return null;
    }
}
