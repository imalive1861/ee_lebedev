package com.accenture.flowershop.fe.filters;

import com.accenture.flowershop.be.utils.SecurityUtils;
import com.accenture.flowershop.be.utils.SessionUtils;
import com.accenture.flowershop.be.utils.UserRoleRequestWrapper;
import com.accenture.flowershop.fe.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Фильтр безопасности веб-приложения.
 */
@WebFilter(filterName = "SecurityFilter", urlPatterns = "/*")
public class SecurityFilter implements Filter {

    public SecurityFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();
        UserDTO loginedUser = SessionUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/login")
                || servletPath.equals("/registration")
                || servletPath.equals("/ws")
                || servletPath.equals("/rest")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser == null) {
            response.sendRedirect(wrapRequest.getContextPath() + "/login");
            return;
        }

        String userName = loginedUser.getLogin();
        String role = loginedUser.getRole().name();
        wrapRequest = new UserRoleRequestWrapper(userName, role, request);

        if (SecurityUtils.isSecurityPage(request)) {
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {

                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/accessDenied.jsp");

                dispatcher.forward(request, response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
