<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="ISO-8859-1"
import="java.util.*" 
%>
<html>
<!--JSTL Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<script>
<!--
function addRow(table){
var row=table.insertRow(table.rows.length);
var col=document.createElement("TD");
col.setAttribute("align","center");
row.appendChild(col);
col.innerHTML='<input type="button" value="-" onClick="deleteRow('+table.id+')"/>';

col=document.createElement("TD");
row.appendChild(col);
col.innerHTML='<input type="text" id="text1" name="text1" size="50"/>';

col=document.createElement("TD");
row.appendChild(col);
col.innerHTML='<input type="text" id="text2" name="text2" size="50"/>';
}    

function deleteRow(table){
var src=event.srcElement;
	while (src!=null){
	   while (src.nodeName=="TR"){
	   eval(table).deleteRow(src.rowIndex);
	   return;   
	   }
	   src=src.parentElement;
	}
}
      
-->
</script>
    
<head>
    <title>tab.html</title>
</head>
<table width="100%" >
  <tr> 
    <td>good </td>
    <td>welcome</td>
  </tr>
  <tr>
    <td height='28' colspan='8'>
     <table id="innerTable2">
       <tr>
         <td>
           <input name="submit"  type="button" onClick="addRow(innerTable2);"  value="+">
         </td>
         <td>
         </td>
         <td>
         </td>
        </tr>
        
        <% String[] array_str={"a","b","c"}; 
           request.setAttribute("array_str",array_str);
        %>
        <c:set var="count" value="0"/>
        <c:out value="${count}"/>
        <c:forEach items="${array_str}" > 
          <tr>
            <td>
            <input type="button" value="-" onClick="deleteRow(innerTable2)"/>
            </td>
            <td>
            <input type="text" name="text1" id="text1" value="<c:out value='${array_str[count]}'/>" size='50'/>
            </td>
            <td>
            <input type="text" name="text2" id="text2" value="<c:out value='${array_str[count]}'/>" size='50'/>
            </td>
          </tr>
          <c:set var="count" value="${count+1}"/>
        </c:forEach>
               
      </table>   
  </tr>

</table>


</html>
