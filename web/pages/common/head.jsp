<%--
  Created by IntelliJ IDEA.
  User: Allen
  Date: 2020/9/19
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()
    + "://"
    + request.getServerName()
    + ":"
    + request.getServerPort()
    + request.getContextPath()
    + "/";
    request.setAttribute("basePath",basePath);
%>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
