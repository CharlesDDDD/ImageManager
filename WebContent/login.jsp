<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <title>图片管理</title>
    <script src="http://api.html5media.info/1.1.8/html5media.min.js"></script>
</head>
<body>
<div class="background">
    <video autoplay loop muted>
        <source src="pic/moji.mp4" type="video/mp4"  />
        Your Browser is no longer supported.
    </video>
</div>


<form class="login" action="DengluServlet" method="post">
    <div class="logo_1"></div>
    <div class="form-item">
        <input id="username" type="text" autocomplete="off" placeholder="用户名" name = "user">
    </div>
    <div class="form-item">
        <input id="password" type="password" autocomplete="off" placeholder="登录密码" name = "pswd">
    </div>
    <div class="form-item" >
        <input style="color: #0a833d;"id="submit" type="submit" autocomplete="off"value="登录">
    </div>
    <div class="reg-bar">
        <a href="register.jsp">立即注册</a>
    </div>
    <div class="test">
        <p>欢迎来到图片管理系统</p>
    </div>
</form>

</body>
<script type="text/javascript" src="./assets/scripts/main.js"></script></body>
<%
String Msg = (String)session.getAttribute("loginMsg");
if(Msg != null) { //第三步：在需要弹窗的页面的尾部展现消息
%>
<script type="text/javascript" language="javascript">
alert("<%=Msg%>");
</script>
<%
session.removeAttribute("loginMsg"); 
}
%>
</html>
</html>