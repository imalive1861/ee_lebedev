package com.accenture.flowershop.shop.be.utils.config;

import com.accenture.flowershop.shop.fe.enums.UserRoles;
import com.accenture.flowershop.shop.fe.enums.UserRoles;

import java.util.*;

/**
 * Файл конфигурации для доступа авторизованных пользователей к ресурсам
 */
public class SecurityConfig {

    /**
     * Карта, которая хранит списоки доступных ресурсов для пользователей разных ролей
     */
    private static final Map<String, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    /**
     * Инициализация класса.
     * При инициализации заполняется карта mapConfig для полей ADMIN и CUSTOMER.
     */
    private static void init() {

        List<String> urlPatterns1 = new ArrayList<>();

        urlPatterns1.add("/userInfo");
        urlPatterns1.add("/customer");
        urlPatterns1.add("/order");

        mapConfig.put(UserRoles.CUSTOMER.name(), urlPatterns1);

        List<String> urlPatterns2 = new ArrayList<>();

        urlPatterns2.add("/userInfo");
        urlPatterns2.add("/admin");

        mapConfig.put(UserRoles.ADMIN.name(), urlPatterns2);
    }

    /**
     * Функция получения множества всех доступных ролей.
     *
     * @return множество всех доступных ролей
     */
    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    /**
     * Функция получения списока всех доступных ресурсов для роли.
     *
     * @param role - роль
     * @return список всех доступных ресурсов для роли
     */
    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}
