package com.accenture.flowershop.fe.dto.mappers;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface UserMapper {
    UserDTO userToUserDto(User user);
    User userDtoToUser(UserDTO userDTO);
}
