package com.accenture.flowershop.services.rest;

import com.accenture.flowershop.fe.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.BadRequestException;

public class RestTemplateTest {

    private UserDTO testValidUserData;
    private UserDTO testUnvalidUserData;

    @Before
    public void initUsers() {
        testValidUserData = new UserDTO.Builder()
                .login("ololo")
                .password("123123123")
                .build();

        testUnvalidUserData = new UserDTO.Builder()
                .login("admin")
                .build();
    }

    @Test
    public void testUsedLogin() {
        testLogins(testValidUserData);
    }

    @Test(expected = BadRequestException.class)
    public void testNotUsedLogin() {
        testLogins(testUnvalidUserData);
    }

    private void testLogins(UserDTO testLogin) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        HttpEntity<UserDTO> requestBody = new HttpEntity<>(testLogin, headers);
        restTemplate.postForObject(
                "http://localhost:8080/rest/reg/loginValidation", requestBody, String.class);
    }
}
