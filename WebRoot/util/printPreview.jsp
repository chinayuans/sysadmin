<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*"%>
<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" pageEncoding="GB2312" %>

<!--JSTL Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>




<!--Struts Tag Library -->
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
  <head>
    <base href="<%=basePath%>">
    <title>打印功能的实现</title>
    
    <!-- ＝＝＝＝＝＝＝打印功能代码的开始＝＝＝＝＝＝＝＝＝＝＝  -->
      
    <script type="text/javascript" 
            src="<c:url value='/scripts/print.js'/>">
    </script>  
    <style media=print>
        .Noprint{display:none}
        .PageNext{page-break-after: always}
    </style>
   </head>
<body>
  
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=0 VIEWASTEXT></OBJECT>    
<table >
 <tr>
     <td>
       <input type=button name=button_print value="打印" onclick="javascript:printit()" class="NOPRINT">
     </td>
    
     <td>
       <input type=button name=button_setup value="打印页面设置" onclick="javascript:printsetup()" class="NOPRINT">
     </td>
    
     <td>   
       <input type=button name=button_show value="打印预览"  onclick="javascript:printpreview()" class="NOPRINT"> 
     </td>
     
     <td>   
        <input type=button name=button_fh value="关闭" onclick="javascript:window.close();" class="NOPRINT">
     </td>
 </tr>
</table>    
    
<!-- ＝＝＝＝＝＝＝＝＝打印功能代码的结束 ＝＝＝＝＝＝＝＝＝＝＝ -->    
    
    
    
    
    
    <c:out value="hello,welcome you!"/>
    <c:out value="${1+ 2}" default="0" escapeXml="false"> </c:out>
    <c:out value="param.name" >
     Nodefault
    </c:out>
    
    <c:out value="<hr>" default="0" escapeXml="false"></c:out>
    <c:out value="<hr>" default="0" escapeXml="true"></c:out>
    
    <c:set var="variable" value="<hr>" scope="page"/>
    <c:out value="${variable}" default="0" escapeXml="false"/>
    
    <INPUT type="text" value="<c:out value="${variable}"/>"/>
    
    <c:out value="${pageScope.variable}" />
    <c:out value="${requestScope.variable}"/>
    <c:out value="${sessionScope.variable}"/>    
    <c:out value="${applicationScope.variable}"/>   
    
    <c:set var="boolean" value="true"/>    
    <c:out value="<hr>" default="Nothing" escapeXml="${boolean}"/>
    
    <% Vector v=new Vector();
       String name="Joson";
       String[] fruit={"apple","grape"};
       v.add(name);
       v.add(fruit);
       session.setAttribute("v",v);
    %>
    <c:out value="${v}"/>
    
    
    <c:forEach var="i" begin="1" end="100" step="2" >
     <c:out value="${i}"/>
     <br/>
    </c:forEach>
    
  <form name="f" action="jstl_reply.jsp">
   <input type="text" name="username" />
  </form> 
   

  </body>
</html>
