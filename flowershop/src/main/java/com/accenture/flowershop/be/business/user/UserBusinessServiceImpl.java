package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserAccess;
import com.accenture.flowershop.be.entity.user.User;
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
        for (User u: userAccess.getAllUsers().values()){
            UserDTO userDTO = new UserDTO(u.getLogin(),u.getPassword(),u.getName(),u.getAddress(),
                    u.getPhoneNumber(),u.getScore(),u.getSale(),u.getRoles());
            userDTOs.put(userDTO.getLogin(), userDTO);
        }
    }

    public void saveUser(String login, String password, String name, String address,
                         String phoneNumber, BigDecimal score, int sale, String role) {
        userAccess.saveUser(login, password, name, address, phoneNumber, score, sale, role);
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

    public void setNewUser(String login, String password, String name,
                           String address, String phoneNumber){
        Random random = new Random();
        UserDTO userDTO = new UserDTO(login, password, name, address,
                phoneNumber, new BigDecimal(2000.00).setScale(2), random.nextInt(10), SecurityConfig.ROLE_CUSTOMER);
        saveUser(userDTO.getLogin(),userDTO.getPassword(),userDTO.getName(),userDTO.getAddress(),
                userDTO.getPhoneNumber(),userDTO.getScore(),userDTO.getSale(),userDTO.getRole());
        userDTOs.put(userDTO.getLogin(), userDTO);
        LOG.debug("Customer with login = {} name = {} was created", userDTO.getLogin(), userDTO.getName());

    }
}
