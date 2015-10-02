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
							        <td>&nbsp;<bean:message key="menuItem.name" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.name"
							            name="menuItemForm" /></td>
							        <td>&nbsp;<bean:message key="menuItem.title" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.title"
							            name="menuItemForm" /></td>
							    </tr>
							    <tr>
							        <td>&nbsp;<bean:message key="menuItem.parent_name" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.parent_name"
							            name="menuItemForm" /></td>
							        <td>&nbsp;<bean:message key="menuItem.description" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.description"
							            name="menuItemForm" /></td>
							    </tr>
							    <tr>
							        <td>&nbsp;<bean:message key="menuItem.forward" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.forward"
							            name="menuItemForm" /></td>
							        <td>&nbsp;<bean:message key="menuItem.action" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.action"
							            name="menuItemForm" /></td>
							    </tr>
							    <tr>
							        <td>&nbsp;<bean:message key="menuItem.location" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.location"
							            name="menuItemForm" /></td>
							        <td>&nbsp;<bean:message key="menuItem.target" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.target"
							            name="menuItemForm" /></td>
							    </tr>
							    <tr>
							        <td>&nbsp;<bean:message key="menuItem.onclick" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.onclick"
							            name="menuItemForm" /></td>
							        <td>&nbsp;<bean:message key="menuItem.onmouseover" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.onmouseover"
							            name="menuItemForm" /></td>
							    </tr>
							    <tr>
							        <td>&nbsp;<bean:message key="menuItem.onmouseout" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.onmouseout"
							            name="menuItemForm" /></td>
							        <td>&nbsp;<bean:message key="menuItem.image" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.image"
							            name="menuItemForm" /></td>
							    </tr>
							    <tr>
							        <td>&nbsp;<bean:message key="menuItem.altImage" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.altImage"
							            name="menuItemForm" /></td>
							        <td>&nbsp;<bean:message key="menuItem.toolTip" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.toolTip"
							            name="menuItemForm" /></td>
							    </tr>
							    <tr>
							        <td>&nbsp;<bean:message key="menuItem.roles" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.roles"
							            name="menuItemForm" /></td>
							        <td>&nbsp;<bean:message key="menuItem.page" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.page"
							            name="menuItemForm" /></td>
							    </tr>
							    <tr>
							        <td>&nbsp;<bean:message key="menuItem.width" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.width"
							            name="menuItemForm" /></td>
							        <td>&nbsp;<bean:message key="menuItem.height" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.height"
							            name="menuItemForm" /></td>
							    </tr>
							    <tr>
							        <td>&nbsp;<bean:message key="menuItem.align" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.align"
							            name="menuItemForm" /></td>
							        <td>&nbsp;<bean:message key="menuItem.url" />:</td>
							        <td>&nbsp;<bean:write property="menuItem.url"
							            name="menuItemForm" /></td>
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

