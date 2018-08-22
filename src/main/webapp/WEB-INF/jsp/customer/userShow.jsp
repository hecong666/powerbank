<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Show</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/jquery-1.7.1.min.js"></script>
	<link href="${pageContext.request.contextPath }/style/css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<style type="text/css">
		body {
	background-color: #f3f3f3;
}

.yj{
	padding:10px; width:auto; height:70px;
    border: 2px solid gray;
    -moz-border-radius: 15px; 
    -webkit-border-radius: 15px; 
    border-radius:15px;       
}
}
.mian {
	width: 100%;
	height: 100%;
}
.top {
	padding:10px;
	margin-top: 10px;
	font-weight:bold;
	font-size: 20px;
}
.top a{
	text-decoration:none;
	color: gray;
}
.top_right {
	display: inline;
	float: right;
}
.top_left {
	display: inline;
	float: left;
}
.center{
	margin-top:15%;
	margin-left: 20%;
	margin-right: 20%;	
}
.img{
	width:100%;
    height:100%;
}
.center_text{
	text-align: left;	
}

.next {
	text-align: center;
	margin: 20px 0;
}
.next button {
	width: 100%;
	height: 45px;
	padding: 0;
 	margin: 0;
 	background: #007BFF;
	color: #fff;
  	border: 0;
 	outline: none;	
	border-radius: 3px;
}		
	</style>
  </head>
  
  <body>
    <input id="user" type="hidden" value="${user.userName }">
	<div class="mian">
		<div class="top">
		
			
			<div class="top_left">余额：<span id="balance">${user.userAccount }</span> &nbsp;|&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/customer/recharge.html?userName=${user.userId}">充值</a></div>
			<div class="top_right">${user.userName }&nbsp;&nbsp;</div>
		</div>
		<div class="center">
			<div class="img">
				<img src="${pageContext.request.contextPath }/style/cdb.jpg"  width="100%" height="100%">
			</div>
			<div align="center">
			<div class="yj">
			<div class="center_text">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;☀编号：<span id="pid"> ${powerbank.pid } </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;☀ 开始租借时间：${startTime }&nbsp;&nbsp;<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;☀ 预计可用时间：${time }小时&nbsp;&nbsp;<br>
			</div>
			</div>
			<div class="next">  
				<button id="end" >归还</button>
			</div>
		</div>
	</div>
   </div>
  </body>
  <script type="text/javascript">
  	$(function() {
  		var usr=$("#user").val();
  		var pid=$("#pid").text();
		$("#end").click(function() {
			location.href="api-borrow-returnBicycle?pid=" + pid + "&userName=" + usr;
		})
	})
  </script>
</html>
