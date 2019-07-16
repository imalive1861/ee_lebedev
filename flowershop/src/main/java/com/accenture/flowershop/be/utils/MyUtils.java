package com.accenture.flowershop.be.utils;

import com.accenture.flowershop.fe.dto.CardDTO;
import com.accenture.flowershop.fe.dto.UserDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public class MyUtils {

    // Сохранить информацию пользователя, который вошел в систему (login) в Session.
    public static void storeLoginedUser(HttpSession session, UserDTO loginedUser) {
        // В JSP можно получить доступ через ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }

    // Получить информацию пользователя, сохраненная в Session.
    public static UserDTO getLoginedUser(HttpSession session) {
        return (UserDTO) session.getAttribute("loginedUser");
    }


    public static void storeUserCard(HttpSession session, List<CardDTO> cardDTOs){
        session.setAttribute("userCard", cardDTOs);
    }
}
