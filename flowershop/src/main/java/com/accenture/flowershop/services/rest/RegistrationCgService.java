package com.accenture.flowershop.services.rest;

import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.service.dto.userdto.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reg")
public class RegistrationCgService {

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private UserDtoService userDtoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/loginValidation")
    public Response loginValidation(UserDTO userDTO) {
        String error = userDtoService.loginValidation(userDTO);
        if (userBusinessService.existsByLogin(userDTO.getLogin())) {
            error = "Login is busy, please choose another one!";
        }
        if (error.equals("")) {
            return Response.status(200).entity("Login ok! ^_^").build();
        }
        return Response.status(400).entity(error).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/passwordValidation")
    public Response passwordValidation(UserDTO userDTO) {
        String error = userDtoService.passwordValidation(userDTO);
        if (error.equals("")) {
            return Response.status(200).entity("Password ok! ^_^").build();
        }
        return Response.status(400).entity(error).build();
    }
}
