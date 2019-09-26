package com.accenture.flowershop.be.utils;

import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;

import javax.servlet.http.HttpSession;

/**
 * Утилиты для работы с потльзовательской сессией.
 */
public class SessionUtils {

    /**
     * Сохранить данные пользователя в сессию.
     *
     * @param session     - объект HttpSession
     * @param loginedUser - объект UserDTO
     */
    public static void storeLoginedUser(HttpSession session, UserDTO loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    /**
     * Взять данные пользователя из сессии.
     *
     * @param session - объект HttpSession
     * @return объект UserDTO
     */
    public static UserDTO getLoginedUser(HttpSession session) {

        return (UserDTO) session.getAttribute("loginedUser");
    }

    /**
     * Сохранить пользовательскую корзину товаров в сессию.
     *
     * @param session  - объект HttpSession
     * @param userCart - объект OrderDTO
     */
    public static void storeUserCart(HttpSession session, OrderDTO userCart) {
        session.setAttribute("userCart", userCart);
    }

    /**
     * Взять пользовательскую корзину товаров из сессию.
     *
     * @param session - объект HttpSession
     * @return объект OrderDTO
     */
    public static OrderDTO getUserCart(HttpSession session) {
        return (OrderDTO) session.getAttribute("userCart");
    }
}
