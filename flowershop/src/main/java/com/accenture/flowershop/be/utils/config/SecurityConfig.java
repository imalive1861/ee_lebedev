package com.accenture.flowershop.be.utils.config;

import com.accenture.flowershop.fe.enums.UserRoles;

import java.util.*;

public class SecurityConfig {

    private static final Map<String, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        List<String> urlPatterns1 = new ArrayList<>();

        urlPatterns1.add("/userInfo");
        urlPatterns1.add("/customer");
        urlPatterns1.add("/order");

        mapConfig.put(UserRoles.CUSTOMER.getTitle(), urlPatterns1);

        List<String> urlPatterns2 = new ArrayList<>();

        urlPatterns2.add("/userInfo");
        urlPatterns2.add("/admin");

        mapConfig.put(UserRoles.ADMIN.getTitle(), urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}
