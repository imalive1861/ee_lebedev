package com.accenture.flowershop.be.utils;

import com.accenture.flowershop.be.utils.config.SecurityConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * Утилиты для проверки авторизации пользователя.
 */
public class SecurityUtils {

    /**
     * Является ли путь, заданный в параметре, защищенным
     * @param request - объект HttpServletRequest
     * @return true - если является защищенным, false - если не является защищенным
     */
    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = request.getServletPath();

        Set<String> roles = SecurityConfig.getAllAppRoles();

        for (String role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Имеет ли пользователь доспуп к запрашиваемому ресурсу.
     * @param request - объект HttpServletRequest
     * @return true - если имеет доступ, false - если не имеет доступа
     */
    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = request.getServletPath();

        Set<String> roles = SecurityConfig.getAllAppRoles();

        for (String role : roles) {
            if (!request.isUserInRole(role)) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}
