<%@ include file="/common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/InputOrEdit.css'/>" />

<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<script type="text/javascript" src="<c:url value='/scripts/calendar_cn.js'/>">
</script>
<html>
<head>
<title></title>
</head>
<body onclick="document_onclick(event)" onkeyup="document_onkeyup(event)" onload="init('#336BA0');">

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
							  <html:javascript formName="userForm"/><!--修改之处-->
							</c:if>
							<!--报告错误 -->
							<html:errors/>
							
							<!--target="listing" 不可以删除，否则_ListAll.jsp文件得不到刷新后的数据 -->
							<html:form action="/user" 
							           focus="user.user_id" 
							           onsubmit="return validateUserForm(this);">
							    
							    <c:if test="${not empty method}">
							          <html-el:hidden property="method" value="${method}"/>
							    </c:if>
							    <c:if test="${ empty method}">
							          <html-el:hidden property="method" value="${param.method}" />
							    </c:if>
							   
							    <html:hidden property="user.id" />
							   
							    <table class="border">
							        <tr>
							            <td>&nbsp;<bean:message key="user.user_id" />:</td>
							            <td>&nbsp;<html:text property="user.user_id" /></td>
							            <td>&nbsp;<bean:message key="user.user_name" />:</td>
							            <td>&nbsp;<html:text property="user.user_name" /></td>
							        <tr>
							            <td>&nbsp;<bean:message key="user.user_password" />:</td>
							            <td>&nbsp;<html:password property="user.user_password" /></td>
							            <td>&nbsp;<bean:message key="user.userstatus_id" />:</td>
							            
							            <!--log:fatal message="welcome to use log tag"/-->
							            <td>&nbsp;  
							               
							               <html:select property="user.userStatus.id" >
							                 <html:options collection="userstatus_s" labelProperty="description" property="id"/>            
							               </html:select >
							               <c:if test="${user.userStatus.description==null}"></c:if>
							            </td>
							            
							        </tr>
							        <tr>
							            <td>&nbsp;<bean:message key="user.firstName" />:</td>
							            <td>&nbsp;<html:text property="user.firstName" /></td>
							            <td>&nbsp;<bean:message key="user.lastName" />:</td>
							            <td>&nbsp;<html:text property="user.lastName" /></td>
							        </tr>
							        <tr >
							            <td>&nbsp;<bean:message key="user.birthday" />:</td>
							            <td>&nbsp;<html:text property="user.birthday" onblur="javascript:document_onclick(event);" />
							                <a HREF="javascript:void(0);">
							                <img width="18" height="18" BORDER="0" ALIGN="ABSMIDDLE" src="images/calendar.gif" onClick="setday(this,document.getElementsByName('user.birthday')[0],'yyyymmdd');">
							                </a>
							                &nbsp;
							                <SPAN CLASS="comment"> Date Format: YYYY-MM-DD 
							            </td>
							            <td>&nbsp;<bean:message key="user.comments" />:</td>
							            <td>&nbsp;<html:text property="user.comments" /></td>
							        </tr>
							        
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

