<%--
  Created by IntelliJ IDEA.
  User: HuiJa
  Date: 2017/7/28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>添加单车</title>
    <%@include file="../common/head.jsp" %>
</head>

<body data-type="widgets">
<script src="${pageContext.request.contextPath }/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 加载顶部导航栏 -->
    <jsp:include page="../common/header.jsp"/>
    <!-- 加载侧边导航栏 -->
    <jsp:include page="../common/list.jsp"/>

    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">
        <div class="row-content am-cf">
            <div class="row">

                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="widget am-cf">
                        <div class="widget-head am-cf">
                            <div class="widget-title am-fl">添加充电宝</div>
                            <div class="widget-function am-fr">
                            </div>
                        </div>
                        <div class="widget-body am-fr">
                            <form autocomplete="off" class="am-form tpl-form-line-form" action="admin-bicycle-addbicycle-execute" method="post">

                               

                                <div class="am-form-group">
                                    <label for="bicycle-statement" class="am-u-sm-3 am-form-label">单车状态</label>
                                    <div class="am-u-sm-9">
                                        <input type="number" max="1" min="-1" class="tpl-form-input" id="bicycle-statement" placeholder="" name="bicycleStatement">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="bicycle-n" class="am-u-sm-3 am-form-label">添加数量</label>
                                    <div class="am-u-sm-9">
                                        <input type="number" min="1" class="tpl-form-input" id="bicycle-n" placeholder="" name="n">
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="submit" class="am-btn am-btn-primary tpl-btn-bg-color-success ">提交</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath }/assets/js/amazeui.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/app.js"></script>
</body>

</html>

