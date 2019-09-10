package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.service.business.cart.CartBusinessService;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.be.utils.SessionUtils;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.service.dto.orderdto.OrderDtoService;
import com.accenture.flowershop.fe.service.dto.userdto.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

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
    private CartBusinessService cartBusinessService;
    /**
     * Вспомогательный сервис.
     */
    @Autowired
    private UserDtoService userDtoService;
    /**
     * Вспомогательный сервис.
     */
    @Autowired
    private OrderDtoService orderDtoService;
    /**
     * Наличие ошибки. Пока true переход на другую страницу не осуществляется.
     */
    private boolean hasError = true;
    /**
     * Сообщение об ошибке.
     */
    private String errorString = null;

    public LoginServlet() {
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
     * Запрос POST. Проверяет нажаты ли кнопки.
     * При наличии ошибки выводит сообщение об ошибке.
     * При отсутствии ошибки перенаправляет на главную страницу.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (isNotBlank(request.getParameter("loginButton"))) {
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
     *
     * @param login    - логин
     * @param password - пароль
     */
    private UserDTO login(String login, String password) {
        if (isNotBlank(login) && isNotBlank(password)) {
            errorString = "Login and/or password cannot be empty!";
            return null;
        }
        User user = userBusinessService.logIn(login, password);
        if (user != null) {
            hasError = false;
            return userDtoService.toDto(user);
        }
        errorString = "User Name or password invalid";
        return null;
    }

    /**
     * Сохранение пользователя и создание корзины пользователя в сессии.
     *
     * @param session - объект HttpSession
     */
    private void storeUserToSession(UserDTO userDTO, HttpSession session) {
        if (userDTO != null) {
            SessionUtils.storeLoginedUser(session, userDTO);
            cartBusinessService.setCart(userDTO.getLogin());
        }
    }
}
