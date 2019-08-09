<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div style="background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1>Flower Shop</h1>
    </div>
    <div style="float: right; padding: 10px; text-align: right;">
        <c:choose>
            <c:when test="${loginedUser.login != null}">
                Hello <b>${loginedUser.login}</b>
                <a href="${pageContext.request.contextPath}/logout"> Log out</a>
                <c:choose>
                    <c:when test="${!(loginedUser.login eq 'admin')}">
                        <br/>
                        Your cash: <b>${loginedUser.cash}</b>
                        <br/>
                        Your discount: <b>${loginedUser.discount} %</b>
                        <br/>
                    </c:when>
                </c:choose>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/login">Log in</a> or
                <a href="${pageContext.request.contextPath}/registration">Reg</a>
            </c:otherwise>
        </c:choose>
        <br/>
    </div>
</div>
