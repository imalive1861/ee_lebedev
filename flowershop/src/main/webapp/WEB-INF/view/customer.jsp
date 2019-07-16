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
    <p style="color: red;">${errorString}</p>
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Code</th>
          <th>Name</th>
          <th>Price</th>
          <th>Number</th>
          <th>Number to card</th>
          <th>Add to card</th>
       </tr>
       <c:forEach items="${flowerList}" var="flower" >
          <tr>
             <td>${flower.id}</td>
             <td>${flower.name}</td>
             <td>${flower.price}</td>
             <td>${flower.number}</td>
             <form method="GET" action="${pageContext.request.contextPath}/customer">
                 <td><input type="number" min="0" name="numberToCard" value="0"/></td>
                 <td>
                    <input type="hidden" name="flowerId" value="${flower.id}"/>
                    <input type="submit" value= "Add to card" />
                 </td>
             </form>
          </tr>
       </c:forEach>
    </table>
</body>
</html>
