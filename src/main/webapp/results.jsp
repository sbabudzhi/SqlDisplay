<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Babudzhi
  Date: 22.05.2018
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Results of Data Base</title>
</head>
<body>
<jsp:useBean id="listResults" class="java.util.ArrayList" scope="session"/>
<b>Список введеных человек:</b>
<table>
    <tbody>
    <c:forEach items="${listResults}" var="test">
     <p>
        <c:out value="${test}"/>
     </p>
    </c:forEach>
    </tbody>
</table>



</body>
</html>
