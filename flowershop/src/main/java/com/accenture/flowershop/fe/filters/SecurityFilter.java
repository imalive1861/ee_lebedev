package com.accenture.flowershop.fe.filters;

import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.be.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/*")
public class SecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        // Информация пользователя сохранена в Session
        // (После успешного входа в систему).
        UserDTO loginedUser = MyUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }

        if (loginedUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.setAttribute("user", loginedUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
        dispatcher.forward(request, response);

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
