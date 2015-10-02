<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" pageEncoding="GB2312" %>

<!-- 1.设置系统的错误页面-->
<%@ page errorPage="/common/errorpage.jsp" %>

<!-- 2.导入通用标签文件-->
<%@ include file="/common/common_taglibs.jsp"%>

<!-- 3.导入特定功能文件：目前只有查看源代码的功能。-->
<%@ include file="/common/common_function.jsp"%>

<!-- 4.导入通用css文件-->
<%@ include file="/common/common_css.jsp"%>


<html>
<head> 
   
     
   <title>
         <decorator:title default="装饰器页面..." />
   </title>
   
   <decorator:head/>
</head>

<!--注意:由于calendar.jsp和 calendar_struts.jsp
    使用到了 body标签中 一些属性所以要将属性加上去,避免丢失,
    产生错误.
    -->
<body <decorator:getProperty property="body.onload" writeEntireProperty="true" />
      <decorator:getProperty property="body.onclick" writeEntireProperty="true" />
      <decorator:getProperty property="body.onkeyup" writeEntireProperty="true" />
>
         
       <decorator:body/>
       
</body>

<%@ include file="/common/footer.jsp"%>
</html>
