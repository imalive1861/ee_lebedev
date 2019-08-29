package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.service.dto.cartdto.CartService;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.be.utils.SessionUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Сервлет, использующийся для авторизации пользователя.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    /**
     * Ссылка на бизнес уровень для сущности User.
     */
    @Autowired
    private UserBusinessService userBusinessService;
    /**
     * Ссылка на транспортный уровень для работы с временной корзиной покупателя.
     */
    @Autowired
    private CartService cartService;
    /**
     * Маппер.
     */
    @Autowired
    private Mapper mapper;

    public LoginServlet(){
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
     * Наличие ошибки. Пока true переход на другую страницу не осуществляется.
     */
    private boolean hasError = true;
    /**
     * Сообщение об ошибке.
     */
    private String errorString = null;

    /**
     * Запрос POST. Проверяет нажаты ли кнопки.
     * При наличии ошибки выводит сообщение об ошибке.
     * При отсутствии ошибки перенаправляет на главную страницу.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (request.getParameter("loginButton") != null) {
            UserDTO userDTO = login(
                    request.getParameter("login"),
                    request.getParameter("password"));
            storeUserToSession(userDTO, session);
        }

        request.setAttribute("errorString", errorString);

        if (hasError) {
            errorString = null;
            RequestDispatcher dispatcher =
                    this.getServletContext().getRequestDispatcher("/view/login.jsp");
            dispatcher.forward(request, response);
        } else {
            hasError = true;
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    /**
     * Запрос GET. Пересылает запрос на форму login.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                this.getServletContext().getRequestDispatcher("/view/login.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Авторизация пользователя.
     * @param login - логин
     * @param password - пароль
     */
    private UserDTO login(String login, String password) {
        if (login == null
                || password == null
                || login.length() == 0
                || password.length() == 0) {
            errorString = "Required username and password!";
            return null;
        }
        User user = userBusinessService.logIn(login, password);
        if (user != null) {
            hasError = false;
            return mapper.map(user, UserDTO.class);
        }
        errorString = "User Name or password invalid";
        return null;
    }

    /**
     * Сохранение пользователя и создание корзины пользователя в сессии.
     * @param session - объект HttpSession
     */
    private void storeUserToSession(UserDTO userDTO, HttpSession session) {
        if (userDTO != null) {
            SessionUtils.storeLoginedUser(session, userDTO);
            SessionUtils.storeUserCart(session, cartService.setCart(userDTO.getLogin()));
        }
    }
}
