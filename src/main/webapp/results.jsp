<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Babudzhi
  Date: 22.05.2018
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results of Data Base</title>
</head>
<body>
<%
    Connection db = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
    PreparedStatement query = db.prepareStatement("SELECT * FROM TEST123");
    ResultSet rs = query.executeQuery();
    PrintWriter outs = response.getWriter();


    while (rs.next()) {
    out.println(String.format("%s %s %s ",rs.getString(1), rs.getString(2), rs.getString(3)));
    out.println("<br>");
    }
%>

</body>
</html>
