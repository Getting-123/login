<%@ page import="java.util.ArrayList" %>
<%@ page import="method.user" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/4/23
  Time: 0:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    ArrayList<user>p=new ArrayList<>();
    p.add(new user(1,"梅雪松","1714541828","174544845@qq.com"));
    session.setAttribute("arr",p);
%>
<c:forEach items="${sessionScope.arr}" var="arr">
    <h>${arr.id}</h>
    <h>${arr.name}</h>
    <h>${arr.password}</h>
    <h>${arr.email}</h>

    <table>

    </table>
</c:forEach>


</body>
</html>
