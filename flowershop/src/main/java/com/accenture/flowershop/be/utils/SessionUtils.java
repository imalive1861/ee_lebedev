package com.accenture.flowershop.be.utils;

import com.accenture.flowershop.be.service.business.card.CardService;
import com.accenture.flowershop.fe.dto.CustomerCardDTO;
import com.accenture.flowershop.fe.dto.UserDTO;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class SessionUtils {

    public static void storeLoginedUser(HttpSession session, UserDTO loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    public static UserDTO getLoginedUser(HttpSession session) {
        return (UserDTO) session.getAttribute("loginedUser");
    }

    public static void storeUserCard(HttpSession session, CardService cardService){
        session.setAttribute("userCard", cardService.getCard());
        session.setAttribute("allSum", cardService.getAllSumPrice());
    }

    public static List<CustomerCardDTO> getUserCard(HttpSession session){
        return (List<CustomerCardDTO>) session.getAttribute("userCard");
    }

    public static BigDecimal getAllSum(HttpSession session){
        return (BigDecimal) session.getAttribute("allSum");
    }
}
