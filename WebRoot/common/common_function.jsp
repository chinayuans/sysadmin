<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" pageEncoding="GB2312" %>



<!--下面这段代码用来查看是否要显示页面中的代码--->
<!-- 在不需要重新启动应用服务器的情况下 设置on ,off来是否显示jsp源代码 -->
<!-- 设置web.xml中的参数来是否显示jsp源代码,需要重新启动机器.
 <context-param>
        <param-name>view_jsp_sourceCode</param-name>
        <param-value>false</param-value>
    </context-param>
  
-->

<!-- 上面两个开关全部关闭的情况下,查看源代码才不会显示-->
<c:set var="view_jsp_sourceCode" value="off"/>

<c:if test="${view_jsp_sourceCode=='on' or initParam.view_jsp_sourceCode=='true'}" >
    <ul id="showsource">
            <li><a href="<%=request.getRequestURI()%>.source">查看页面源代码</a></li>
    </ul>
</c:if>