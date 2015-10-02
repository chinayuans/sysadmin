<%@ include file="/common/header.jsp"%>
<!--�����������-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>


<!-- simple,dropdown�˵���ʽ -->
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/global.css'/>" /> 
<script type="text/javascript" src="<c:url value='/scripts/tree/cookies.js'/>"></script>

<html>
<head>
<title>DropDown ���</title>
     <META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body >

DropDown:
<menu:useMenuDisplayer name="DropDown" 
                       repository="repository" >
                       
     <table cellpadding=0 cellspacing=0>
        <c:forEach var="menu" items="${repository.topMenus}">
          <tr>
            <td>   
            <menu-el:displayMenu name="${menu.name}"/>
            </td>
          </tr>
        </c:forEach>
       </table>
       
</menu:useMenuDisplayer>

<!-- ��֮ͬ�����ڶ��һ��config ������  AppDisplayerStrings����AppDisplayerStrings.properties�ļ�-->

<menu:useMenuDisplayer name="DropDown" 
                       config="AppDisplayerStrings"
                       repository="repository" >
                       
     <table cellpadding=0 cellspacing=0>
        <c:forEach var="menu" items="${repository.topMenus}">
          <tr>
            <td>   
            <menu-el:displayMenu name="${menu.name}"/>
            </td>
          </tr>
        </c:forEach>
       </table>
       
</menu:useMenuDisplayer>


</body>
</html>
<%@ include file="/common/footer.jsp"%>