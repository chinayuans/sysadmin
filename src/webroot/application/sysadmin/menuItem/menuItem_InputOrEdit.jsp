<%@ include file="/common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/InputOrEdit.css'/>" />

<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>
<html>
<head>
<title></title>
</head>
<body>
<!-- 这个页面用来作为
      增加一条记录，
      更新一条记录，
      条件删除多条记录，
      条件查询多条记录
      的公共输入数据页面 
-->

<center>
<table border="0" width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="8" height="26"><img src="images/tbl/m11.gif"></td>
					<td background="images/tbl/m10.gif" height="26"><img src="images/tbl/empty.gif"></td>
					<td width="8" height="26"><img src="images/tbl/m12.gif"></td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr >
		<td>
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="7" background="images/tbl/m3.gif"><img src="images/tbl/empty.gif"></td>					
					<td background="images/tbl/m6.gif" align="center" valign="top">
					
					
							<p><bean:message key="InputOrEdit.information" />：</p>
							<!--struts validator 效验开始-->
							<c:if test="${method=='saveOne' or method=='updateOne' or method=='updateSome'}">
							   <html:javascript formName="menuItemForm"/><!--修改之处-->
							</c:if>
							<html:errors/>
							
							<!--target="listing" 不可以删除，否则_ListAll.jsp文件得不到刷新后的数据 -->
							<html:form action="/menuItem" 
							           focus="menuItem.name"
							           onsubmit="return validateMenuItemForm(this);" >  <!--修改之处-->
							
							
							    <c:if test="${not empty method}">
							          <html-el:hidden property="method" value="${method}"/>
							    </c:if>
							    <c:if test="${ empty method}">
							          <html-el:hidden property="method" value="${param.method}" />
							    </c:if>
							    
							    <!--修改之处开始-->
							    <html:hidden property="menuItem.id" />
							    <table class="border">
							        <tr>
							            <td><bean:message key="menuItem.name" />:</td>
							            <td><html:text property="menuItem.name" styleClass="text" /></td>
							            <td><bean:message key="menuItem.title" />:</td>
							            <td><html:text property="menuItem.title" styleClass="text"/></td>
							        </tr>
							        
							        <tr>
							            <td><bean:message key="menuItem.parent_name" />:</td>
							            <td><html:text property="menuItem.parent_name" styleClass="text" /></td>
							            <td><bean:message key="menuItem.description" />:</td>
							            <td><html:text property="menuItem.description" styleClass="text"/></td>
							        </tr>
							        
							        <tr>
							            <td><bean:message key="menuItem.forward" />:</td>
							            <td><html:text property="menuItem.forward" styleClass="text" /></td>
							            <td><bean:message key="menuItem.action" />:</td>
							            <td><html:text property="menuItem.action" styleClass="text"/></td>
							        </tr>
							        
							        <tr>
							            <td><bean:message key="menuItem.location" />:</td>
							            <td><html:text property="menuItem.location" styleClass="text" /></td>
							            <td><bean:message key="menuItem.target" />:</td>
							            <td><html:text property="menuItem.target" styleClass="text"/></td>
							        </tr>
							        
							        <tr>
							            <td><bean:message key="menuItem.onclick" />:</td>
							            <td><html:text property="menuItem.onclick" styleClass="text" /></td>
							            <td><bean:message key="menuItem.onmouseover" />:</td>
							            <td><html:text property="menuItem.onmouseover" styleClass="text" /></td>
							        </tr>
							        
							        <tr>
							            <td><bean:message key="menuItem.onmouseout" />:</td>
							            <td><html:text property="menuItem.onmouseout" styleClass="text" /></td>
							            <td><bean:message key="menuItem.image" />:</td>
							            <td><html:text property="menuItem.image" styleClass="text"/></td>
							        </tr>
							        
							        <tr>
							            <td><bean:message key="menuItem.altImage" />:</td>
							            <td><html:text property="menuItem.altImage" styleClass="text" /></td>
							            <td><bean:message key="menuItem.toolTip" />:</td>
							            <td><html:text property="menuItem.toolTip" styleClass="text"/></td>
							        </tr>
							        
							        
							        <tr>
							            <td><bean:message key="menuItem.roles" />:</td>
							            <td><html:text property="menuItem.roles" styleClass="text" /></td>
							            <td><bean:message key="menuItem.page" />:</td>
							            <td><html:text property="menuItem.page" styleClass="text"/></td>
							        </tr>
							        
							        
							        <tr>
							            <td><bean:message key="menuItem.width" />:</td>
							            <td><html:text property="menuItem.width" styleClass="text" /></td>
							            <td><bean:message key="menuItem.height" />:</td>
							            <td><html:text property="menuItem.height" styleClass="text"/></td>
							        </tr>
							        
							     
							        
							         <tr>
							            <td><bean:message key="menuItem.align" />:</td>
							            <td><html:text property="menuItem.align" styleClass="text" /></td>
							            <td><bean:message key="menuItem.url" />:</td>
							            <td><html:text property="menuItem.url" styleClass="text"/></td>
							        </tr>
							     <!--修改之处结束--> 
							        
							        
							        
							        
							        
							        <tr >            
							            <td colspan='4'>
							            <center>
							               <html:submit styleClass="button"><bean:message key="submit" /></html:submit>
							               <html:reset  styleClass="button"><bean:message key="reset" /></html:reset>
							               <button  class="button" onclick="hideFrame();" ><bean:message key="back" /></button>
							            </center>   
							            </td>
							        </tr>
							    </table>
							</html:form>
							
							<%@ include file="/common/footer.jsp"%>
					
					
					
					
					
					

					</td>
					<td width="7" background="images/tbl/m4.gif"><img src="images/tbl/empty.gif"></td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td >
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="9" height="26"><img src="images/tbl/m7.gif"></td>
					<td background="images/tbl/m5.gif" height="26"><img src="images/tbl/empty.gif"></td>
					<td width="9" height="26"><img src="images/tbl/m8.gif"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</center>

</body>
</html>

