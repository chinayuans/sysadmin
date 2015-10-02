<%@ include file="/common/header.jsp"%>
<!--�����������-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>


<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/_ListAll_table.css'/>" />
<%@ include file="/scripts/_ListAll.js"%>


<font class="changecolor_bar" >&nbsp;</font>

<div align="right">
<img align="right" src="<c:out value="${pageContext.request.contextPath}"/>/images/solid_arrow_down.jpg" height="40" width="40" onclick="changeFramePosition(this);">
<button class="textBox" onclick="changeStyle('row');">�ı�����</button>
</div>



<html>
<head>
<title>�б���ʾ</title>
<META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body >


<!--  request ��Χ�� -->


<table border="0" >

<tr>  
    <button class="textBox" onclick="common_add();">����</button>   
    <button class="textBox" onclick="common_delete_checked();">ɾ��ѡ�м�¼</button>   
    <button class="textBox" onclick="common_delete_condition();">����ɾ��</button>   
    <button class="textBox" onclick="common_query_condition();">������ѯ</button>
  
</tr>
<tr>
<td>

<form  method="post">
<!-- display-el ������ʹ��expression language 
     display   ������ֻ����ʹ��jsp����
-->


<!-- ���request��û�����ݣ���ʾû�н��и��²�����
      ��ô��session��ȡ������ʾ����ʾ������������ѯ����-->
<c:if test="${empty requestScope.data}" >
  <c:set var="data" value="${sessionScope.results}" scope="request"/>
</c:if>

<!--requestURI="${pageContext.request.requestURL}?method=queryAll"-->

<display-el:table id="row"
               name="data" 
               pagesize="9"
               export="true"
               requestURI="${pageContext.request.requestURL}"
               length=""
               defaultsort="2"
               offset="1"  
               class="mars"
               decorator="org.displaytag.sample.OperatorWrapper">

<!--
����˵��:
request.setAttribute("user",user); //user��һ��bean,������ID,UserName.

����ϣ���ܹ�����һ�� user.do?method=saveOne&Name=yuanjs����һ��href.
��ô:
href="user.do?method=saveOne";   -------ָ��url
paramId="Name"                                  -------ָ����url�����ӵĲ�������
paramName="user"                              --------��������Ҫ��ֵ���Ǹ�bean����
paramProperty="UserName"   -------��������Ҫ��ֵ���Ǹ�bean����������
paramScope="request" ---------------��������Ҫ��ֵ���Ǹ�bean�ķ�Χ
-->



    <!--ҳ���Ͽ��ƣ�    �������csv��ʽ.  -->               
    <display:setProperty name="export.csv" value="false"/>
    
    <display:setProperty name="paging.banner.placement" value="top" />      
    <display:setProperty name="export.excel.include_header" value="false" /> 
         
     
     <display:column media="html" title="<input id='selectAll_CheckBox' type='checkbox' onclick=\"selectAll_CheckedBox('ids',this);\" /> ">
        <input id="ids"   name="ids" type="checkbox" value="<c:out value='${row.id}'/>"/>
     </display:column>
     
     <display:column title="���" media="html" sort="true">
         <a href="#" onclick="common_edit('<c:out value='${row.id}'/>');">
                <%=pageContext.getAttribute("row_rowNum")%>
         </a>
     </display:column>
     <display:column title="���" media="xxx" ><c:out value="${row_rowNum}"/> </display:column>
     
    <!--=============================================-->
    <!--=============���ϴ��벻Ҫ�Ķ� ==================-->
    <!--=============================================-->

     
     <!--=======================================--->
     <!--==========��������Ϊ��ҳҪ�޸ĵĵط�========--->
     <!--��������ĵط��⣬�����ط�Ϊ���ò��֣������ԸĶ�--->
     <!--=======================================--->
     
     
     
     <display:column property="name" title="�˵���" nulls="true" sort="true"/>
     <display:column property="title" title="�˵���ʾ��" nulls="true" sort="true"/>
     <display:column property="parent_name" title="parent_name" sort="true"/>
     <display:column property="description" title="description" sort="true"/>
     <display:column property="location" title="location" sort="true"/>
     <display:column property="page" title="page" sort="true"/>
     <display:column property="forward" title="forward" sort="true"/>
     <display:column property="action" title="action" sort="true"/>
     
     <display:column property="target" title="target" sort="true"/>
     <display:column property="onclick" title="onclick" sort="true"/>
     <display:column property="onmouseover" title="onmouseover" sort="true"/>
     <display:column property="onmouseout" title="onmouseout" sort="true"/>
     <display:column property="image" title="image" sort="true"/>
     <display:column property="altImage" title="altImage" sort="true"/>
     <display:column property="toolTip" title="toolTip" sort="true"/>
     <display:column property="roles" title="roles" sort="true"/>
    
     <display:column property="width" title="width" sort="true"/>
     <display:column property="height" title="height" sort="true"/>
    

     <display:column property="align" title="align" sort="true"/>
     <display:column property="url" title="url" sort="true"/>
     
     
     
     <!--===================================--->
     <!--==========��������Ϊ��ҳҪ�޸ĵĵط�====--->
     <!--===================================--->
     
 
    <!--=============================================-->
    <!--=============���´��벻Ҫ�Ķ� ==================-->
    <!--=============================================-->
    
    <display:column title="��������" media="html" >
          <button class="textBox" onclick="common_view('<c:out value='${row.id}'/>');">�鿴</button>
          <button class="textBox" onclick="common_edit('<c:out value='${row.id}'/>');">�༭</button>
          <button class="textBox" onclick="common_delete('<c:out value='${row.id}'/>');">ɾ��</button>
     </display:column>
    
    
     <display:column title="��������" media="xxx" >
          <a href="#" onclick="common_view('<c:out value='${row.id}'/>');">�鿴</a>
          <a href="#" onclick="common_edit('<c:out value='${row.id}'/>');">�༭</a>
          <a href="#" onclick="common_delete('<c:out value='${row.id}'/>');">ɾ��</a>
     </display:column>
     
      <!-- ҳ���ϲ���ʾ����-->
     <display-el:column property="id"
                     media="xxx"
                     title="ID" 
                     paramId="id" 
                     paramProperty="id"
                     href="${pageContext.request.requestURL}?method=preUpdateOne"> 
     </display-el:column>   
     
     <!-- ҳ���ϲ���ʾ����-->
     <display:column property="button"  title="��������" media="xxx" />     
     
     <!-- ҳ���ϲ���ʾ����-->
     <display:column title="��������" media="xxx" >
          <button onclick="common_view('<c:out value='${row.id}'/>');">�鿴</button>
          <button onclick="common_edit('<c:out value='${row.id}'/>');">�༭</button>
          <button onclick="common_delete('<c:out value='${row.id}'/>');">ɾ��</button> 
     </display:column>
     
      
     <display:caption>�˵��б�</display:caption>
     <display:footer>
        <tr>
          <td colspan="2"></td>
          <td></td>
        </tr>
     </display:footer>     
</display-el:table>

</form >



</td>
</tr>
</table>



</body>
</html>


<%@ include file="/common/footer.jsp"%>
