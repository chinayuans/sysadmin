
<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<!-- ListMenu菜单样式 -->
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/global.css'/>" />  
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/menuExpandable.css'/>" />

<!-- ListMenu菜单js -->
<script type="text/javascript" src="<c:url value='/scripts/tree/menuExpandable.js'/>"></script>
<table width="20%">
<menu:useMenuDisplayer name="ListMenu" 
    repository="repository">
    <c:forEach var="menu" items="${repository.topMenus}">
        <menu-el:displayMenu name="${menu.name}"/>
    </c:forEach>
</menu:useMenuDisplayer>
</table>
   