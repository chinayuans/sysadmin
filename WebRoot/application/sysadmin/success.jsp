<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<html>
<head>
<title>操作成功</title>
<META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body >
              <p>操作成功</p>

<!--满足条件刷新页面-->
<!--
refresh:
<c:out value='${refresh}'/>
-->


<!--做了更新操作以后-->
<c:if test="${refresh=='afterSaveOne' or  refresh=='afterUpdateOne' or  refresh=='afterUpdateSome' or  refresh=='afterSaveOrUpdateOne' or refresh=='afterRemoveSome'}">
    <script type="text/javascript">
    <!--
    hideFrame();
    parent.listing.location.href="<c:out value='${pageContext.request.requestURL}'/>?method=queryAll";
    //-->
    </script>
    <!--log:fatal message="redirect"/-->
</c:if>

<!--做了条件查询操作以后-->
<c:if test="${refresh=='afterQueryStandard'}">
    
    <!-- 始终保存条件查询后获得数据到session中。 -->
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