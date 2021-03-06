<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Registration</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.js"></script>
    <script type="text/javascript">
    <%@include file="/resources/js/jqueryscripts.js"%>
    </script>
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
            <td><input type="text" id="login" name="login" value= "${user.login}" /> </td>
            <td><div id="errorlogin"></div><div style="color: red;">${errorlogin}</div></td>
        </tr>
        <tr>
            <td>New password</td>
            <td><input type="text" id="newPassword" name="newPassword" value= "" /> </td>
            <td><div id="errorpassword"></div><div style="color: red;">${errorpassword}</div></td>
        </tr>
        <tr>
            <td>Confirm password</td>
            <td><input type="text" id="confirmPassword" name="confirmPassword" value= "" /> </td>
            <td><div id="errorConfirmPassword"></div><div style="color: red;">${errorConfirmPassword}</div></td>
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
                <input type="submit" id="reg" name="regButton" value="Registration" disabled/>
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
