package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.dto.mappers.UserMapper;
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

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private UserMapper userMapper;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        UserDTO userDTO;
        boolean hasError = false;
        String errorString = null;
        String okString;

        userDTO = new UserDTO.Builder()
                .login(login)
                .name(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();

        if (    login == null ||        login.length() == 0 ||
                password == null ||     password.length() == 0 ||
                name == null ||         name.length() == 0 ||
                address == null ||      address.length() == 0 ||
                phoneNumber == null ||  phoneNumber.length() == 0) {
            hasError = true;
            errorString = "Fill empty sells!";
        } else {
            if (userBusinessService.isUniqueLogin(userMapper.userDtoToUser(userDTO))) {
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
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", userDTO);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(
                    "/view/registration.jsp");
            dispatcher.forward(request, response);
        } else {
            userDTO.setPassword(password);
            userBusinessService.save(userMapper.userDtoToUser(userDTO));
            okString = "Registration completed successfully!";

            request.setAttribute("okString", okString);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(
                    "/view/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/registration.jsp").forward(request, response);
    }
}
