<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<script>
			function jump_1(){
				window.location.href="Registration.jsp";
			}
		</script>

		<script>
			function jump_2(){
				window.location.href="Reset.jsp";
			}
		</script>
		<!-- Page title -->
		<title>imooc - Login</title>
		<!-- End of Page title -->
		<!-- Libraries -->
		<link type="text/css" href="css/login.css" rel="stylesheet" />	
		<link type="text/css" href="css/smoothness/jquery-ui-1.7.2.custom.html" rel="stylesheet" />	
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="js/easyTooltip.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.7.2.custom.min.js"></script>
		<!-- End of Libraries -->	
	</head>
	<body>
	<div id="container">
		<div class="logo">
			<a href="#"><img src="assets/logo.png" alt="" /></a>
		</div>
		<div id="box">
			<form action="Servletlogin" method="post">
			<p class="main">
				<label>用户名: </label>
				<input name="username" value="" /> 
				<label>密码: </label>
				<input type="password" name="password" value="">
			</p>
			<p class="space">
				<input type="submit" value="登录" class="login" style="cursor: pointer;"/>
				<input type="button" value="注册" class="login" style="cursor: pointer;" onclick="jump_1()">
				<input type="button" value="重置" class="login" style="cursor: pointer;" onclick="jump_2()">
			</p>
			</form>
		</div>
	</div>
	</body>
</html>