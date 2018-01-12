<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spitters</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
</head>
<body>
<div class="listTitle">
    <h1>Recent Spitters</h1>
    <ul class="spitterList">
        <c:forEach items="${spitterList}" var="spitter">
            <li id="spittle_<c:out value="spitter.id"/>">
                <div class="spittleMessage"><c:out value="${spitter.email}"/></div>
                <div>
                    <span class="spittleTime"><c:out value="${spitter.username}"/></span>
                    <span class="spittleLocation">(<c:out value="${spitter.firstName}"/>, <c:out value="${spitter.lastName}"/>)</span>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>