package com.accenture.flowershop.be.utils;

import com.accenture.flowershop.fe.dto.CartDTO;
import com.accenture.flowershop.fe.dto.UserDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public class SessionUtils {

    public static void storeLoginedUser(HttpSession session, UserDTO loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    public static UserDTO getLoginedUser(HttpSession session) {

        return (UserDTO) session.getAttribute("loginedUser");
    }

    public static void storeUserCart(HttpSession session, List<CartDTO> userCart){
        session.setAttribute("userCart", userCart);
    }

    public static List<CartDTO> getUserCart(HttpSession session){
        return (List<CartDTO>) session.getAttribute("userCart");
    }
}
