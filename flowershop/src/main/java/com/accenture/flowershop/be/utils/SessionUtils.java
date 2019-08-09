package com.accenture.flowershop.be.utils;

import com.accenture.flowershop.fe.dto.CustomerCartDTO;
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

    public static void storeUserCart(HttpSession session, List<CustomerCartDTO> userCart){
        session.setAttribute("userCart", userCart);
    }

    public static List<CustomerCartDTO> getUserCart(HttpSession session){
        return (List<CustomerCartDTO>) session.getAttribute("userCart");
    }
}
