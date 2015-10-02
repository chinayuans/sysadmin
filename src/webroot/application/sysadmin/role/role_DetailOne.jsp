<%@ include file="/common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/InputOrEdit.css'/>" />

<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<html>
<head>
<title>详细内容显示</title>
</head>
<body>
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
					
					
							<TABLE border="1" class="border">
							    <!-- 修改开始的地方 -->
							    <tr>
							        <td>&nbsp;<bean:message key="role.description" />:</td>
							        <td>&nbsp;<bean:write property="role.description"
							                              name="roleForm" /></td>
							        <td>&nbsp;<bean:message key="role.comments" />:</td>
							        <td>&nbsp;<bean:write property="role.comments"
							                              name="roleForm" /></td>
							    </tr>
							   
							  
							    <!-- 修改结束的地方 -->
							
							
							
							    <tr>
							        <td align="center" colspan="4">
							        <center>
							        <button class="button" onclick="hideFrame();"><bean:message key="back" /></button>
							        </center>
							        </td>
							    </tr>
							</TABLE>
							
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

