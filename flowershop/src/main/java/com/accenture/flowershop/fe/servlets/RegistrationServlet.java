package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    private boolean hasError = true;
    private String errorString = null;
    private String okString = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        registration(request);

        request.setAttribute("errorString", errorString);
        request.setAttribute("okString", okString);

        if (hasError){
            errorString = null;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/registration.jsp").forward(request, response);
    }

    @Transactional
    private void registration(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        UserDTO userDTO = new UserDTO.Builder()
                .login(login)
                .name(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();

        if (login == null ||        login.length() == 0 ||
            password == null ||     password.length() == 0 ||
            name == null ||         name.length() == 0 ||
            address == null ||      address.length() == 0 ||
            phoneNumber == null ||  phoneNumber.length() == 0) {
            errorString = "Fill empty sells!";
            return;
        }
        if (userBusinessService.existsByLogin(userDTO.getLogin())) {
            request.setAttribute("user", userDTO);
            errorString = "Enter unique login!";
            return;
        }
        if (password.length() < 8){
            request.setAttribute("user", userDTO);
            errorString = "Password must be more than 8 symbols!";
            return;
        }
        hasError = false;
        request.setAttribute("user", userDTO);
        userDTO.setPassword(password);
        userBusinessService.save(mapper.map(userDTO, User.class));
        okString = "Registration completed successfully!";
    }
}
