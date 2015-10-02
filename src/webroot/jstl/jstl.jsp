<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*"%>

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
    
    <title></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
  </head>
  
  <body>
    
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
    
    
    <br/>
    <c:forTokens var="element" items="a,b,c" delims=",">      
        <c:out value="${element}"/>
    </c:forTokens>
    
    <br/>
    <c:forEach var="element" items="a,b,c" >      
       <ui> <c:out value="${element}"/></ui>
    </c:forEach>
    
    
    <br/>
    <c:forEach var="element" items="yyyy:xxxx:zzzz" >  
       <li><c:out value="${element}"/></li>
    </c:forEach>
    
    <br/>
    <c:forEach var="i" begin="1" end="100" step="2" >
     <c:out value="${i}"/>
     <br/>
    </c:forEach>
    
  <form name="f" action="jstl_reply.jsp">
   <input type="text" name="username" />
  </form> 
   

  </body>
</html>
