<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Flowers</title>
</head>
<body>
    <jsp:include page="_header.jsp"/>
    <jsp:include page="_menu.jsp"/>
    <a href="${pageContext.request.contextPath}/order">Go to Cart</a><br/>
    <p style="color: red;">${errorString}</p>
    <b>Search</b>
    <br/>
    <form method="GET" action="${pageContext.request.contextPath}/customer">
        Enter name of flower: <input type="text" name="searchFlowerByName" value="${searchFlowerByName}"/>
        <br/>
        Min price: <input type="number" name="minFlowerPrice" value="${minFlowerPrice}"/>
        Max price: <input type="number" name="maxFlowerPrice" value="${maxFlowerPrice}"/>
        <br/>
        <br/>
        <input type="submit" name="searchClick" value= "Search" />
        <input type="submit" name="cleanSearchClick" value= "Clean search form" />
    </form>
    <br/>
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Name</th>
          <th>Price</th>
          <th>Number</th>
          <th>Number to cart</th>
          <th>Add to cart</th>
       </tr>
       <c:forEach items="${flowerList}" var="flower" >
          <tr>
             <td>${flower.name}</td>
             <td>${flower.price}</td>
             <td>${flower.number}</td>
             <form method="GET" action="${pageContext.request.contextPath}/customer">
                 <td><input type="number" min="0" name="numberToCart" value="0"/></td>
                 <td>
                    <input type="hidden" name="flowerId" value="${flower.id}"/>
                    <input type="submit" name="addToCardButton" value= "Add to cart" />
                 </td>
             </form>
          </tr>
       </c:forEach>
    </table>
</body>
</html>
