<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<!-- coolmenu 菜单样式必须要 -->
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/global.css'/>" /> 

<!-- coolmenu 菜单js必须要 -->
<!--包含：coolmenus3.js  coolmenu-config.js  coolmenu2-config.js-->
<script type="text/javascript" src="<c:url value='/scripts/tree/coolmenus3.js'/>"></script>




<html>
<head>
<title>CoolMenu 风格</title>
     <META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body >

<!-- Custom Configuration for this example, must come after body to work in IE -->
<!-- 以下这段代码在IE浏览器中必须放置在body标签之后。-->
<script type="text/javascript" src="<c:url value='/scripts/tree/coolmenu-config.js'/>"></script>
<!--script type="text/javascript" src="<c:url value='/scripts/tree/coolmenu2-config.js'/>"--></script>

CoolMenu:
<menu:useMenuDisplayer name="CoolMenu" 
                       bundle="org.apache.struts.action.MESSAGE"
                       repository="repository">
    <c:forEach var="menu" items="${repository.topMenus}">
        <menu-el:displayMenu name="${menu.name}"/>
    </c:forEach>
</menu:useMenuDisplayer>


<menu:useMenuDisplayer name="CoolMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="ToDoListMenuFile"/>
    <menu:displayMenu name="ToDoListMenuEdit"/>
    <menu:displayMenu name="CaseDetailMenuCase"/>
</menu:useMenuDisplayer>


</body>
</html>
<%@ include file="/common/footer.jsp"%>