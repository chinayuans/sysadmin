<!--�����������-->
<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" pageEncoding="GB2312" %>



<!--������δ��������鿴�Ƿ�Ҫ��ʾҳ���еĴ���--->
<!-- �ڲ���Ҫ��������Ӧ�÷������������ ����on ,off���Ƿ���ʾjspԴ���� -->
<!-- ����web.xml�еĲ������Ƿ���ʾjspԴ����,��Ҫ������������.
 <context-param>
        <param-name>view_jsp_sourceCode</param-name>
        <param-value>false</param-value>
    </context-param>
  
-->

<!-- ������������ȫ���رյ������,�鿴Դ����Ų�����ʾ-->
<c:set var="view_jsp_sourceCode" value="off"/>

<c:if test="${view_jsp_sourceCode=='on' or initParam.view_jsp_sourceCode=='true'}" >
    <ul id="showsource">
            <li><a href="<%=request.getRequestURI()%>.source">�鿴ҳ��Դ����</a></li>
    </ul>
</c:if>