<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<!-- 树型菜单要用到的css和js -->
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/global.css'/>" />
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/xtrees.css'/>" /> 

<script type="text/javascript" src="<c:url value='/scripts/tree/xtree.js'/>"></script>
<!-- 
=======特别说明如下=========
必须明确在这个文件/javascript/xtreeDemo.html中
../scripts/tree/xtree.js
中导入的rootIcon        : 'images/tree/foldericon.png',
这个images/tree/foldericon.png表示图像放置在当前目录的images/trees目录下.
所以这种xtreeDemo.html使用方法具有一定的局限性。推荐使用下的方法。

但是在另外一个文件/application/menu-styles/XTree.jsp中
<script type="text/javascript" src="<c:url value='/scripts/tree/xtree.js'/>"></script>
中导入的rootIcon        : 'images/tree/foldericon.png',
这个images/tree/foldericon.png表示图像放置在应用服务器的
某个应用服务的根目录下的images/tree/目录下，
例如：图像放置在http://127.0.0.1:8080/sysadmin/images/tree/目录中，
其中sysadmin为应用服务名。图像放置在应用服务为sysadmin的根目录的images/tree/目录下。
-->

<html>
<head>
<title>树型显示</title>
     <META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body >

<script type="text/javascript">
<menu:useMenuDisplayer 
      name="Velocity" 
      config="/templates/xtree.html"
      repository="repository"
      bundle="org.apache.struts.action.MESSAGE">
      
if (document.getElementById) {
    <c:forEach var="menu" items="${repository.topMenus}">
        <menu-el:displayMenu name="${menu.name}"/>
    </c:forEach>
} else {
  var msg = "您的浏览器不支持document.getElementById().\n";
    msg += "要使用这个菜单必须使用高版本的IE";
  alert(msg);
}
</menu:useMenuDisplayer>
</script>







</body>
</html>
<%@ include file="/common/footer.jsp"%>