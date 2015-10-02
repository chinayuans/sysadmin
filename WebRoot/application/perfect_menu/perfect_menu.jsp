
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单显示</title>
  </head>
   
<body>  


<script language="JavaScript">
<%@ include file="menu.js"%>
//不可以使用c:out标签，因为'不能够正常显示出来。
<%=request.getAttribute("MenuScript")%>
mwritetodocument();
</script>


</body>
</html>
