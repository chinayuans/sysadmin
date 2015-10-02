<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" pageEncoding="GB2312" %>

<!-- 2.导入通用标签文件-->
<%@ include file="/common/common_taglibs.jsp"%>


<html>
<head> 
   <title>
         <decorator:title default="装饰器页面..." />
   </title>
         <decorator:head/>
</head>
<body>
       <p>装饰器1</p>
       <p><decorator:getProperty property="Email" /></p>
       <decorator:body/>
</body>
</html>
