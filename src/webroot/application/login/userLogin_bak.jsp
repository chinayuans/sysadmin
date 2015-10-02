<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<html> 
<head>
  <title></title>
   <!--html:javascript formName="userLoginForm"/-->
</head>
<script type="text/javascript">
<!--
function validateUserLoginForm(object){
  var user_name=object["user.user_name"].value;
  var user_password=object["user.user_password"].value;
  if(user_name==null||user_name==""||user_name.length==0){
     alert("用户名不可以为空!");
     return false;
  }
  if(user_password==null||user_password==""||user_password.length==0){
     alert("密码不可以为空!");
     return false;
  }
  

}
//-->
</script>
<html:errors/>
   
<body>
 <html:form action="/user.do?method=login" 
            focus="user.user_name"
            onsubmit="return validateUserLoginForm(this);">
    <table class="border">
        <tr>
            <td>&nbsp;<bean:message key="user.user_name" />:</td>
            <td>&nbsp;<html:text property="user.user_name" /></td>
            <td>&nbsp;<bean:message key="user.user_password" />:</td>
            <td>&nbsp;<html:password property="user.user_password" /></td>
            
        <tr >            
            <td colspan='4'>
            <center>
               <html:submit styleClass="button">提 交</html:submit>
               <html:reset  styleClass="button">重 置</html:reset>
            </center>   
            </td>
        </tr>
    </table>
 </html:form>
</body>
</html>
<%@ include file="/common/footer.jsp"%>