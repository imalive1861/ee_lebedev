package com.accenture.flowershop.be.service.marshgalling.user;

import com.accenture.flowershop.fe.dto.UserDTO;

import java.io.IOException;

public interface UserMarshallingService {
    void userMarshallingObjectToXML(UserDTO userDTO) throws IOException;
    UserDTO userMarshallingXMLToObject() throws IOException;
    void setUserXML(String userXML);
}
