<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="UTF-8">
        <title>充值</title>
        <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"/>
        <meta content="yes" name="apple-mobile-web-app-capable"/>
        <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
        <meta content="telephone=no" name="format-detection"/>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="css/recharge.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<style type="text/css">
.top {
	padding: 10px;
	margin-top: 10px;
	font-weight: bold;
	font-size: 20px;
}
</style>
</head>
    <body>
      <div class="top">
      	<a href="javascript:history.back(-1)">返回</a>
      </div>
        <section class="aui-flexView">
            <section class="aui-scrollView">
                <div class="aui-recharge-box">
                    <div class="aui-recharge-iphone">
                        <div class="aui-well-bd">
                            <p>
                               <input type="text" name="userPhone" id="userPhone" value="${user.userName }">
                            </p>
                        </div>
                    </div>
                    <div class="aui-cell-box">
                        <h3>余额充值</h3>
                        <div class="aui-grids">
                            <a href="javascript:;" class="aui-grids-item this-card">
                                <span id="sum">30元</span>
                            </a>
                            <a href="javascript:;" class="aui-grids-item">
                                <span id="sum">50元</span>
                            </a>
                            <a href="javascript:;" class="aui-grids-item">
                                <span id="sum">100元</span>
                            </a>
                            <a href="javascript:;" class="aui-grids-item">
                                <span id="sum">200元</span>
                            </a>
                            <a href="javascript:;" class="aui-grids-item">
                                <span id="sum">300元</span>
                            </a>
                            <a href="javascript:;" class="aui-grids-item">
                                <span id="sum">500元</span>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="next">  			
					<button id="Confirm">确认充值</button>
				</div>
            </section>
        </section>
        <script type="text/javascript">
        $(function(){
        	var str=$("#sum").text();
        $('.aui-grids-item').click(function(e){
                $(this).addClass('this-card').siblings().removeClass('this-card');
                $('#type-amount').html($(this).find('.cardAmount').html());
                str=$(this).children().text();   
            });
            $("#Confirm").click(function(){
            	var userPhone=$("#userPhone").val();
            	/* var str ="4500元"; */
            	var num = parseInt(str);
    			/* var num = str.replace(/[^0-9]/ig,""); */
            	location.href="../api-user-recharge?rechargeAmount=" + num + "&userName=" + userPhone;
            }); 
        })           
                   
        </script>
    </body>
</html>