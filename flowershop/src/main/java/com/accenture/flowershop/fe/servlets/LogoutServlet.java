package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.fe.servicedto.cartdto.CartService;
import com.accenture.flowershop.be.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Сервлет, использующийся для деавторизации.
 */
@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    /**
     * Ссылка на транспортный уровень для работы с временной корзиной покупателя.
     */
    @Autowired
    private CartService cartService;

    public LogoutServlet(){
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
     * Запрос POST. Перенаправляет запрос на GET.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Запрос GET. Деавторизует пользователя и перенаправляет на страницу авторизации.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logOut(request);
        response.sendRedirect(request.getContextPath() + "/login");
    }

    /**
     * Деавторизация.
     * @param request - объект HttpServletRequest
     */
    private void logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        cartService.clear(SessionUtils.getLoginedUser(session).getLogin());
        session.invalidate();
    }
}
