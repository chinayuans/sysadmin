<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<title>上传文件</title>
<!--
	The most important part is to declare your form's enctype to be "multipart/form-data",
	and to have a form:file element that maps to your ActionForm's FormFile property
-->



<html:form action="uploadFile" method="post" styleId="uploadForm"
    enctype="multipart/form-data" onsubmit="return validateUploadForm(this)">
<table class="detail">
    <tr>
        <th>
            上传名字：
        </th>
        <td>
            <html:text property="name" size="40" styleId="name" />
        </td>
    </tr>
    <tr>
        <th>
            上传文件：
        </th>
        <td>
            <html:file property="file" size="50" styleId="file" />
        </td>
    </tr>
    <tr>
        <td></td>
        <td class="buttonBar">
            <html:submit styleClass="button" onclick="bCancel=false">
            	<fmt:message key="button.upload"/>
            </html:submit>
            <html:cancel styleClass="button" onclick="bCancel=true">
            	<fmt:message key="button.cancel"/>
            </html:cancel>
        </td>
    </tr>
</table>

</html:form>

<script type="text/javascript">
<!--
highlightFormElements();
// -->
</script>
<html:javascript formName="uploadForm" cdata="false"
    dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" 
    src="<c:url value="/scripts/validator.jsp"/>"></script>
    
    
<%@ include file="/common/footer.jsp"%>    