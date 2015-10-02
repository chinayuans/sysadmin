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
         综合使用Sitemesh的例子
   </title>
   
</head>


<body>
         
    <page:applyDecorator name="decorator1" page="/decorators/decoratee/decoratee1.jsp" >
       <page:param name="Email">chinayuans@163.com</page:param>
    </page:applyDecorator>
    
    <page:applyDecorator name="decorator2" page="/decorators/decoratee/decoratee2.jsp"/>
       
</body>

<%@ include file="/common/footer.jsp"%>
</html>
