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
            </c:when>
            <c:otherwise>
                Log in, pls!
            </c:otherwise>
        </c:choose>
        <br/>
    </div>
</div>
