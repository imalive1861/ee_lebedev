package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
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
import java.util.Random;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Autowired
    private UserBusinessService userBusinessService;

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
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        UserDTO user = null;
        boolean hasError = false;
        String errorString = null;
        String okString = null;

        if (login == null || login.length() == 0 || password == null || password.length() == 0 ||
        name == null || name.length() == 0 || address == null || address.length() == 0 ||
        phoneNumber == null || phoneNumber.length() == 0) {
            hasError = true;
            errorString = "Fill empty sells!";
        } else {
            if (userBusinessService.uniqueLogin(login)) {
                hasError = true;
                errorString = "Enter unique login!";
            } else {
                if (password.length() < 8){
                    hasError = true;
                    errorString = "Password must be more than 8 symbols!";
                }
            }
        }
        if (hasError){
            user = new UserDTO();
            user.setLogin(login);
            user.setName(name);
            user.setAddress(address);
            user.setPhoneNumber(phoneNumber);

            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/registration.jsp");

            dispatcher.forward(request, response);
        } else {
            Random random = new Random();
            user = new UserDTO();
            user.setId(userBusinessService.setMaxId());
            user.setLogin(login);
            user.setPassword(password);
            user.setName(name);
            user.setAddress(address);
            user.setPhoneNumber(phoneNumber);
            user.setSale(random.nextInt(10));
            user.setScore(2000.00);
            userBusinessService.setNewUser(user);
            okString = "Registration completed successfully!";

            request.setAttribute("okString", okString);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/registration.jsp");

            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(request, response);
    }
}
