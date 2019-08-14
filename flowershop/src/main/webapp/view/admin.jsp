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
    <table border cellpadding="5" cellspacing="1">
        <tr>
        <th colspan="7" align="center">Paid Orders</th>
        </tr>
        <tr>
            <th>Code</th>
            <th>Customer</th>
            <th>Sum Price</th>
            <th>Date Create</th>
            <th>Date Close</th>
            <th>Status</th>
            <th>Close?</th>
        </tr>
        <c:forEach items="${orderList}" var="order" >
            <c:choose>
                <c:when test="${order.status eq 'PAID'}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.userId.login}</td>
                        <td>${order.sumPrice}</td>
                        <td>${order.dateCreate}</td>
                        <td>${order.dateClose}</td>
                        <td>${order.status}</td>
                        <form method="GET" action="${pageContext.request.contextPath}/admin">
                            <td>
                                <input type="hidden" name="orderId" value="${order.id}"/>
                                <input type="submit" value= "Close order"/>
                            </td>
                        </form>
                    </tr>
                    <tr align="right">
                        <td colspan="7">
                            <table border>
                                <tr>
                                    <th>Flower Name</th>
                                    <th>Number of Flower</th>
                                </tr>
                                <c:forEach items="${order.carts}" var="cart" >
                                    <tr>
                                        <td>${cart.flower.name}</td>
                                        <td>${cart.number}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
    <table align="right" border cellpadding="5" cellspacing="1">
        <tr>
        <th colspan="6" align="center">Closed Orders</th>
        </tr>
        <tr>
            <th>Code</th>
            <th>Customer</th>
            <th>Sum Price</th>
            <th>Date Create</th>
            <th>Date Close</th>
            <th>Status</th>
        </tr>
        <c:forEach items="${orderList}" var="order" >
            <c:choose>
                <c:when test="${order.status eq 'CLOSED'}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.userId.login}</td>
                        <td>${order.sumPrice}</td>
                        <td>${order.dateCreate}</td>
                        <td>${order.dateClose}</td>
                        <td>${order.status}</td>
                    </tr>
                    <tr align="right">
                        <td colspan="6">
                            <table border>
                                <tr>
                                    <th>Flower Name</th>
                                    <th>Number of Flower</th>
                                </tr>
                                <c:forEach items="${order.carts}" var="cart" >
                                    <tr>
                                        <td>${cart.flower.name}</td>
                                        <td>${cart.number}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
</body>
</html>