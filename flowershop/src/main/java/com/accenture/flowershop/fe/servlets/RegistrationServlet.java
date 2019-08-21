package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    /**
     * Ссылка на бизнес уровень для сущности User.
     */
    @Autowired
    private UserBusinessService userBusinessService;
    /**
     * Маппер.
     */
    @Autowired
    private Mapper mapper;

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
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        UserDTO userDTO = new UserDTO.Builder()
                .login(login)
                .name(name)
                .password(password)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<UserDTO> violation : violations) {
                String propertyPath = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                request.setAttribute("error" + propertyPath, message);
            }
            return;
        }

        if (userBusinessService.existsByLogin(userDTO.getLogin())) {
            request.setAttribute("user", userDTO);
            errorString = "Enter unique login!";
            return;
        }

        hasError = false;
        request.setAttribute("user", userDTO);
        userBusinessService.save(mapper.map(userDTO, User.class));
        okString = "Registration completed successfully!";
    }
}
