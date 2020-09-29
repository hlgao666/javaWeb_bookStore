<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%--静态包含，样式，jquery文件等--%>
	<%@include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {
			$("a.deleteClass").click(function () {
				return confirm("Are you sure to delete "+$(this).parent().parent().find("td:first").text()+"?")
			});

			<%--
			js表达式提供了一个location对象
			该对象有herf属性，可读可写
			--%>
			$("#searchPageBtn").click(function () {
				var pageNo = $("#pn_input").val();
				var pageTotal = ${requestScope.page.pageTotal};
				if(1<=pageNo&&pageNo<=pageTotal){
					location.href = "${requestScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
				}else{
					alert("plz enter page No between 1 and "+ pageTotal);
				}

			});
		});
	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%--静态包含，图书馆里界面--%>
			<%@include file="/pages/common/menu_manager.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}
					&pageTotal=${requestScope.page.pageTotal}&itemTotal=${requestScope.page.itemTotal}
					&pageSize=${requestScope.page.pageSize}">删除</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>

		<%-- 静态包含分页条 --%>
		<%@include file="/pages/common/page_nav.jsp"%>>

	</div>

	<%--静态包含，页脚--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>