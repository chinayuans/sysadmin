<html>
<head>

<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" pageEncoding="GB2312" %>

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


<script type="text/javascript" src="<c:url value='/scripts/calendar_cn.js'/>">
</script>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<base href="<%=basePath%>">
   
</head>
  
<title>Calendar</title>
<body onclick="document_onclick(event)" onkeyup="document_onkeyup(event)" onload="init('#336BA0');">
    

    
  <html:form action="/user" >
  <table>
   <tr >
    
    <td>&nbsp;<html:text property="user.birthday" onblur="javascript:document_onclick(event);" />
        <a HREF="javascript:void(0);">
        <img width="18" height="18" BORDER="0" ALIGN="ABSMIDDLE" src="images/calendar.gif" onClick="setday(this,document.getElementsByName('user.birthday')[0],'yyyymmdd');">
        </a>
        &nbsp;
        <SPAN CLASS="comment"> Date Format: YYYY-MM-DD 
    </td>
           
        </tr>
   </table> 
  </html:form> 
   

  </body>
</html>
