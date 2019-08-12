package com.accenture.flowershop.be.service.marshgalling.user;

import com.accenture.flowershop.be.entity.User;

import java.io.IOException;

public interface UserMarshallingService {
    void marshallingObjectToXML(User user) throws IOException;
    User marshallingXMLToObject() throws IOException;
    void setUserXmlPath(String userXmlPath);
}
