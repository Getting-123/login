<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Arrays ,java.net.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>index</title>
</head>
<body>
<%
    response.setContentType("text/html;charset=utf-8");

    SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间

    sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记

    Date date = new Date();// 获取当前时间

   String value = URLEncoder.encode(sdf.format(date),"utf-8");

%>


<%
    //2 创建Cookies实例对象
    Cookie cookiec = new Cookie("time",value);
    //3 将Cookies实例对象,添加到Response对象中

    cookiec.setMaxAge(60*100);//发送Cookie到客户端

    response.addCookie(cookiec);

    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for (Cookie cookie : cookies){
            String name = cookie.getName();
            if(name.equals("time")){
                //6 打印Cookie名称为id的Cookie值
               out.println("上次登陆时间："+URLDecoder.decode(cookie.getValue(),"utf-8"));
            }
        }
    }

%>

</body>
</html>