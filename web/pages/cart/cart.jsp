<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含，样式，jquery文件等--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.deleteItem").click(function () {
				return confirm("Are you sure delete "+$(this).parent().parent().find("td:first").text());
			});

			$("#clear").click(function () {
				return confirm("Are you sure clear all items?");
			});

			$(".updateCount").change(function () {

				var name = $(this).parent().parent().find("td:first").text();
				var count = this.value;
				var stock = $(this).attr("bookStock");

				if(parseInt(stock)<parseInt(count)){
					alert("The count you chose is greater than it's stock. Plz choose count up to "+stock);
				}else{
					if(confirm("Are you sure modify " +name +"'s count to "+ count +"?")){
						//提交给服务器修改
						location.href = "http://localhost:8080/book/cartServlet?action=updateCount&count="+count+"&id="+$(this).attr("bookId");
					}else{
						//defaultValue是表单项dom对象的属性，他表示默认值
						this.value = this.defaultValue;
					}
				}
			});
		});
	</script>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%--静态包含，登陆成功之后的界面--%>
			<%@include file="/pages/common/login_success_common.jsp"%>
	</div>
	
	<div id="main">
	
		<table>

			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" style="width: 80px;"
								   bookId="${entry.value.id}"
								   bookStock = "${entry.value.stock}"
								   type="text" value="${entry.value.count}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>

			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5">当前购物车为空<br><a href="index.jsp">返回首页</a></td>
				</tr>
			</c:if>
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clear" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	
	</div>

	<%--静态包含，页脚--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>