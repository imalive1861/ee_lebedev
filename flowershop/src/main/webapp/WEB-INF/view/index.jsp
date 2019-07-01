<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome!</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<h3>Hello,
    <c:choose>
        <c:when test="${loginedUser.login != null}">
            ${loginedUser.login}!
        </c:when>
        <c:otherwise>
            visitor. To use this site you need
            <a href="${pageContext.request.contextPath}/login">Log in</a> or
            <a href="${pageContext.request.contextPath}/registration">Reg</a>
        </c:otherwise>
    </c:choose>
</h3>
<br />
</body>
</html>
