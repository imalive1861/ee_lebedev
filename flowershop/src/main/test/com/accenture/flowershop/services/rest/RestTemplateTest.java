package com.accenture.flowershop.services.rest;

import com.accenture.flowershop.fe.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RestTemplateTest {

    private UserDTO testUsedLoginAdmin;
    private UserDTO testNotUsedLogin;

    @Before
    public void initUsers() {
        testUsedLoginAdmin = new UserDTO();
        testUsedLoginAdmin.setLogin("admin");

        testNotUsedLogin = new UserDTO();
        testNotUsedLogin.setLogin("asklfjsdasfrwqgerhtrlknheoruelfj");
    }

    @Test
    public void testUserLogin() {
        assertTrue(testLogins(testUsedLoginAdmin));
        assertFalse(testLogins(testNotUsedLogin));
    }

    private static boolean testLogins(UserDTO testLogin) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<UserDTO> requestBody = new HttpEntity<>(testLogin, headers);
        boolean e = restTemplate.postForObject(
                "http://localhost:8080/rest/reg/checklogin", requestBody, Boolean.class);
        if (e) {
            System.out.println("Login \"" + testLogin.getLogin() + "\" is used");
        } else {
            System.out.println("Login \"" + testLogin.getLogin() + "\" is not used");
        }
        return e;
    }
}
