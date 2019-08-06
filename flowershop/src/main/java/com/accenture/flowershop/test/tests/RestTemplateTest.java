package com.accenture.flowershop.test.tests;

import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {
    public static void main(String[] args) {

        UserDTO testUsedLoginAdmin = new UserDTO();
        testUsedLoginAdmin.setLogin("admin");

        UserDTO testNotUsedLogin = new UserDTO();
        testNotUsedLogin.setLogin("asklfjsdasfrwqgerhtrlknheoruelfj");

        if (testLogins(testUsedLoginAdmin) && !testLogins(testNotUsedLogin)){
            System.out.println("All OK :)");
        } else {
            System.out.println("Somethings not right!");
        }
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
