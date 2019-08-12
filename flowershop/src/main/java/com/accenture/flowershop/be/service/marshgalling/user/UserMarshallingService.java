package com.accenture.flowershop.be.service.marshgalling.user;

import com.accenture.flowershop.fe.dto.UserDTO;

import java.io.IOException;

public interface UserMarshallingService {
    void marshallingObjectToXML(UserDTO userDTO) throws IOException;
    UserDTO marshallingXMLToObject() throws IOException;
    void setUserXmlPath(String userXmlPath);
}
