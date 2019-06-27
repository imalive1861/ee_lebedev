package com.accenture.flowershop.be.utils;

import com.accenture.flowershop.fe.dto.UserDTO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyUtils {
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    // Сохранить информацию пользователя, который вошел в систему (login) в Session.
    public static void storeLoginedUser(HttpSession session, UserDTO loginedUser) {
        // В JSP можно получить доступ через ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }

    // Получить информацию пользователя, сохраненная в Session.
    public static UserDTO getLoginedUser(HttpSession session) {
        return (UserDTO) session.getAttribute("loginedUser");
    }

    // Сохранить информацию пользователя в Cookie.
    public static void storeUserCookie(HttpServletResponse response, UserDTO user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getLogin());
        // 1 день (Конвертированный в секунды)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // Удалить Cookie пользователя
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        // 0 секунд. (Данный Cookie будет сразу недействителен)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
}
