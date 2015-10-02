<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>


<title></title>



<table class="detail" cellpadding="5">
    <tr>
    	<th> Name:</th>
    	<td><c:out value="${formName}"/></td>
    </tr>
    <tr>
    	<th>Filename:</th>
    	<td><c:out value="${fileName}"/></td>
    </tr>
    <tr>
    	<th>File content type:</th>
    	<td><c:out value="${contentType}"/></td>
    </tr>
    <tr>
    	<th>File size:</th>
    	<td><c:out value="${size}"/></td>
    </tr>
    <tr>
    	<th class="tallCell">File Location:</th>
    	<td>The file has been written to: <br />
            <a href="<c:out value="${link}"/>">
            <c:out value="${location}" escapeXml="false"/></a>
        </td>
    </tr>
    <tr>
        <td></td>
        <td class="buttonBar">
            <input type="button" name="done" id="done" value="Done"
                onclick="location.href='<html:rewrite forward="failure"/>'" />
            <input type="button" name="done" id="done" value="Upload Another"
                onclick="location.href='<html:rewrite forward="selectFile"/>'" />
        </td>
    </tr>
</table>

<%@ include file="/common/footer.jsp"%>
