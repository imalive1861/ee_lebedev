<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<h3>Registration Page</h3>
<p style="color: red;">${errorString}</p>
<p style="color: green;">${okString}</p>
<form method="POST" action="${pageContext.request.contextPath}/registration">
    <table>
        <td>
            Pls, enter:
        </td>
        <tr>
            <td>Login</td>
            <td><input type="text" name="login" value= "${user.login}" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="password" value= "${user.password}" /> </td>
        </tr>
        <tr>
            <td>Full Name</td>
            <td><input type="text" name="name" value= "${user.name}" /> </td>
        </tr>
        <tr>
            <td>Address</td>
            <td><input type="text" name="address" value= "${user.address}" /> </td>
        </tr>
        <tr>
            <td>Phone Number</td>
            <td><input type="text" name="phoneNumber" value= "${user.phoneNumber}" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Registration" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
