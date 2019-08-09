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
    <h3>Hello, ${loginedUser.login}!</h3>
    <c:choose>
        <c:when test="${!(loginedUser.login eq 'admin')}">
            Flower catalog is <a href="${pageContext.request.contextPath}/customer">here</a>.<br/>
            See the cart <a href="${pageContext.request.contextPath}/order">here</a>.<br/>
            See account information <a href="${pageContext.request.contextPath}/userInfo">here</a>.<br/>
        </c:when>
        <c:otherwise>
            Click <a href="${pageContext.request.contextPath}/admin">here</a> to open admin panel.<br/>
        </c:otherwise>
    </c:choose>
    Have a nice day! :)
</body>
</html>
