<%@ include file="/common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/InputOrEdit.css'/>" />

<html>
<head>
<title></title>
</head>							

<body >

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


							<p><bean:message key="InputOrEdit.information" />:</p>
							<!--struts validator Ч�鿪ʼ-->
							<c:if test="${method=='saveOne' or method=='updateOne' or method=='updateSome'}">
							   <html:javascript formName="companyForm"/><!--�޸�֮��-->
							</c:if>
							<html:errors/>
							
							<!--target="listing" ������ɾ��������_ListAll.jsp�ļ��ò���ˢ�º������ -->
							<html:form action="/company"  
							           focus="company.name"
							           onsubmit="return validateCompanyForm(this);" >  <!--�޸�֮��-->
							
							
							    <c:if test="${not empty method}">
							          <html-el:hidden property="method" value="${method}"/>
							    </c:if>
							    <c:if test="${ empty method}">
							          <html-el:hidden property="method" value="${param.method}" />
							    </c:if>
							    
							    
							    <!--�޸�֮����ʼ-->
							    <html:hidden property="company.id" />
							    <table class="border">
							        <tr>
							            <td><bean:message key="company.name" />:</td>
							            <td><html:text property="company.name" /></td>
							            <td><bean:message key="company.comments" />:</td>
							            <td><html:text property="company.comments" /></td>
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

