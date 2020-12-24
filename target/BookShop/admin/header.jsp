<%--
  Created by IntelliJ IDEA.
  User: vili
  Date: 2019/8/28
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">叮当书城后台</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li ><a href="order_list.action?pageNumber=1&ostatus=1">订单管理</a></li>
                <li ><a href="user_list.action?pageNumber=1">客户管理</a></li>
                <li ><a href="book_list.action?pageNumber=1&rtype=0">商品管理</a></li>
                <li ><a href="type_list.action">类目管理</a></li>
                <li><a href="logout.action">退出</a></li>
            </ul>
        </div>
    </div>
</nav>

