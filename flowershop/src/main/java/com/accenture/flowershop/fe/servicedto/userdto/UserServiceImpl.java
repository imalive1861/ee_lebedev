package com.accenture.flowershop.fe.servicedto.userdto;

import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBusinessService userBusinessService;

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
                }
            }
        }
        if (userBusinessService.existsByLogin(userDTO.getLogin())) {
            error = "Login is busy, please choose another one!";
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
                }
            }
        }
        return error;
    }
}
