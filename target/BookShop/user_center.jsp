<%--
  Created by IntelliJ IDEA.
  User: vili
  Date: 2019/8/27
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>个人中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/cart.js"></script>
</head>
<body>

<jsp:include page="/header.jsp">
    <jsp:param value="4" name="flag"/>
</jsp:include>
<c:if test="${empty user}"><%response.sendRedirect("index.action");%></c:if>
<!--account-->
<div class="account">
    <div class="container">
        <div class="register">
            <c:if test="${!empty msg }">
                <div class="alert alert-success">${msg }</div>
            </c:if>
            <c:if test="${!empty failMsg }">
                <div class="alert alert-danger">${failMsg }</div>
            </c:if>

            <div class="register-top-grid">
                <h3>个人中心</h3>
                <form action="change_phone_and_address.action" method="post">
                    <!-- 收货信息 start -->
                    <input type="hidden" name="uid" value="${user.uid}"/>
                    <h4>收货信息</h4>
                    <div class="input">
                        <span>收货人<label></label></span>
                        <input type="text" name="urealname" value="${user.urealname }" readonly="readonly" />
                    </div>
                    <div class="input">
                        <span>收货电话</span>
                        <input type="text" name="uphone" value="${user.uphone }" placeholder="请输入收货电话">
                    </div>
                    <div class="input">
                        <span>收货地址</span>
                        <input type="text" name="uaddress" value="${user.uaddress }" placeholder="请输入收货地址">
                    </div>
                    <div class="register-but text-center">
                        <input type="submit" value="提交">
                    </div>
                    <!-- 收货信息 end -->
                </form>
                <hr>
                <form action="change_password.action" method="post">
                    <input type="hidden" name="uid" value="${user.uid}"/>
                    <h4>安全信息</h4>
                    <div class="input">
                        <span>原密码</span>
                        <input type="password" name="oldupwd" placeholder="请输入原密码">
                    </div>
                    <div class="input">
                        <span>新密码</span>
                        <input type="password" name="upwd" placeholder="请输入新密码">
                    </div>
                    <div class="clearfix"> </div>
                    <div class="register-but text-center">
                        <input type="submit" value="提交">
                    </div>
                </form>
            </div>

            <div class="clearfix"> </div>
        </div>
    </div>
</div>
<!--//account-->




<jsp:include page="/footer.jsp"></jsp:include>


</body>
</html>