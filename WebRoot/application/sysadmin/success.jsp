<%@ include file="/common/header.jsp"%>
<!--�����������-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<html>
<head>
<title>�����ɹ�</title>
<META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body >
              <p>�����ɹ�</p>

<!--��������ˢ��ҳ��-->
<!--
refresh:
<c:out value='${refresh}'/>
-->


<!--���˸��²����Ժ�-->
<c:if test="${refresh=='afterSaveOne' or  refresh=='afterUpdateOne' or  refresh=='afterUpdateSome' or  refresh=='afterSaveOrUpdateOne' or refresh=='afterRemoveSome'}">
    <script type="text/javascript">
    <!--
    hideFrame();
    parent.listing.location.href="<c:out value='${pageContext.request.requestURL}'/>?method=queryAll";
    //-->
    </script>
    <!--log:fatal message="redirect"/-->
</c:if>

<!--����������ѯ�����Ժ�-->
<c:if test="${refresh=='afterQueryStandard'}">
    
    <!-- ʼ�ձ���������ѯ�������ݵ�session�С� -->
    <c:set var="results" value="${null}" scope="session"/>
    <c:set var="results" value="${requestScope.data}" scope="session"/>
   
     
    <script type="text/javascript">
    <!--
    hideFrame();
    parent.listing.location.href="<c:out value='${pageContext.request.requestURL}'/>?method=forward";
    //-->
    </script>
    <!--log:fatal message="forward"/-->
</c:if> 
<!--log:fatal message="no_redirect"/-->



</body>
</html>


<%@ include file="/common/footer.jsp"%>