<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" pageEncoding="GB2312" %>
<%@ page import="java.io.*" %>
<%@ page isErrorPage="true" %>


<html><head><title>����ҳ��</title></head>
<body>
    <p>
        �������˷�������:<%= exception.getMessage() %>
    </p>
    <p>
        ����ԭ��Ϊ:<% exception.printStackTrace(new PrintWriter(out));%>
    </p>
</body></html>