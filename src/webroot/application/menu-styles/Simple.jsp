<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<!-- simple,dropdown菜单样式 -->
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/global.css'/>" /> 
<script type="text/javascript" src="<c:url value='/scripts/tree/cookies.js'/>"></script>


<html>
<head>
<title>Simple 风格</title>
     <META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body >
Simple:
<menu:useMenuDisplayer name="Simple" 
                       repository="repository">
 
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

<!-- 不同之处在于多出一个config 的属性  AppDisplayerStrings代表AppDisplayerStrings.properties文件-->

<menu:useMenuDisplayer name="Simple"
                       config="AppDisplayerStrings"
                       repository="repository">
                       
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