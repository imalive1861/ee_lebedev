package com.accenture.flowershop.shop.fe.service.dto.userdto;

import com.accenture.flowershop.shop.be.entity.User;
import com.accenture.flowershop.shop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.shop.fe.dto.UserDTO;
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

/**
 * Реализация интерфейса UserDtoService.
 * Свойства: userBusinessService, mapper.
 */
@Service
public class UserDtoServiceImpl implements UserDtoService {
    /**
     * Ссылка на бизнес уровень для сущности User.
     */
    @Autowired
    private UserBusinessService userBusinessService;
    /**
     * Маппер.
     */
    @Autowired
    private Mapper mapper;

    @Override
    public Map<String, String> dataValidation(UserDTO userDTO) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Map<String, String> errorMap = new HashMap<>();
        if (!violations.isEmpty()) {
            for (ConstraintViolation<UserDTO> violation : violations) {
                String propertyPath = violation.getPropertyPath().toString();
                String error = violation.getMessage();
                errorMap.put("error" + propertyPath, error);
            }
        }
        if (userBusinessService.existsByLogin(userDTO.getLogin())) {
            errorMap.put("errorlogin", "Login is busy, please choose another one!");
        }
        return errorMap;
    }

    @Override
    public UserDTO toDto(User user) {
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public User fromDto(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }
}
