package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.be.access.user.UserAccess;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.service.marshgalling.user.UserMarshallingService;
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

    @Autowired
    private Logger LOG;

    private UserMapper userMapper;
    private UserAccess userAccess;
    private ProducerTest producerTest;

    @Autowired
    public UserBusinessServiceImpl(UserAccess userAccess, UserMapper userMapper,
                                   ProducerTest producerTest){
        this.userAccess = userAccess;
        this.userMapper = userMapper;
        this.producerTest = producerTest;
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
        return userAccess.get(userDTO.getLogin()) != null;
    }

    public void saveNewUser(String login, String password, String name,
                           String address, String phoneNumber){
        UserDTO userDTO = new UserDTO(login, password, name, address, phoneNumber,
                new BigDecimal(2000.00),0, UserRoles.CUSTOMER.getTitle());
        User user = userMapper.userDtoToUser(producerTest.saleRequest(userDTO));
        userAccess.saveUser(user);
        LOG.debug("Customer with login = {} name = {} was created", userDTO.getLogin(), userDTO.getName());
    }

    public UserDTO get(String login) {
        if (login != null) {
            return userMapper.userToUserDto(userAccess.get(login));
        }
        return null;
    }

    public User get(UserDTO userDTO) {
        return userAccess.get(userDTO.getLogin());
    }

    public BigDecimal checkScore(UserDTO userDTO, BigDecimal sumPrice){
        BigDecimal score = userDTO.getScore();
        if (sumPrice.compareTo(score) < 0) {
            userDTO.setScore(score.subtract(sumPrice).setScale(2, RoundingMode.UP));
            userAccess.update(userMapper.userDtoToUser(userDTO));
            return sumPrice.setScale(2, RoundingMode.UP);
        }
        return null;
    }
}
