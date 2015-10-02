<%@ page language="java" isErrorPage="true" %>
<!--设置中文输出-->
<%@ include file="/common/header.jsp"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>



<html:html locale="true">
<head>
  <title>显示  应用异常 信息</title>
  <html:base/>
  
</head>
<body bgcolor="white">
  <p> <h2>应用异常信息显示</h2>
     <p>
     <log:fatal message="欢迎使用log标签"/>
        <html:errors/> 
     </p>
   
     <p> 
          <!--logic:messagesNotPresent-->
              没有出错信息
          <!--/logic:messagesNotPresent-->
     </p>
   </p>  

</body>


</html:html>