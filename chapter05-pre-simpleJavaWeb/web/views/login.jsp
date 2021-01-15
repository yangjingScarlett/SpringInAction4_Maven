<%--
  Created by IntelliJ IDEA.
  User: 212719065
  Date: 1/15/2021
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form action="/simpleJavaWeb/login" method="post">
    <table align="center" width="40%" style="margin-left: 30px">
        <tr>
            <td>账户名</td>
            <td><input type="text" name="accountName" id="accountName"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
            <td>确认密码</td>
            <td><input type="password" name="password1" id="password1"></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="登录">
                <input type="button" value="注册"
                       onclick="location='<c:url value="/views/register.jsp"/>'">
            </td>
        </tr>
    </table>
</form>
</body>
<p>${msg}</p>
</html>
