<%--
  Created by IntelliJ IDEA.
  User: vili
  Date: 2019/8/26
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>商品详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/flexslider.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.flexslider.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="js/cart.js"></script>
    <script>
        $(function() {
            $('.flexslider').flexslider({
                animation: "slide",
                controlNav: "thumbnails"
            });
        });
    </script>
</head>
<body>







<!--header-->
<jsp:include page="/header.jsp"></jsp:include>
<!--//header-->


<!--//single-page-->
<div class="single">
    <div class="container">
        <div class="single-grids">
            <div class="col-md-4 single-grid">
                <div class="flexslider">

                    <ul class="slides">
                        <li data-thumb="${book.bcover}">
                            <div class="thumb-image"> <img src="${book.bcover}" data-imagezoom="true" class="img-responsive"> </div>
                        </li>
                        <li data-thumb="${book.bimage1}">
                            <div class="thumb-image"> <img src="${book.bimage1}" data-imagezoom="true" class="img-responsive"> </div>
                        </li>
                        <li data-thumb="${book.bimage2}">
                            <div class="thumb-image"> <img src="${book.bimage2}" data-imagezoom="true" class="img-responsive"> </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-4 single-grid simpleCart_shelfItem">
                <h3>${book.bname}</h3>
                <div class="tag">
                    <p>分类 : <a href="goods.action?typeid=5">${book.btname}</a></p>
                </div>
                <p>${book.bmark}</p>
                <div class="galry">
                    <div class="prices">
                        <h5 class="item_price">¥ ${book.bprice}</h5>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="btn_form">
                    <a href="javascript:;" class="add-cart item_add" onclick="buy(${book.bid})">加入购物车</a>
                </div>
            </div>
            <div class="col-md-4 single-grid1">
                <!-- <h2>商品分类</h2> -->
                <ul>
                    <li><a  href="booktypes_list.action?pageNumber=1&btid=-1">全部系列</a></li>

                    <c:forEach items="${bookTypes}" var="t">
                        <li><a href="booktypes_list.action?pageNumber=1&btid=${t.btid}">${t.btname}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>

<!--related-products--><!--
	<div class="related-products">
		<div class="container">
			<h3>猜你喜欢</h3>
			<div class="product-model-sec single-product-grids">
				<div class="product-grid single-product">
					<a href="single.html">
					<div class="more-product"><span> </span></div>
					<div class="product-img b-link-stripe b-animate-go  thickbox">
						<img src="images/m1.png" class="img-responsive" alt="">
						<div class="b-wrapper">
						<h4 class="b-animate b-from-left  b-delay03">
						<button>View</button>
						</h4>
						</div>
					</div>
					</a>
					<div class="product-info simpleCart_shelfItem">
						<div class="product-info-cust prt_name">
							<h4>Product #1</h4>
							<span class="item_price">$2000</span>
							<div class="ofr">
							  <p class="pric1"><del>$2300</del></p>
							  <p class="disc">[15% Off]</p>
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
 -->	<!--related-products-->






<!--footer-->
<jsp:include page="/footer.jsp"></jsp:include>
<!--//footer-->


</body>
</html>
