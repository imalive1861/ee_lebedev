package com.accenture.flowershop.shop.services.rest;

import com.accenture.flowershop.shop.be.service.business.user.UserBusinessService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Класс, реализующий RESTfull сервисы для валидации данных при регистрации.
 */
@Path("/reg")
public class RegistrationCgService {

    /**
     * Ссылка на бизнес уровень для сущности User.
     */
    @Autowired
    private UserBusinessService userBusinessService;

    /**
     * Валидация логина.
     *
     * @param text - логин
     * @return HTTP ответ, если логин ок - код 200,
     * если логин не ок - код 400
     */
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
        return Response.status(200).entity("Login ok! ^_^").build();
    }

    /**
     * Валидация пароля.
     *
     * @param text - пароль
     * @return HTTP ответ, если пароль ок - код 200,
     * если пароль не ок - код 400
     */
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
        return Response.status(200).entity("Password ok! ^_^").build();
    }
}
