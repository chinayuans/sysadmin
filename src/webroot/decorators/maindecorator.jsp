<!--�����������-->
<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" pageEncoding="GB2312" %>

<!-- 1.����ϵͳ�Ĵ���ҳ��-->
<%@ page errorPage="/common/errorpage.jsp" %>

<!-- 2.����ͨ�ñ�ǩ�ļ�-->
<%@ include file="/common/common_taglibs.jsp"%>

<!-- 3.�����ض������ļ���Ŀǰֻ�в鿴Դ����Ĺ��ܡ�-->
<%@ include file="/common/common_function.jsp"%>

<!-- 4.����ͨ��css�ļ�-->
<%@ include file="/common/common_css.jsp"%>


<html>
<head> 
   
     
   <title>
         �ۺ�ʹ��Sitemesh������
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
