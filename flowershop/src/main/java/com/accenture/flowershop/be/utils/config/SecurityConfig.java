package com.accenture.flowershop.be.utils.config;

import java.util.*;

public class SecurityConfig {
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_CUSTOMER = "CUSTOMER";

    // String: Role
    // List<String>: urlPatterns.
    private static final Map<String, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        // Конфигурация для роли "User".
        List<String> urlPatterns1 = new ArrayList<>();

        urlPatterns1.add("/userInfo");
        urlPatterns1.add("/customer");
        urlPatterns1.add("/order");

        mapConfig.put(ROLE_CUSTOMER, urlPatterns1);

        // Конфигурация для роли "Admin".
        List<String> urlPatterns2 = new ArrayList<>();

        urlPatterns2.add("/userInfo");
        urlPatterns2.add("/admin");

        mapConfig.put(ROLE_ADMIN, urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}
