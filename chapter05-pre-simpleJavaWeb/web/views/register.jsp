<%--
  Created by IntelliJ IDEA.
  User: 212719065
  Date: 1/15/2021
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="/simpleJavaWeb/register" method="post">
    <table align="center" width="40%" style="margin-left: 30px">
        <tr>
            <td width="100px">账户名</td>
            <td>
                <input type="text" id="accountName" name="accountName"/>
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td>
                <input type="text" id="password" name="password"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" name="register" value="注册"/>
            </td>
        </tr>
    </table>
</form>
<p>${msg}</p>
</body>
</html>
