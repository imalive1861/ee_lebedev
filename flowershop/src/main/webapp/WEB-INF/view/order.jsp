<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Order</title>
</head>
<body>
    <jsp:include page="_header.jsp"/>
        <jsp:include page="_menu.jsp"/>
        <p style="color: red;">${errorString}</p>
        <table border="1" cellpadding="5" cellspacing="1" >
            <tr>
                <th>Flower id</th>
                <th>Flower name</th>
                <th>Number</th>
                <th>Sum Price</th>
            </tr>
            <c:forEach items="${userCard}" var="card" >
                <tr>
                    <td>${card.flowerDTO.id}</td>
                    <td>${card.flowerDTO.name}</td>
                    <td>${card.number}</td>
                    <td>${card.sumPrice}</td>
                </tr>
            </c:forEach>
            <tr>
                <c:choose>
                    <c:when test="${allSum == 0.00}">
                        <td colspan="4">Card is empty</td>
                    </c:when>
                    <c:otherwise>
                        <td colspan="2">
                            <form method="GET" action="${pageContext.request.contextPath}/order">
                                <input type="hidden" name="createOrder" value="1"/>
                                <input type="submit" value= "Create order" />
                            </form>
                        </td>
                        <td>Total price:</td>
                        <td>${allSum}</td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </table>
</body>
</html>
