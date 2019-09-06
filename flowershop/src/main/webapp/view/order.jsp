<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Order</title>
</head>
<body>
    <jsp:include page="_header.jsp"/>
    <jsp:include page="_menu.jsp"/>
    <p style="color: red;">${outputString}</p>
    <table border="1" cellpadding="5" cellspacing="1" >
        <tr>
            <th>Flower name</th>
            <th>Number</th>
            <th>Sum Price</th>
            <th>Delete position</th>
        </tr>
        <c:forEach items="${userCart.carts}" var="cart" >
            <tr>
                <td>${cart.flower.name}</td>
                <td>${cart.number}</td>
                <td>${cart.sumPrice}</td>
                <td>
                    <form method="POST" action="${pageContext.request.contextPath}/order">
                        <input type="hidden" name="flowerId" value="${cart.flower.id}"/>
                        <input type="submit" name="deletePosition" value= "X" />
                    </form>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <c:choose>
                <c:when test="${userCart.carts.isEmpty()}">
                    <td colspan="5">Cart is empty</td>
                </c:when>
                <c:otherwise>
                    <td>
                        <form method="POST" action="${pageContext.request.contextPath}/order">
                            <input type="submit" name="createOrder" value= "Create order" />
                        </form>
                    </td>
                    <td>Total price:</td>
                    <td>${userCart.sumPrice}</td>
                    <td>
                        <form method="POST" action="${pageContext.request.contextPath}/order">
                            <input type="submit" name="cleanCart" value= "Clean cart" />
                        </form>
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
    </table>
    <table align="right" border cellpadding="5" cellspacing="1">
        <tr>
        <th colspan="5" align="center">Orders</th>
        </tr>
        <tr>
            <th>Customer</th>
            <th>Sum Price</th>
            <th>Date Create</th>
            <th>Date Close</th>
            <th>Status</th>
        </tr>
        <c:forEach items="${loginedUser.orders}" var="order" >
            <tr>
                <td>${order.user.login}</td>
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
                            <th>Price of Flower</th>
                        </tr>
                        <c:forEach items="${order.carts}" var="cart" >
                            <tr>
                                <td>${cart.flower.name}</td>
                                <td>${cart.number}</td>
                                <td>${cart.sumPrice}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
