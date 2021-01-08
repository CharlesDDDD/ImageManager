  <%@page import="static java.lang.System.out"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form class="satellite_pic" action="${pageContext.request.contextPath}/AddPictureServlet?method=add" method="post" enctype="multipart/form-data"> //卫星图片表单
	<div class="satellite_pic">
		点击选择卫星图片上传
		<input type="file" name="sat_address" id="sat_pic"><br>
	</div>
	<div class="satellite_pic">
		<input type="text" name="sat_nationality" id="sat_pic" placeholder="请输入图片所属国别"><br>
	</div>
	<div class="satellite_pic">
		<input type="text" name="sat_position" id="sat_pic" placeholder="请输入图片具体位置"><br>
	</div>
	<div class="satellite_pic">
		<input type="text" name="sat_resolution" id="sat_pic" placeholder="请输入图片分辨率"><br>
	</div>
	<div class="satellite_pic">
		<input type="text" name="sat_la_longtitude" id="sat_pic" placeholder="请输入图片所在经纬度"><br>
	</div>
	<div class="satellite_pic">
		<input type="text" name="sat_actime" id="sat_pic" placeholder="请输入图片采集时间"><br>
	</div>
	<div class="satellite_pic">
		<input type="text" name="sat_scale" id="sat_pic" placeholder="请输入图片比例尺"><br>
	</div>
	<div class="satellite_pic">
		<input type="submit"  id="sat_pic" value="确认上传"><br>
	</div>
</form>
<%-- ${pageContext.request.contextPath}/AddPictureServlet?method=add --%>
<form class="landscape_pic" action="SearchServlet" method="post"> //风景图片表单
	<div class="landscape_pic">
		点击选择风景图片上传
		<input type="file" name="ls_address" id="ls_pic"><br>
	</div>
	<div class="landscape_pic">
		<input type="text" name="ls_name" id="ls_pic" placeholder="请输入图片名字"><br>
	</div>
	<div class="landscape_pic">
		<input type="text" name="ls_nationality" id="ls_pic" placeholder="请输入图片所属国别"><br>
	</div>
	<div class="landscape_pic">
		<input type="text" name="ls_resolution" id="ls_pic" placeholder="请输入图片分辨率"><br>
	</div>
	<div class="landscape_pic">
		<input type="text" name="ls_position" id="ls_pic" placeholder="请输入图片具体位置"><br>
	</div>
	
	<div class="landscape_pic">
		<input type="text" name="ls_actime" id="ls_pic" placeholder="请输入图片采集时间"><br>
	</div>
	
	<div class="satellite_pic">
		<input type="submit"  id="sat_pic" value="确认上传"><br>
	</div>
</form>

<form class="handsome_pic" action="DeleteServlet" method="post"> //帅哥图片表单
	<div class="handsome_pic">
		点击选择帅哥图片上传
		<input type="file" name="hd_address" id="hd_pic"><br>
	</div>
	<div class="handsome_pic">
		<input type="text" name="hd_name" id="hd_pic" placeholder="请输入帅哥的名字"><br>
	</div>
	<div class="handsome_pic">
		<input type="text" name="hd_age" id="hd_pic" placeholder="请输入帅哥的年龄"><br>
	</div>
	<div class="handsome_pic">
		<input type="text" name="hd_nationality" id="hd_pic" placeholder="请输入帅哥的国籍"><br>
	</div>
	<div class="handsome_pic">
		<input type="text" name="hd_height" id="hd_pic" placeholder="请输入帅哥的身高"><br>
	</div>
	<div class="handsome_pic">
		<input type="text" name="hd_fansnum" id="hd_pic" placeholder="请输入帅哥的粉丝数"><br>
	</div>
	<div class="satellite_pic">
		<input type="submit"  id="sat_pic" value="确认上传"><br>
	</div>
</form>
</body>
<%
String Msg = (String)session.getAttribute("operationMsg");
if(Msg != null) { //第三步：在需要弹窗的页面的尾部展现消息
%>
<script type="text/javascript" language="javascript">
alert("<%=Msg%>");
</script>
<%
session.removeAttribute("operationMsg"); 
}
%>

</html>