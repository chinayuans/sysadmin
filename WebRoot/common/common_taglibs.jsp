<!--�����������-->
<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" pageEncoding="GB2312" %>

<!--Struts Tag Library -->
<!-- 
      el ��ʾexpression language ��${} ,��ͬ��JSTL,ע��
      ֻ��servlet2.4/jsp2.0�ſ���֧�֡�
      Servlet 2.3/JSP 1.2���磺Tomcat 4.1.29
      Servlet 2.4/JSP 2.0���磺Tomcat 5.0���ϡ�
-->
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean-el" prefix="bean-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic-el" prefix="logic-el" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!--JSTL Tag Library -->
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="x"   uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>

<!-- DisplayTag Library -->
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="display-el" uri="http://displaytag.sf.net/el" %>

<!-- Struts-menu Tag Library -->
<%@ taglib prefix="menu" uri="http://struts-menu.sf.net/tag" %>
<%@ taglib prefix="menu-el" uri="http://struts-menu.sf.net/tag-el" %>

<!-- Sitemesh Tag Library -->
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<!-- Jsp log Library -->
<%@ taglib prefix="log" uri="http://jakarta.apache.org/taglibs/log-1.0" %>

<!-- Customized tag Library -->
<%@ taglib prefix="myapp" uri="/WEB-INF/taglib/myapp.tld" %>
<%@ taglib prefix="myapp-el" uri="/WEB-INF/taglib/myapp-el.tld" %>



<!--
����ʹ��jsp log ��ǩ:
��message��Ϣ����� ��̨dos����̨��ȥ��
-->
<!--log:debug message="welcome to use log tag"/-->
<!--log:info message="welcome to use log tag"/-->
<!--log:warn message="welcome to use log tag"/-->
<!--log:error message="welcome to use log tag"/-->
<!--log:fatal message="welcome to use log tag"/-->

<!--log:dump ��ǩ�Ὣ���ַ�Χ�ڵ��������ݴ�ӡ����ҳ��ȥ�� -->
<!--log:dump scope="page"/-->
<!--log:dump scope="request"/-->
<!--log:dump scope="session"/-->
<!--log:dump scope="application"/-->
<!--
����������������web-inf/classesĿ¼�µ�log4j.properties����log4j.xml����
-->
<!--����-->
<log:warn message="��ӭʹ��log��ǩ"/>
