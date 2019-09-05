package com.accenture.flowershop.fe.service.dto.userdto;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class UserDtoServiceImpl implements UserDtoService {

    @Autowired
    private Mapper mapper;

    @Override
    public String loginValidation(UserDTO userDTO) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        String error = "";
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<UserDTO> violation : violations) {
                String propertyPath = violation.getPropertyPath().toString();
                if (propertyPath.equals("login")) {
                    error = violation.getMessage();
                    break;
                }
            }
        }
        return error;
    }

    @Override
    public String passwordValidation(UserDTO userDTO) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        String error = "";
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<UserDTO> violation : violations) {
                String propertyPath = violation.getPropertyPath().toString();
                if (propertyPath.equals("password")) {
                    error = violation.getMessage();
                    break;
                }
            }
        }
        return error;
    }

    public Map<String, String> dataValidation(UserDTO userDTO) {
        Map<String, String> errorMap = new HashMap<>();
        String errorlogin = loginValidation(userDTO);
        String errorpassword = passwordValidation(userDTO);
        if (!errorlogin.equals("")) {
            errorMap.put("errorlogin", errorlogin);
        }
        if (!errorpassword.equals("")) {
            errorMap.put("errorpassword", errorpassword);
        }
        return errorMap;
    }

    public UserDTO toDto(User user) {
        return mapper.map(user, UserDTO.class);
    }

    public User fromDto(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }
}
