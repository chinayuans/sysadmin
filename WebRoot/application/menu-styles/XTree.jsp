<%@ include file="/common/header.jsp"%>
<!--�����������-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<!-- ���Ͳ˵�Ҫ�õ���css��js -->
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/global.css'/>" />
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/xtrees.css'/>" /> 

<script type="text/javascript" src="<c:url value='/scripts/tree/xtree.js'/>"></script>
<!-- 
=======�ر�˵������=========
������ȷ������ļ�/javascript/xtreeDemo.html��
../scripts/tree/xtree.js
�е����rootIcon        : 'images/tree/foldericon.png',
���images/tree/foldericon.png��ʾͼ������ڵ�ǰĿ¼��images/treesĿ¼��.
��������xtreeDemo.htmlʹ�÷�������һ���ľ����ԡ��Ƽ�ʹ���µķ�����

����������һ���ļ�/application/menu-styles/XTree.jsp��
<script type="text/javascript" src="<c:url value='/scripts/tree/xtree.js'/>"></script>
�е����rootIcon        : 'images/tree/foldericon.png',
���images/tree/foldericon.png��ʾͼ�������Ӧ�÷�������
ĳ��Ӧ�÷���ĸ�Ŀ¼�µ�images/tree/Ŀ¼�£�
���磺ͼ�������http://127.0.0.1:8080/sysadmin/images/tree/Ŀ¼�У�
����sysadminΪӦ�÷�������ͼ�������Ӧ�÷���Ϊsysadmin�ĸ�Ŀ¼��images/tree/Ŀ¼�¡�
-->

<html>
<head>
<title>������ʾ</title>
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
  var msg = "�����������֧��document.getElementById().\n";
    msg += "Ҫʹ������˵�����ʹ�ø߰汾��IE";
  alert(msg);
}
</menu:useMenuDisplayer>
</script>







</body>
</html>
<%@ include file="/common/footer.jsp"%>