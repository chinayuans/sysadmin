<%@ page language="java" isErrorPage="true" %>
<!--设置中文输出-->
<%@ include file="/common/header.jsp"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<!--error.jsp与errorpage.jsp有不同的用处  -->


<head>
<title>出错信息</title>
<META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>

     应用程序中发生错误 

<% if (exception != null) { %>
    <pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre>
<% } else { %>
   请检查日志来获得更多的信息。
<% } %>
