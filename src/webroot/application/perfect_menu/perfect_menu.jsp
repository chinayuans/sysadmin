
<!--�����������-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�˵���ʾ</title>
  </head>
   
<body>  


<script language="JavaScript">
<%@ include file="menu.js"%>
//������ʹ��c:out��ǩ����Ϊ'���ܹ�������ʾ������
<%=request.getAttribute("MenuScript")%>
mwritetodocument();
</script>


</body>
</html>
