package com.accenture.flowershop.be.service.marshgalling.user;

import com.accenture.flowershop.be.utils.marshalling.XMLConverter;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserMarshallingServiceImpl implements UserMarshallingService {

    @Value("userXML")
    private String userXML;

    @Autowired
    private XMLConverter converter;

    public UserMarshallingServiceImpl() {}

    public void userMarshallingObjectToXML(UserDTO userDTO)
            throws IOException {
        converter.convertFromObjectToXML(userDTO, userXML);
    }

    public UserDTO userMarshallingXMLToObject() throws IOException {
        return (UserDTO) converter.convertFromXMLToObject(userXML);
    }

}
