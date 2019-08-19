package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.fe.servicedto.cartdto.CartService;
import com.accenture.flowershop.be.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logOut(request);
        response.sendRedirect(request.getContextPath() + "/");
    }

    @Transactional
    private void logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        cartService.clear(SessionUtils.getLoginedUser(session).getLogin());
        session.invalidate();
    }
}
