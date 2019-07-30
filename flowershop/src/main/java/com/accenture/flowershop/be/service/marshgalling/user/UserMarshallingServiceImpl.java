package com.accenture.flowershop.be.service.marshgalling.user;

import com.accenture.flowershop.be.utils.marshalling.XMLConverter;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserMarshallingServiceImpl implements UserMarshallingService {

    private String userXML;
    private XMLConverter converter;

    @Autowired
    public UserMarshallingServiceImpl(XMLConverter converter) {
        this.converter = converter;
    }

    public void setUserXML(String userXML) {
        this.userXML = userXML;
    }

    public void userMarshallingObjectToXML(UserDTO userDTO)
            throws IOException {
        converter.convertFromObjectToXML(userDTO, userXML);
    }

    public UserDTO userMarshallingXMLToObject() throws IOException {
        return (UserDTO) converter.convertFromXMLToObject(userXML);
    }

}
