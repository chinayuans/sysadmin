<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" pageEncoding="GB2312" %>

<%@ include file="/common/header.jsp" %>
<html>
<head><title>欢迎您</title></head>
<body >
<!--%@ include file="/common/banner.jsp" %-->

<!-- 音乐的导入-->
<object classid=CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95 class=O id=M width=0 height=0 >
<param name=Filename value="<c:url value='/other_resources/promises.mp3'/>">
<param name=loop value=true></object>




<table width="100%" height="100%" border="0" align="center">
  <tr> 
    <td width="30%">
    </td>
    <td width="" height="100%" align="center"> 
      <p align="center">&nbsp;</p>
      <p align="center"><b><font color="#336699" size="4">欢迎您使用本系统</font></b></p>
      <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<span class="textMain"><font size="2">您好！欢迎您使用本系统。</font></span></p>
      <p align="left"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;<span class="textMain">通过本系统，你可以获得进行用户管理，权限分配等功能。</span></font></p>
      <p align="left"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;<span class="textMain">您可以点击导航栏使用相关的具体功能。如果您在使用过程中发现了什么问题，可以点击“技术支持”来获得来自我们工程师的帮助。</span></font></p>
      <p align="left"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;</font></p>
      <p align="right">&nbsp;</p>
     </td>
  </tr>
</table>


</body>
</html>
<%@ include file="/common/footer.jsp" %>
