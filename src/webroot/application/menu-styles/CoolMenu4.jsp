<%@ include file="/common/header.jsp"%>
<!--�����������-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/global.css'/>" /> 

<!-- coolmenu4 �˵���ʽ ����Ҫ-->
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/coolmenu.css'/>" /> 

<!-- coolmenu4 �˵�js ����Ҫ-->
<!--������coolmenus4.js cm_addins.js coolmenu4-config.js-->
<script type="text/javascript" src="<c:url value='/scripts/tree/coolmenus4.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/tree/cm_addins.js'/>"></script>



<html>
<head>
<title>CoolMenu4 ���</title>
     <META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body >
<!-- Custom Configuration for this example, must come after body to work in IE -->  
<!-- ������δ�����IE������б��������body��ǩ֮��-->
<script type="text/javascript" src="<c:url value='/scripts/tree/coolmenu4-config.js'/>"></script>


CoolMenu4:
<menu:useMenuDisplayer name="CoolMenu4" 
    repository="repository">
    <c:forEach var="menu" items="${repository.topMenus}">
        <menu-el:displayMenu name="${menu.name}"/>
    </c:forEach>
</menu:useMenuDisplayer>



</body>
</html>
<%@ include file="/common/footer.jsp"%>