<!--�����������-->
<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" pageEncoding="GB2312" %>

<!-- 2.����ͨ�ñ�ǩ�ļ�-->
<%@ include file="/common/common_taglibs.jsp"%>


<html>
<head> 
   <title>
         <decorator:title default="װ����ҳ��..." />
   </title>
         <decorator:head/>
</head>
<body>
       <p>װ����1</p>
       <p><decorator:getProperty property="Email" /></p>
       <decorator:body/>
</body>
</html>
