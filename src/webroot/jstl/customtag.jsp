<%@ include file="/common/common_taglibs.jsp"%>

<%@ taglib prefix="myapp" uri="/WEB-INF/taglib/myapp.tld" %>
<%@ taglib prefix="myapp-el" uri="/WEB-INF/taglib/myapp-el.tld"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<html>
<head>
<title>欢迎您</title>

</head>

<body>

    <% 
    	 java.util.List list = new java.util.ArrayList();
//      	初始化数据
          for (int i = 0; i < 10; i++) { 
              	org.appfuse.model.taglibs.LabelValue label=new org.appfuse.model.taglibs.LabelValue();
              	label.setValue(""+i);
              	label.setProp1("a"+i);
              	label.setProp2("b"+i);
              	label.setProp3("c"+i);
              	label.setProp4("d"+i);
              	 if (!list.contains(label)) {
                       list.add(label);
                   }
          }
       pageContext.setAttribute("list",list);
    %>

   <c:forEach var="list" items="${list}" >      
      <myapp-el:selectionOptions name="name" object="LabelValue" defaultvalue='${list.value}' keycol="value"  valuecol="prop1&prop2"/>
      <myapp-el:selectionOptions name="name" object="LabelValue" defaultvalue='1' keycol="value"  valuecol="prop1&prop2"/>
   </c:forEach>
   
   
   <c:set var="information" value="1-beijing|2-tianjing|3-shanghai"/>
   <c:set var="default" value="2-tianjing"/>
   <myapp-el:checkboxOptions name="check" msg="${information}" defaultvalue="${default}"/> 
   <myapp-el:radioOptions name="radio" msg="${information}" defaultvalue="${default}"/> 
</body>
</html>


<%--
<myapp:selectOptions name="name" object="userobj" defaultvalue="001" keycol="userid"  

valuecol="name|age"/>

<forEach>
  <myapp:selectOptions object="<c:out value=""/>" defaultvalue="<c:out value=""/>" 

keycol="<c:out value=""/>"  valuecol="<c:out value=""/>"/>  
</forEach>  --%>