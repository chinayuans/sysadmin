<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>


<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/_ListAll_table.css'/>" />
<%@ include file="/scripts/_ListAll.js"%>


<font class="changecolor_bar" >&nbsp;</font>

<div align="right">
<img align="right" src="<c:out value="${pageContext.request.contextPath}"/>/images/solid_arrow_down.jpg" height="40" width="40" onclick="changeFramePosition(this);">
<button class="textBox" onclick="changeStyle('row');">改变表格风格</button>
</div>



<html>
<head>
<title>列表显示</title>
<META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body >


<!--  request 范围的 -->


<table border="0" >

<tr>  
    <button class="textBox" onclick="common_add();">增加</button>   
    <button class="textBox" onclick="common_delete_checked();">删除选中记录</button>   
    <button class="textBox" onclick="common_delete_condition();">条件删除</button>   
    <button class="textBox" onclick="common_query_condition();">条件查询</button>
  
</tr>
<tr>
<td>

<form  method="post">
<!-- display-el 的属性使用expression language 
     display   的属性只可以使用jsp代码
-->


<!-- 如果request中没有数据，表示没有进行更新操作。
      那么从session中取数据显示（表示经过了条件查询）。-->
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
举例说明:
request.setAttribute("user",user); //user是一个bean,里面有ID,UserName.

现在希望能够生成一个 user.do?method=saveOne&Name=yuanjs这样一个href.
那么:
href="user.do?method=saveOne";   -------指定url
paramId="Name"                                  -------指定往url中增加的参数名字
paramName="user"                              --------往参数中要赋值的那个bean名字
paramProperty="UserName"   -------往参数中要赋值的那个bean的属性名字
paramScope="request" ---------------往参数中要赋值的那个bean的范围
-->



    <!--页面上控制：    不输出到csv格式.  -->               
    <display:setProperty name="export.csv" value="false"/>
    
    <display:setProperty name="paging.banner.placement" value="top" />      
    <display:setProperty name="export.excel.include_header" value="false" /> 
         
     
     <display:column media="html" title="<input id='selectAll_CheckBox' type='checkbox' onclick=\"selectAll_CheckedBox('ids',this);\" /> ">
        <input id="ids"   name="ids" type="checkbox" value="<c:out value='${row.id}'/>"/>
     </display:column>
     
     <display:column title="序号" media="html" sort="true">
         <a href="#" onclick="common_edit('<c:out value='${row.id}'/>');">
                <%=pageContext.getAttribute("row_rowNum")%>
         </a>
     </display:column>
     <display:column title="序号" media="xxx" ><c:out value="${row_rowNum}"/> </display:column>
     
    <!--=============================================-->
    <!--=============以上代码不要改动 ==================-->
    <!--=============================================-->

     
     <!--=======================================--->
     <!--==========以下内容为本页要修改的地方========--->
     <!--除了下面的地方外，其他地方为公用部分，不可以改动--->
     <!--=======================================--->
     
     
     
     <display:column property="name" title="菜单名" nulls="true" sort="true"/>
     <display:column property="title" title="菜单显示名" nulls="true" sort="true"/>
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
     <!--==========以上内容为本页要修改的地方====--->
     <!--===================================--->
     
 
    <!--=============================================-->
    <!--=============以下代码不要改动 ==================-->
    <!--=============================================-->
    
    <display:column title="操作类型" media="html" >
          <button class="textBox" onclick="common_view('<c:out value='${row.id}'/>');">查看</button>
          <button class="textBox" onclick="common_edit('<c:out value='${row.id}'/>');">编辑</button>
          <button class="textBox" onclick="common_delete('<c:out value='${row.id}'/>');">删除</button>
     </display:column>
    
    
     <display:column title="操作类型" media="xxx" >
          <a href="#" onclick="common_view('<c:out value='${row.id}'/>');">查看</a>
          <a href="#" onclick="common_edit('<c:out value='${row.id}'/>');">编辑</a>
          <a href="#" onclick="common_delete('<c:out value='${row.id}'/>');">删除</a>
     </display:column>
     
      <!-- 页面上不显示该列-->
     <display-el:column property="id"
                     media="xxx"
                     title="ID" 
                     paramId="id" 
                     paramProperty="id"
                     href="${pageContext.request.requestURL}?method=preUpdateOne"> 
     </display-el:column>   
     
     <!-- 页面上不显示该列-->
     <display:column property="button"  title="操作类型" media="xxx" />     
     
     <!-- 页面上不显示该列-->
     <display:column title="操作类型" media="xxx" >
          <button onclick="common_view('<c:out value='${row.id}'/>');">查看</button>
          <button onclick="common_edit('<c:out value='${row.id}'/>');">编辑</button>
          <button onclick="common_delete('<c:out value='${row.id}'/>');">删除</button> 
     </display:column>
     
      
     <display:caption>菜单列表</display:caption>
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
