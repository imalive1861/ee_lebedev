package com.accenture.flowershop.fe.filters;

import com.accenture.flowershop.be.utils.SecurityUtils;
import com.accenture.flowershop.be.utils.UserRoleRequestWrapper;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.be.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/*")
public class SecurityFilter implements Filter {

    public SecurityFilter(){
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        // Информация пользователя сохранена в Session
        // (После успешного входа в систему).
        UserDTO loginedUser = MyUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/login") || servletPath.equals("/registration")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            String userName = loginedUser.getLogin();
            String role = loginedUser.getRole();
            wrapRequest = new UserRoleRequestWrapper(userName, role, request);
        }

        if (SecurityUtils.isSecurityPage(request)) {

            if (loginedUser == null) {
                String requestUri = request.getRequestURI();

                int redirectId = MyUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }

            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/accessDeniedView.jsp");

                dispatcher.forward(request, response);
                return;
            }
            chain.doFilter(wrapRequest, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
