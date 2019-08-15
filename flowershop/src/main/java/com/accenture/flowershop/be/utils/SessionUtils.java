package com.accenture.flowershop.be.utils;

import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;

import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static void storeLoginedUser(HttpSession session, UserDTO loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    public static UserDTO getLoginedUser(HttpSession session) {

        return (UserDTO) session.getAttribute("loginedUser");
    }

    public static void storeUserCart(HttpSession session, OrderDTO userCart){
        session.setAttribute("userCart", userCart);
    }

    public static OrderDTO getUserCart(HttpSession session){
        return (OrderDTO) session.getAttribute("userCart");
    }
}
