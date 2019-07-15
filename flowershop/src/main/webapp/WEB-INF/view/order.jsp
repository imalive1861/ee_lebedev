<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="_header.jsp"/>
        <jsp:include page="_menu.jsp"/>
        <h3>Hello, ${loginedUser.login}!</h3>
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
                 <td>${card.flowerId}</td>
                 <td>${card.flowerName}</td>
                 <td>${card.number}</td>
                 <td>${card.sumPrice}</td>
              </tr>
           </c:forEach>
        </table>
</body>
</html>
