<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html  lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <title>图片管理</title>
    <script src="http://api.html5media.info/1.1.8/html5media.min.js"></script>
</head>
<body>
<div class="background">
    <video autoplay loop muted>
        <source src="pic/moji.mp4" type="video/mp4"/>
        Your Browser is no longer supported.
    </video>
</div>


<form  class="login" action="ZhuceServlet" method="post">
    <div class="logo_1"></div>
    <div class="form-item">
        <input type="text" name="register_user" id="reg" placeholder="用户名">
    </div>
    <div class="form-item">
        <input type="password" name="register_pswd" id="reg" placeholder="登录密码">
    </div>
    <div class="form-item">
        <input type="password" name="register_pswd_again" id="reg" placeholder="重新输入登录密码">
    </div>
    <div class="form-item">
        <input style="text-decoration: none ;color: #0a833d;text-align: center;" id="submit" type="submit" value="提交">
    </div>
    <div class="test">
        <p>欢迎来到图片管理系统</p>
    </div>
</form>

</body>
</html>