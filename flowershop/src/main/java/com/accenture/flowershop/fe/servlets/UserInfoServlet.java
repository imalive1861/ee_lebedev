package com.accenture.flowershop.fe.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет, использующийся для вывода информации о пользователе.
 */
@WebServlet(name = "UserInfoServlet", urlPatterns = "/userInfo")
public class UserInfoServlet extends HttpServlet {

    public UserInfoServlet(){
        super();
    }

    /**
     * Запрос POST. Перенаправляет запрос на GET.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Запрос GET. Пересылает запрос на страницу userInfo.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/userInfo.jsp").forward(request, response);
    }
}
