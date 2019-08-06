package com.accenture.flowershop.services.rest;

import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/reg")
public class RegistrationCgService {

    @Autowired
    private UserBusinessService userBusinessService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/checklogin")
    public boolean checkLogin(UserDTO userDTO){
        return userBusinessService.uniqueLogin(userDTO);
    }
}
