package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.be.access.user.UserAccess;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.utils.config.SecurityConfig;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

@Service
public class UserBusinessServiceImpl implements UserBusinessService{

    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    private UserAccess userAccess;

    private Map<String, UserDTO> userDTOs;

    @Autowired
    public UserBusinessServiceImpl(UserAccess userAccess){
        this.userAccess = userAccess;
        userDTOs = new TreeMap<>();
        for (User u: userAccess.getAll().values()){
            UserDTO userDTO = toUserDTO(u);
            userDTOs.put(userDTO.getLogin(), userDTO);
        }
    }

    public UserDTO getUserDTO(String login, String password) {
        UserDTO userDTO = userDTOs.get(login);
        if (userDTO != null && userDTO.getPassword().equals(password)){
            return userDTO;
        }
        return null;
    }

    public boolean uniqueLogin(String login){
        return userDTOs.containsKey(login);
    }

    public void saveNewUser(String login, String password, String name,
                           String address, String phoneNumber){
        Random random = new Random();
        UserDTO userDTO = new UserDTO(login, password, name, address, phoneNumber, new BigDecimal(2000.00),
                random.nextInt(10), SecurityConfig.ROLE_CUSTOMER);
        User user = toUser(userDTO);
        userAccess.saveUser(user);
        userDTO.setId(user.getId());
        userDTOs.put(userDTO.getLogin(), userDTO);
        LOG.debug("Customer with login = {} name = {} was created", userDTO.getLogin(), userDTO.getName());
    }

    private User toUser(UserDTO userDTO){
        if(userAccess.get(userDTO.getId()) != null) {
            return get(userDTO.getId());
        }
        return new User(userDTO.getLogin(),userDTO.getPassword(),userDTO.getName(),
                userDTO.getAddress(),userDTO.getPhoneNumber(),userDTO.getScore(),
                userDTO.getSale(),userDTO.getRole());
    }

    private UserDTO toUserDTO(User user){
        if(userDTOs.get(user.getLogin()) != null) {
            return userDTOs.get(user.getLogin());
        }
        return new UserDTO(user.getLogin(),user.getPassword(),user.getName(),
                user.getAddress(),user.getPhoneNumber(),user.getScore(),
                user.getSale(),user.getRole());
    }

    public User get(long id) {
        if (id != 0) {
            return userAccess.get(id);
        }
        return null;
    }
}
