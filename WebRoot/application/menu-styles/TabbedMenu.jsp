<%@ include file="/common/header.jsp"%>
<!--�����������-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<!-- tabbedMenu�˵���ʽ -->
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/global.css'/>" /> 
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/tabs.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/tree/tabs.js'/>"></script>

<html>
<head>
<title>tabbedMenu ���</title>
     <META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body >

TabbedMenu:
<menu:useMenuDisplayer name="TabbedMenu" 
                       repository="repository"
                       bundle="org.apache.struts.action.MESSAGE">
    <c:forEach var="menu" items="${repository.topMenus}">
        <menu-el:displayMenu name="${menu.name}"/>
    </c:forEach>
</menu:useMenuDisplayer>

</body>
</html>
<%@ include file="/common/footer.jsp"%>