package com.accenture.flowershop.shop.fe.servlets;

import com.accenture.flowershop.shop.be.utils.SessionUtils;
import com.accenture.flowershop.shop.fe.dto.UserDTO;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет, использующийся для перенаправления на главную страницу,
 * соответствующую роли пользователя.
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/", "/index"})
public class HomeServlet extends HttpServlet {

    public HomeServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    /**
     * Запрос GET. Перенаправляет на главную страницу,
     * соответствующую роли пользователя.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO userDTO = SessionUtils.getLoginedUser(request.getSession());
        if (userDTO.getRole().name().equals("ADMIN")) {
            response.sendRedirect(request.getContextPath() + "/admin");
        } else {
            response.sendRedirect(request.getContextPath() + "/customer");
        }
    }

    /**
     * Запрос POST. Перенаправляет запрос на GET.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
