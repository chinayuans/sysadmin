<%@ page language="java" isErrorPage="true" %>
<!--�����������-->
<%@ include file="/common/header.jsp"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<!--error.jsp��errorpage.jsp�в�ͬ���ô�  -->


<head>
<title>������Ϣ</title>
<META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>

     Ӧ�ó����з������� 

<% if (exception != null) { %>
    <pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre>
<% } else { %>
   ������־����ø������Ϣ��
<% } %>
