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

            <p style="color: red;">${errorString}</p>

                <table border="1" cellpadding="5" cellspacing="1" >
                   <tr>
                      <th>Code</th>
                      <th>Name</th>
                      <th>Price</th>
                      <th>Number</th>
                      <th>Add to card</th>
                   </tr>
                   <c:forEach items="${flowerList}" var="flowers" >
                      <tr>
                         <td>${flowers.id}</td>
                         <td>${flowers.name}</td>
                         <td>${flowers.price}</td>
                         <td>${flowers.number}</td>
                         <td>
                            <a href="/">Add</a>
                         </td>
                      </tr>
                   </c:forEach>
                </table>
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
