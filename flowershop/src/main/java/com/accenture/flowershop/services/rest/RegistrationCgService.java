package com.accenture.flowershop.services.rest;

import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.service.dto.userdto.UserDtoService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

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
    public Response loginValidation(String text) {
        JSONObject obj = new JSONObject(text);
        String login = obj.getString("login");
        if (!isNotBlank(login)) {
            return Response.status(400).entity("Login cannot be empty!").build();
        }
        if (login.length() < 4 || login.length() > 20) {
            return Response.status(400).entity("Login length minimum 4, maximum 20!").build();
        }
        if (userBusinessService.existsByLogin(login)) {
            return Response.status(400).entity("Login is busy, please choose another one!").build();
        }
        return Response.status(200).entity(login + "  -  Login ok! ^_^").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/passwordValidation")
    public Response passwordValidation(String text) {
        JSONObject obj = new JSONObject(text);
        String password = obj.getString("password");
        if (!isNotBlank(password)) {
            return Response.status(400).entity("Password cannot be empty!").build();
        }
        if (password.length() < 8 || password.length() > 20) {
            return Response.status(400).entity("Password length minimum 8, maximum 20!").build();
        }
        return Response.status(200).entity(password + "  -  Password ok! ^_^").build();
    }
}
