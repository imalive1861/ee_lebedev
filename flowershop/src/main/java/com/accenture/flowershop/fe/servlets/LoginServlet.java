package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.servicedto.cartdto.CartService;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.be.utils.SessionUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private CartService cartService;

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

    private boolean hasError = true;
    private String errorString = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        login(request);

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                this.getServletContext().getRequestDispatcher("/view/login.jsp");
        dispatcher.forward(request, response);
    }

    @Transactional
    private void login(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || password == null || login.length() == 0 || password.length() == 0) {
            errorString = "Required username and password!";
            return;
        }
        User user = userBusinessService.logIn(login, password);
        if (user != null) {
            hasError = false;
            HttpSession session = request.getSession();
            SessionUtils.storeLoginedUser(session, mapper.map(user, UserDTO.class));
            SessionUtils.storeUserCart(session, cartService.setCart(login));
            return;
        }
        errorString = "User Name or password invalid";
    }
}
