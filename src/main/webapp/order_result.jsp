<%--
  Created by IntelliJ IDEA.
  User: vili
  Date: 2019/8/27
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>
        <c:if test="${!empty msg }">
            支付成功
        </c:if>
        <c:if test="${!empty failmsg }">
            支付失败
        </c:if>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="js/cart.js"></script>
</head>
<body>







<!--header-->
<jsp:include page="header.jsp"></jsp:include>
<!--//header-->


<!--cart-items-->
<div class="cart-items">
    <div class="container">

        <c:if test="${!empty msg }">
            <div class="alert alert-success">${msg }</div>
        </c:if>
        <c:if test="${!empty failmsg }">
            <div class="alert alert-danger">${failmsg }</div>
        </c:if>
        <p><a class="btn btn-success" href="order_list.action">查看我的订单</a></p>
    </div>
</div>
<!--//cart-items-->






<!--footer-->
<jsp:include page="footer.jsp"></jsp:include>
<!--//footer-->


</body>
</html>
