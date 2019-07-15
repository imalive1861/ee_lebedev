<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div style="padding: 5px;">
    <a href="${pageContext.request.contextPath}/">Home</a>
    |
    <c:choose>
        <c:when test="${loginedUser.login != null}">
            <c:choose>
                <c:when test="${!(loginedUser.login eq 'admin')}">
                <a href="${pageContext.request.contextPath}/customer">Flowers</a>
                |
                <a href="${pageContext.request.contextPath}/order">Card</a>
                |
                </c:when>
                <c:otherwise>
                <a href="${pageContext.request.contextPath}/admin">Admin Panel</a>
                |
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <a href="${pageContext.request.contextPath}/userInfo">My Account Info</a>
</div>
