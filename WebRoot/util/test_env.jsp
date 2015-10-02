<%@ include file="/common/header.jsp"%>
<%@ page import="java.util.*" %>
<pre>
<%
out.println("request.getPathInfo()="+request.getPathInfo()); 
out.println("request.getQueryString()="+request.getQueryString()); 
out.println("request.getRequestURL()="+request.getRequestURL()); 
out.println("request.getRequestURI()="+request.getRequestURI());

out.println("request.getContextPath()="+request.getContextPath());
out.println("request.getServletPath()="+request.getServletPath());
out.println("session.getRealPath()="+request.getRealPath("/"));

out.println("session's  max life time :");
out.println(session.getMaxInactiveInterval()) ;

Properties props = System.getProperties(); // get list of properties
// Print properties using Enumeration
for (Enumeration enum1 = props.propertyNames(); enum1.hasMoreElements();) {
    String key = (String)enum1.nextElement();
    out.println("<P>"+key + " = " + (String)(props.get(key))+"</P>");
}
%>
</pre>
