<%--
  Created by IntelliJ IDEA.
  User: vili
  Date: 2019/8/28
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>订单列表</title>
    <link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="header.jsp"></jsp:include>

    <br>
    <ul role="tablist" class="nav nav-tabs">
        <li <c:if test="${ostatus==1 }">class="active"</c:if> role="presentation"><a href="order_list.action?pageNumber=1&ostatus=1">全部订单</a></li>
        <li <c:if test="${ostatus==5 }">class="active"</c:if> role="presentation"><a href="order_list.action?pageNumber=1&ostatus=5">未付款</a></li>
        <li <c:if test="${ostatus==2 }">class="active"</c:if> role="presentation"><a href="order_list.action?pageNumber=1&ostatus=2">已付款</a></li>
        <li <c:if test="${ostatus==3 }">class="active"</c:if> role="presentation"><a href="order_list.action?pageNumber=1&ostatus=3">配送中</a></li>
        <li <c:if test="${ostatus==4 }">class="active"</c:if> role="presentation"><a href="order_list.action?pageNumber=1&ostatus=4">已完成</a></li>
    </ul>

    <br>

    <table class="table table-bordered table-hover">

        <tr>
            <th width="5%">ID</th>
            <th width="5%">总价</th>
            <th width="15%">商品详情</th>
            <th width="20%">收货信息</th>
            <th width="10%">订单状态</th>
            <th width="10%">支付方式</th>
            <th width="10%">下单用户</th>
            <th width="10%">下单时间</th>
            <th width="10%">操作</th>
        </tr>

        <c:forEach items="${p.list }" var="order">
            <tr>
                <td><p>${order.oid }</p></td>
                <td><p>${order.ototal }</p></td>
                <td>
                    <c:forEach items="${order.itemList }" var="item">
                        <p>${item.book.bname }(${item.oiprice }) x ${item.oiamount}</p>
                    </c:forEach>
                </td>
                <td>
                    <p>${order.orealname }</p>
                    <p>${order.ophone }</p>
                    <p>${order.oaddress }</p>
                </td>
                <td>
                    <p>
                        <c:if test="${order.ostatus==2 }"><span style="color:red;">已付款</span></c:if>
                        <c:if test="${order.ostatus==3 }"><span style="color:green;">已发货</span></c:if>
                        <c:if test="${order.ostatus==4 }"><span style="color:black;">已完成</span></c:if>

                    </p>
                </td>
                <td>
                    <p>

                        <c:if test="${order.opaytype==1 }">微信</c:if>
                        <c:if test="${order.opaytype==2 }">支付宝</c:if>
                        <c:if test="${order.opaytype==3 }">货到付款</c:if>

                    </p>
                </td>
                <td><p>${order.orealname }</p></td>
                <td><p>${order.odatetime }</p></td>
                <td>
                    <c:if test="${order.ostatus==2 }">
                        <a class="btn btn-success" href="order_status_change.action?oid=${order.oid}&ostatus=3">发货</a>
                    </c:if>
                    <c:if test="${order.ostatus==3 }">
                        <a class="btn btn-warning" href="order_status_change.action?oid=${order.oid}&ostatus=4">完成</a>
                    </c:if>
                    <a class="btn btn-danger" href="order_delete.action?oid=${order.oid}&ostatus=${ostatus}">删除</a>
                </td>
            </tr>
        </c:forEach>


    </table>

    <br>
    <jsp:include page="/page.jsp">
        <jsp:param value="/admin/order_list" name="url"/>
        <jsp:param value="&status=${ostatus}" name="param"/>
    </jsp:include>
    <br>
</div>
</body>
</html>