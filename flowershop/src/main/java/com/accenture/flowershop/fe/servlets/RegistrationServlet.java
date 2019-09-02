package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.service.dto.userdto.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    /**
     * Ссылка на бизнес уровень для сущности User.
     */
    @Autowired
    private UserBusinessService userBusinessService;
    /**
     * Вспомогательный сервис.
     */
    @Autowired
    private UserService userService;

    public RegistrationServlet(){
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    /**
     * Наличие ошибки. Пока true переход на другую страницу не осуществляется.
     */
    private boolean hasError = true;
    /**
     * Сообщение об ошибке.
     */
    private String errorString = "";
    /**
     * Сообщение об успешном действии.
     */
    private String okString = null;

    /**
     * Запрос POST. Проверяет нажаты ли кнопки.
     * При наличии ошибки выводит сообщение об ошибке.
     * При отсутствии ошибки перенаправляет на форму авторизации.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("regButton") != null) {
            registration(request);
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("okString", okString);

        if (hasError){
            errorString = "";
            okString = null;
            RequestDispatcher dispatcher =
                    this.getServletContext().getRequestDispatcher("/view/registration.jsp");
            dispatcher.forward(request, response);
        } else {
            hasError = true;
            RequestDispatcher dispatcher =
                    this.getServletContext().getRequestDispatcher("/view/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Запрос GET. Пересылает запрос на форму registration.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/registration.jsp").forward(request, response);
    }

    private void registration(HttpServletRequest request) {
        String login = request.getParameter("login");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorConfirmPassword", "Passwords do not match!");
            return;
        }

        UserDTO userDTO = new UserDTO.Builder()
                .login(login)
                .name(name)
                .password(newPassword)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();

        Map<String,String> errorMap = userService.dataValidation(userDTO);
        if (!errorMap.isEmpty()) {
            for (String s: errorMap.keySet()) {
                request.setAttribute(s, errorMap.get(s));
            }
            return;
        }

        hasError = false;
        request.setAttribute("user", userDTO);
        userBusinessService.save(userService.fromDto(userDTO));
        okString = "Registration completed successfully!";
    }
}
