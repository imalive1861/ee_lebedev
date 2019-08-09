package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.repository.user.UserRepository;
import com.accenture.flowershop.fe.dto.UserDTO;

import com.accenture.flowershop.fe.dto.mappers.UserMapper;
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
    private UserMapper userMapper;
    private ProducerTest producerTest;
    private Logger LOG;

    @Autowired
    public UserBusinessServiceImpl(UserRepository userRepository,
                                   UserMapper userMapper,
                                   ProducerTest producerTest,
                                   Logger LOG){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.producerTest = producerTest;
        this.LOG = LOG;
    }

    public UserDTO logIn(String login, String password) {
        UserDTO userDTO = get(login);
        if (userDTO != null && userDTO.getPassword().equals(password)){
            LOG.debug("Customer with login = {} name = {} log in",
                    userDTO.getLogin(), userDTO.getName());
            return userDTO;
        }
        return null;
    }

    public boolean uniqueLogin(UserDTO userDTO){
        return userRepository.getByLogin(userDTO.getLogin()) != null;
    }

    public void saveNewUser(String login, String password, String name,
                           String address, String phoneNumber){
        UserDTO userDTO = new UserDTO(login, password, name, address, phoneNumber,
                new BigDecimal(2000.00),0, UserRoles.CUSTOMER.getTitle());
        User user = userMapper.userDtoToUser(producerTest.saleRequest(userDTO));
        userRepository.saveAndFlush(user);
        LOG.debug("Customer with login = {} name = {} was created", userDTO.getLogin(), userDTO.getName());
    }

    public UserDTO get(String login) {
        if (login != null) {
            return userMapper.userToUserDto(userRepository.getByLogin(login));
        }
        return null;
    }

    public User get(UserDTO userDTO) {
        return userRepository.getByLogin(userDTO.getLogin());
    }

    public BigDecimal checkScore(UserDTO userDTO, BigDecimal sumPrice){
        BigDecimal score = userDTO.getCash();
        if (sumPrice.compareTo(score) < 0) {
            userDTO.setCash(score.subtract(sumPrice).setScale(2, RoundingMode.UP));
            userRepository.saveAndFlush(userMapper.userDtoToUser(userDTO));
            return sumPrice.setScale(2, RoundingMode.UP);
        }
        return null;
    }
}
