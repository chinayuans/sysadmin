<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" pageEncoding="GB2312" %>


<!-- 1.设置系统的错误页面-->
<%@ page errorPage="/common/errorpage.jsp" %>

<!-- 2.导入通用标签文件-->
<%@ include file="/common/common_taglibs.jsp"%>

<!-- 3.导入特定功能文件：目前只有查看源代码的功能。-->
<%@ include file="/common/common_function.jsp"%>

<!-- 4.导入通用css文件-->
<%@ include file="/common/common_css.jsp"%>

<!-- 5.导入通用js文件-->
<%@ include file="/common/common_js.jsp"%>


<!-- 注意以上文件的先后关系：2，3，4都必须建立在1成功导入的前提下。
     3,4又必须建立在2成功导入的前提下，因为3.4都用到了2中定义好了的标签库 
-->


