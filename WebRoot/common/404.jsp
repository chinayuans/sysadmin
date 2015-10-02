<%-- SiteMesh has a bug where error pages aren't decorated - hence the full HTML --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        
<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <title>404 ~ 找不到页面</title>
    <meta http-equiv="content-type" content="text/html; charset=GB2312"/>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <link href="${ctx}/css/global.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/images/favicon.ico" rel="SHORTCUT ICON"/>
</head>
<body>

<div >
    <p style="text-align: center; margin-top: 20px">
          您所请求的页面不存在，您需要返回到
    <a href="<c:url value="/"/>">欢迎页面</a>！
    </p>

    <p style="text-align: center; margin-top: 20px">
      <img style="border: 0"
           src="<c:url value="/images/404.jpg"/>"
           alt="再见" />
    </p>
</div>
   

</body>
</html>


