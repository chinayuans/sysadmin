<%@ include file="/common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/InputOrEdit.css'/>" />

<!--�����������-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>
<html>
<head>
<title></title>
</head>
<body>
<!-- ���ҳ��������Ϊ
      ����һ����¼��
      ����һ����¼��
      ����ɾ��������¼��
      ������ѯ������¼
      �Ĺ�����������ҳ�� 
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
					
					
							<p><bean:message key="InputOrEdit.information" />��</p>
							<!--struts validator Ч�鿪ʼ-->
							<c:if test="${method=='saveOne' or method=='updateOne' or method=='updateSome'}">
							  <html:javascript formName="departmentForm"/><!--�޸�֮��-->
							</c:if>
							<!--������� -->
							<html:errors/>
							
							<!--target="listing" ������ɾ��������_ListAll.jsp�ļ��ò���ˢ�º������ -->
							<html:form action="/department" 
							           focus="department.name" 
							           onsubmit="return validateDepartmentForm(this);">  <!--�޸�֮��-->
							
							
							    <c:if test="${not empty method}">
							          <html-el:hidden property="method" value="${method}"/>
							    </c:if>
							    <c:if test="${ empty method}">
							          <html-el:hidden property="method" value="${param.method}" />
							    </c:if>
							    
							    
							    <!--�޸�֮����ʼ-->
							    <html:hidden property="department.id" />
							    <table class="border">
							        <tr>
							            <td><bean:message key="department.name" />:</td>
							            <td><html:text property="department.name" styleClass="text" /></td>
							            
							            <td><bean:message key="department.company" />:</td>
							            <td>
							               <html:select property="department.company.id" >
							                 <html:options collection="companys" labelProperty="name" property="id"/>            
							                 <!--html:option value="1"-->xxx<!--/html:option-->
							               </html:select >
							            </td>
							            <!--td--><!--html:text property="department.company.name" styleClass="text"/--><!--/td-->
							        </tr>
							         <tr>
							            <td><bean:message key="department.comments" />:</td>
							            <td><html:text property="department.comments" styleClass="text"/></td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							        </tr>
							      
							     <!--�޸�֮������--> 
							        
							        
							        
							        
							        
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

