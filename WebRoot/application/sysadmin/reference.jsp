<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<html>
<head>
<title>列表显示</title>
</head>
<body>



<%@ include file="/common/banner.jsp"%>


<button onclick="location.href='user.do?method=preSaveOne'">增加用户</button>

<!--
<table class="list">
    <thead>
        <tr>
            <th>Delete</th>
            <th>User Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Birthday</th>
        </tr>
    </thead>
    <tbody>
        
        <c:forEach var="user" items="${data}" varStatus="status">
            <c:choose>
                <c:when test="${status.count % 2 == 0}">
                    <tr class="even">
                </c:when>
                <c:otherwise>
                    <tr class="odd">
                </c:otherwise>
            </c:choose>
            <td>
                <a href="user.do?method=removeOne&amp;id=<c:out value='${user.id}'/>">
                  删除
                </a>
            </td>
            <td>
                <a href="user.do?method=preUpdateOne&amp;id=<c:out value='${user.id}'/>">
                <c:out value="${user.id}"/>
                </a>
            </td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.birthday}"/></td>
            
            </tr>
        </c:forEach>
    </tbody>
</table>

-->

<!-- 下面这段代码用来显示displaytag不同的显示风格 -->
<button onclick="changeStyle('table2');">改变表格风格</button>
<script type="text/javascript">
<!--
var i=0;
function changeStyle(object){
  var styles=new Array("its","mars","simple","report","mark","isis");
  swapClass(eval(object),styles[i]);
  //eval(object).className=styles[i];
  if (i==5){
      i=0;
  }
  else{
     i++;
  }
     
}
//-->
</script>



<!--c:import url="/jstl/jstl.jsp"/-->


<c:if test="${not empty data}">
  <c:set var="users" value="${data}" scope="session"/>
</c:if>


<!-- 表格1 session 范围的 -->
<display:table uid="table1"
               name="sessionScope.users" 
               pagesize="5"
               styleClass="list"
               export="false"               
               defaultsort="1"
               length=""
               offset="2">

<!--页面上控制阻止不输出到csv格式.  -->               
<display:setProperty name="export.csv" value="false"/>
<display:setProperty name="paging.banner.placement" value="top" />      
          
     <display:column property="id"
                     title="ID" 
                     paramId="id" paramProperty="id"
                     href="user.do?method=preUpdateOne" 
                     sort="true"/>
     <display:column property="firstName" title="第一姓名" nulls="true" sort="true"/>
     <display:column property="lastName"  title="第二姓名" media="all"  sort="true"/>
     <display:column property="birthday"  title="生日"  sort="true"/>
     <display:caption>用户列表</display:caption>
     <display:footer>
        <tr>
          <td colspan="2">表尾左边</td>
          <td>表尾右边</td>
        </tr>
     </display:footer>
     
</display:table>


<!-- 表格2 request 范围的 -->

<display:table uid="table2"
               name="data" 
               pagesize="5"
               styleClass="list"
               export="false"
               requestURI="user.do?method=queryAll"
               defaultsort="1"
               length=""
               offset="2"
               class="report"
               decorator="org.displaytag.sample.OperatorWrapper"
               >

<!--页面上控制阻止不输出到csv格式.  -->               
<display:setProperty name="export.csv" value="false"/>
<display:setProperty name="paging.banner.placement" value="bottom" />      
          
     <display:column property="id"
                     title="ID" 
                     paramId="id" 
                     paramProperty="id"
                     href="user.do?method=preUpdateOne" 
                     sort="true"/>
     <display:column property="firstName" title="第一姓名" nulls="true" sort="true"/>
     <display:column property="lastName"  title="第二姓名" media="all"  sort="true"/>
     <display:column property="birthday"  title="生日"  sort="true" class="tableCellError" />
     <display:column property="operator"  title="操作类型" />
     
     
     
     
     <display:caption>用户列表</display:caption>
     <display:footer>
        <tr>
          <td colspan="2">表尾左边</td>
          <td>表尾右边</td>
        </tr>
     </display:footer>
     
</display:table>
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

</body>
</html>


<%@ include file="/common/footer.jsp"%>
