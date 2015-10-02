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
         <decorator:title default="װ����ҳ��..." />
   </title>
   
   <decorator:head/>
</head>

<!--ע��:����calendar.jsp�� calendar_struts.jsp
    ʹ�õ��� body��ǩ�� һЩ��������Ҫ�����Լ���ȥ,���ⶪʧ,
    ��������.
    -->
<body <decorator:getProperty property="body.onload" writeEntireProperty="true" />
      <decorator:getProperty property="body.onclick" writeEntireProperty="true" />
      <decorator:getProperty property="body.onkeyup" writeEntireProperty="true" />
>
         
       <decorator:body/>
       
</body>

<%@ include file="/common/footer.jsp"%>
</html>
