<%@ page language="java" isErrorPage="true" %>
<!--�����������-->
<%@ include file="/common/header.jsp"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>



<html:html locale="true">
<head>
  <title>��ʾ  Ӧ���쳣 ��Ϣ</title>
  <html:base/>
  
</head>
<body bgcolor="white">
  <p> <h2>Ӧ���쳣��Ϣ��ʾ</h2>
     <p>
     <log:fatal message="��ӭʹ��log��ǩ"/>
        <html:errors/> 
     </p>
   
     <p> 
          <!--logic:messagesNotPresent-->
              û�г�����Ϣ
          <!--/logic:messagesNotPresent-->
     </p>
   </p>  

</body>


</html:html>