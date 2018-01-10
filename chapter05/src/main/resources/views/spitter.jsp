<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />">
</head>
<body>
<h1>Your Spitter</h1>
<c:out value="${spitter.username}"/><br/>
<c:out value="${spitter.firstName}"/> <c:out value="${spitter.lastName}"/><br/>
<c:out value="${spitter.email}"/>
</body>
</html>