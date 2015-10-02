<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/global.css'/>" /> 

<!-- coolmenu4 菜单样式 必须要-->
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/coolmenu.css'/>" /> 

<!-- coolmenu4 菜单js 必须要-->
<!--包含：coolmenus4.js cm_addins.js coolmenu4-config.js-->
<script type="text/javascript" src="<c:url value='/scripts/tree/coolmenus4.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/tree/cm_addins.js'/>"></script>



<html>
<head>
<title>CoolMenu4 风格</title>
     <META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body >
<!-- Custom Configuration for this example, must come after body to work in IE -->  
<!-- 以下这段代码在IE浏览器中必须放置在body标签之后。-->
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