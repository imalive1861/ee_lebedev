package com.accenture.flowershop.be.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

/**
 * Расширение для HTTPServletRequest, которое переопределяет getUserPrincipal () и isUserInRole ().
 */
public class UserRoleRequestWrapper extends HttpServletRequestWrapper {
    private String user;
    private String roles;
    private HttpServletRequest realRequest;

    public UserRoleRequestWrapper(String user, String roles, HttpServletRequest request) {
        super(request);
        this.user = user;
        this.roles = roles;
        this.realRequest = request;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (roles == null) {
            return this.realRequest.isUserInRole(role);
        }
        return roles.contains(role);
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.user == null) {
            return realRequest.getUserPrincipal();
        }

        return () -> user;
    }
}
