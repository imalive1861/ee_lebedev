package com.accenture.flowershop.be.service.marshgalling.user;

import com.accenture.flowershop.be.utils.marshalling.XMLConverter;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserMarshallingServiceImpl implements UserMarshallingService {

    private String userXmlPath;
    private XMLConverter converter;

    @Autowired
    public UserMarshallingServiceImpl(XMLConverter converter) {
        this.converter = converter;
    }

    public void setUserXmlPath(String userXmlPath) {
        this.userXmlPath = userXmlPath;
    }

    public void marshallingObjectToXML(UserDTO userDTO)
            throws IOException {
        converter.convertFromObjectToXML(userDTO, userXmlPath);
    }

    public UserDTO marshallingXMLToObject() throws IOException {
        return (UserDTO) converter.convertFromXMLToObject(userXmlPath);
    }

}
