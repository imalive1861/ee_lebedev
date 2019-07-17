<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Panel</title>
</head>
<body>
    <jsp:include page="_header.jsp"/>
    <jsp:include page="_menu.jsp"/>
    <p style="color: red;">${errorString}</p>
    <table border="1" cellpadding="5" cellspacing="1" >
        <tr>
            <th>Code</th>
            <th>Sum Price</th>
            <th>Date Create</th>
            <th>Date Close</th>
            <th>Status</th>
            <th>Close?</th>
        </tr>
        <c:forEach items="${orderList}" var="order" >
            <tr>
                <td>${order.id}</td>
                <td>${order.sumPrice}</td>
                <td>${order.dateCreate}</td>
                <td>${order.dateClose}</td>
                <td>${order.status}</td>
                <form method="GET" action="${pageContext.request.contextPath}/order">
                    <td>
                        <input type="hidden" name="orderId" value="${order.id}"/>
                        <input type="submit" value= "Close order"/>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>
</body>
</html>