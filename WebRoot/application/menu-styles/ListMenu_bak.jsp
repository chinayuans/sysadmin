<%@ include file="/common/header.jsp"%>
<!--�����������-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>


<!-- ListMenu�˵���ʽ -->
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/global.css'/>" />  
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/menuExpandable.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/tree/menuExpandable.js'/>"></script>


<html>
<head>
<title>ListMenu ���</title>
     <META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body >

ListMenu:
<menu:useMenuDisplayer name="ListMenu" 
    repository="repository">
    <c:forEach var="menu" items="${repository.topMenus}">
        <menu-el:displayMenu name="${menu.name}"/>
    </c:forEach>
</menu:useMenuDisplayer>
        
   


</body>
</html>
<%@ include file="/common/footer.jsp"%>