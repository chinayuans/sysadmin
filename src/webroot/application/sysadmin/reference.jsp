<%@ include file="/common/header.jsp"%>
<!--�����������-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<html>
<head>
<title>�б���ʾ</title>
</head>
<body>



<%@ include file="/common/banner.jsp"%>


<button onclick="location.href='user.do?method=preSaveOne'">�����û�</button>

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
                  ɾ��
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

<!-- ������δ���������ʾdisplaytag��ͬ����ʾ��� -->
<button onclick="changeStyle('table2');">�ı�����</button>
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


<!-- ���1 session ��Χ�� -->
<display:table uid="table1"
               name="sessionScope.users" 
               pagesize="5"
               styleClass="list"
               export="false"               
               defaultsort="1"
               length=""
               offset="2">

<!--ҳ���Ͽ�����ֹ�������csv��ʽ.  -->               
<display:setProperty name="export.csv" value="false"/>
<display:setProperty name="paging.banner.placement" value="top" />      
          
     <display:column property="id"
                     title="ID" 
                     paramId="id" paramProperty="id"
                     href="user.do?method=preUpdateOne" 
                     sort="true"/>
     <display:column property="firstName" title="��һ����" nulls="true" sort="true"/>
     <display:column property="lastName"  title="�ڶ�����" media="all"  sort="true"/>
     <display:column property="birthday"  title="����"  sort="true"/>
     <display:caption>�û��б�</display:caption>
     <display:footer>
        <tr>
          <td colspan="2">��β���</td>
          <td>��β�ұ�</td>
        </tr>
     </display:footer>
     
</display:table>


<!-- ���2 request ��Χ�� -->

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

<!--ҳ���Ͽ�����ֹ�������csv��ʽ.  -->               
<display:setProperty name="export.csv" value="false"/>
<display:setProperty name="paging.banner.placement" value="bottom" />      
          
     <display:column property="id"
                     title="ID" 
                     paramId="id" 
                     paramProperty="id"
                     href="user.do?method=preUpdateOne" 
                     sort="true"/>
     <display:column property="firstName" title="��һ����" nulls="true" sort="true"/>
     <display:column property="lastName"  title="�ڶ�����" media="all"  sort="true"/>
     <display:column property="birthday"  title="����"  sort="true" class="tableCellError" />
     <display:column property="operator"  title="��������" />
     
     
     
     
     <display:caption>�û��б�</display:caption>
     <display:footer>
        <tr>
          <td colspan="2">��β���</td>
          <td>��β�ұ�</td>
        </tr>
     </display:footer>
     
</display:table>
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

</body>
</html>


<%@ include file="/common/footer.jsp"%>
