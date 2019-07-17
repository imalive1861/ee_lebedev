package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.be.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@Controller
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserBusinessService userBusinessService;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserDTO user = null;
        boolean hasError = false;
        String errorString = null;

        if (login == null || password == null || login.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            // Найти user.
            try{
                user = userBusinessService.getUserDTO(login, password);

                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            }catch (NullPointerException e){
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // В случае, если есть ошибка,
        // forward (перенаправить) к /WEB-INF/view/login.jsp
        if (hasError) {
            user = new UserDTO();
            user.setLogin(login);
            user.setPassword(password);

            // Сохранить информацию в request attribute перед forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            // Forward (перенаправить) к странице /WEB-INF/view/login.jsp
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp");

            dispatcher.forward(request, response);
        }
        // В случае, если нет ошибки.
        // Сохранить информацию пользователя в Session.
        // И перенаправить к странице userInfo.
        else {
            HttpSession session = request.getSession();
            SessionUtils.storeLoginedUser(session, user);

            // Redirect (Перенаправить) на страницу /admin или /customer
            if (user.getRole().equals("ADMIN")){
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                response.sendRedirect(request.getContextPath() + "/customer");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp");
        dispatcher.forward(request, response);
    }
}
