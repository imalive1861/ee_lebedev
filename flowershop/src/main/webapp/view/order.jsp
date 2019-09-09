<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Order</title>
    <style type="text/css">
        .tabs { width: 100%; padding: 0px; margin: 0 auto; }
        .tabs>input { display:none; }
        .tabs>div {
            display: none;
            padding: 12px;
            border: 1px solid #C0C0C0;
            background: #FFFFFF;
        }
        .tabs>label {
            display: inline-block;
            padding: 7px;
            margin: 0 -5px -1px 0;
            text-align: center;
            color: #666666;
            border: 1px solid #C0C0C0;
            background: #E0E0E0;
            cursor: pointer;
        }
        .tabs>input:checked + label {
            color: #000000;
            border: 1px solid #C0C0C0;
            border-bottom: 1px solid #FFFFFF;
            background: #FFFFFF;
        }
        #tab_1:checked ~ #txt_1,
        #tab_2:checked ~ #txt_2,
        #tab_3:checked ~ #txt_3 { display: block; }
    </style>
</head>
<body>
    <jsp:include page="_header.jsp"/>
    <jsp:include page="_menu.jsp"/>
    <p style="color: red;">${outputString}</p>
    <table border="1" cellpadding="5" cellspacing="1" >
        <tr>
            <th>Flower name</th>
            <th>Number</th>
            <th>Sum Price Without Discount</th>
            <th>Sum Price With Discount</th>
            <th>Delete position</th>
        </tr>
        <c:forEach items="${userCart.carts}" var="cart" >
            <tr>
                <td>${cart.flower.name}</td>
                <td>${cart.number}</td>
                <td>${cart.sumPriceWithoutDiscount}</td>
                <td>${cart.sumPriceWithDiscount}</td>
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
                    <td colspan="2">Total price:</td>
                    <td>${userCart.sumPriceWithoutDiscount}</td>
                    <td>${userCart.sumPriceWithDiscount}</td>
                    <td>
                        <form method="POST" action="${pageContext.request.contextPath}/order">
                            <input type="submit" name="cleanCart" value= "Clean cart" />
                        </form>
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
    </table>
    <br>
    <form method="POST" action="${pageContext.request.contextPath}/order">
        <input type="submit" name="createOrder" value= "Create order" />
    </form>
    <div class="tabs">
        <input type="radio" name="inset" value="" id="tab_1" checked>
        <label for="tab_1">Opened Orders</label>

        <input type="radio" name="inset" value="" id="tab_2">
        <label for="tab_2">Paid Orders</label>

        <input type="radio" name="inset" value="" id="tab_3">
        <label for="tab_3">Closed Orders</label>

        <div id="txt_1">
            <table border cellpadding="5" cellspacing="1">
                <tr>
                    <th colspan="7" align="center">Orders</th>
                </tr>
                <tr>
                    <th>Customer</th>
                    <th>Sum Price Without Discount</th>
                    <th>Sum Price With Discount</th>
                    <th>Date Create</th>
                    <th>Date Close</th>
                    <th>Status</th>
                    <th>Pay</th>
                </tr>
                <c:forEach items="${loginedUser.orders}" var="order" >
                    <c:choose>
                        <c:when test="${order.status eq 'OPENED'}">
                            <tr>
                                <td>${order.user.login}</td>
                                <td>${order.sumPriceWithoutDiscount}</td>
                                <td>${order.sumPriceWithDiscount}</td>
                                <td>${order.dateCreate}</td>
                                <td>${order.dateClose}</td>
                                <td>${order.status}</td>
                                <td>
                                    <form method="POST" action="${pageContext.request.contextPath}/order">
                                        <input type="hidden" name="orderId" value="${order.id}"/>
                                        <input type="submit" name="payOrder" value= "Pay" />
                                    </form>
                                </td>
                            </tr>
                            <tr align="right">
                                <td colspan="7">
                                    <table border>
                                        <tr>
                                            <th>Flower Name</th>
                                            <th>Number of Flower</th>
                                            <th>Sum Price Without Discount</th>
                                            <th>Sum Price With Discount</th>
                                        </tr>
                                        <c:forEach items="${order.carts}" var="cart" >
                                            <tr>
                                                <td>${cart.flower.name}</td>
                                                <td>${cart.number}</td>
                                                <td>${cart.sumPriceWithoutDiscount}</td>
                                                <td>${cart.sumPriceWithDiscount}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </table>
        </div>
        <div id="txt_2">
            <table border cellpadding="5" cellspacing="1">
                <tr>
                  <th colspan="6" align="center">Orders</th>
                </tr>
                <tr>
                  <th>Customer</th>
                  <th>Sum Price Without Discount</th>
                  <th>Sum Price With Discount</th>
                  <th>Date Create</th>
                  <th>Date Close</th>
                  <th>Status</th>
                </tr>
                <c:forEach items="${loginedUser.orders}" var="order" >
                    <c:choose>
                        <c:when test="${order.status eq 'PAID'}">
                            <tr>
                                <td>${order.user.login}</td>
                                <td>${order.sumPriceWithoutDiscount}</td>
                                <td>${order.sumPriceWithDiscount}</td>
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
                                            <th>Sum Price Without Discount</th>
                                            <th>Sum Price With Discount</th>
                                        </tr>
                                        <c:forEach items="${order.carts}" var="cart" >
                                            <tr>
                                                <td>${cart.flower.name}</td>
                                                <td>${cart.number}</td>
                                                <td>${cart.sumPriceWithoutDiscount}</td>
                                                <td>${cart.sumPriceWithDiscount}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </table>
        </div>
        <div id="txt_3">
            <table border cellpadding="5" cellspacing="1">
                <tr>
                    <th colspan="6" align="center">Orders</th>
                </tr>
                <tr>
                    <th>Customer</th>
                    <th>Sum Price Without Discount</th>
                    <th>Sum Price With Discount</th>
                    <th>Date Create</th>
                    <th>Date Close</th>
                    <th>Status</th>
                </tr>
                <c:forEach items="${loginedUser.orders}" var="order" >
                    <c:choose>
                        <c:when test="${order.status eq 'CLOSED'}">
                            <tr>
                                <td>${order.user.login}</td>
                                <td>${order.sumPriceWithoutDiscount}</td>
                                <td>${order.sumPriceWithDiscount}</td>
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
                                            <th>Sum Price Without Discount</th>
                                            <th>Sum Price With Discount</th>
                                        </tr>
                                        <c:forEach items="${order.carts}" var="cart" >
                                            <tr>
                                                <td>${cart.flower.name}</td>
                                                <td>${cart.number}</td>
                                                <td>${cart.sumPriceWithoutDiscount}</td>
                                                <td>${cart.sumPriceWithDiscount}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
