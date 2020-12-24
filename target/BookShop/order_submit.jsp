<%--
  Created by IntelliJ IDEA.
  User: vili
  Date: 2019/8/27
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>支付</title>
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

<div class="cart-items">
    <div class="container">
        <h2>确认收货信息</h2>
        <form class="form-horizontal" action="order_confirm.action" method="post" id="payform">
            <div class="row">
                <label class="control-label col-md-1">收货人</label>
                <div class="col-md-6">
                    <input type="text" class="form-control" name="name" value="${user.urealname }" style="height:auto;padding:10px;" readonly="readonly" required="required"><br>
                </div>
            </div>
            <div class="row">
                <label class="control-label col-md-1">收货电话</label>
                <div class="col-md-6">
                    <input type="text" class="form-control" name="phone" value="${user.uphone }" style="height:auto;padding:10px;" readonly="readonly" required="required"><br>
                </div>
            </div>
            <div class="row">
                <label class="control-label col-md-1">收货地址</label>
                <div class="col-md-6">
                    <input type="text" class="form-control" name="address" value="${user.uaddress }" style="height:auto;padding:10px;" readonly="readonly" required="required"><br>
                </div>
            </div>

            <br><hr><br>

            <h2>选择支付方式</h2>
            <h3>支付金额: ${order.ototal }</h3><br><br>


            <div class="col-sm-6 col-md-4 col-lg-3 ">
                <label>
                    <div class="thumbnail">
                        <input type="radio" name="opaytype" value="1" checked="checked" />
                        <img src="images/wechat.jpg" alt="微信支付">
                    </div>
                </label>
            </div>
            <div class="col-sm-6 col-md-4 col-lg-3 ">
                <label>
                    <div class="thumbnail">
                        <input type="radio" name="opaytype" value="2"  />
                        <img src="images/alipay.jpg" alt="支付宝支付">
                    </div>
                </label>
            </div>
            <div class="col-sm-6 col-md-4 col-lg-3 ">
                <label>
                    <div class="thumbnail">
                        <input type="radio" name="opaytype" value="3"  />
                        <img src="images/offline.jpg" alt="货到付款">
                    </div>
                </label>
            </div>
            <div class="clearfix"> </div>
            <div class="register-but text-center">
                <input type="submit" value="确认订单">
                <div class="clearfix"> </div>
            </div>
        </form>
    </div>
</div>





<!--footer-->
<jsp:include page="footer.jsp"></jsp:include>
<!--//footer-->


<script type="text/javascript">
    function dopay(paytype){
        $("#paytype").val(paytype);
        $("#payform").submit();
    }
</script>

</body>
</html>
