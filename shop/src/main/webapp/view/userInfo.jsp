<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<h3>Hello: ${loginedUser.login}</h3>

User Name: <b>${loginedUser.name}</b><br/>
Address: <b>${loginedUser.address}</b><br/>
Phone Number: <b>${loginedUser.phoneNumber}</b><br/>
Score: <b>${loginedUser.cash}</b><br/>
Sale: <b>${loginedUser.discount}</b><br/>

</body>
</html>
