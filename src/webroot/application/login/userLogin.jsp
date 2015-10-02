<%@ include file="/common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/_ListAll_table.css'/>" />

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


   
<body>
<table >


<tr height="15%" >
    <td>
    <p align="center">
         <font color="#CC3300" size="7"  style=""face="Courier New, Courier, mono">
            <strong>C座1319公司</strong>
         </font> 
    </p>
    
    </td>
</tr>

<tr height="10%"> 
  <td><!-- 报告登陆错误信息 -->
     <!--html:errors /-->
  </td>
</tr>

<tr height="30%">
  <td>
    <table height="30%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#ccceee" bgcolor="#FFFFFF">
    
    <html:form action="/user.do?method=login" 
            focus="user.user_name"
            onsubmit="return validateUserLoginForm(this);">
      <tr> 
        <td  colspan="4"> 
        <div align="center">
            <font size="5" face="楷体_GB2312">
                <strong>欢迎使用MIS系统</strong>
            </font>
        </div>
        </td>
      </tr>
      
      <tr > 
        <td rowspan="3" width="15%">&nbsp;</td>
        <td width="35%">
              <div align="right">
                  <font size="3" face="楷体_GB2312">
                  &nbsp;<bean:message key="user.user_name" />: 
                  </font>
              </div></td>
        <td width="35%">
              <div align="left">
                <html:text property="user.user_name" />
              </div></td>
        <td rowspan="3" width="15%">&nbsp;</td>
      </tr>
      
      <tr > 
        <td><div align="right">
                  <font size="3" face="楷体_GB2312">
                  &nbsp;<bean:message key="user.user_password" />:
                  </font>
            </div></td>
        <td><div align="left"><html:password property="user.user_password" /></div></td>
      </tr>
      
      <tr > 
        <td> 
           <div align="right">
           <html:submit styleClass="button"><bean:message key="submit" /></html:submit>
           </div>
        </td>
        <td>
           <div align="left">
           <html:reset  styleClass="button"><bean:message key="reset" /></html:reset>
           </div>
        </td>
      </tr>
      
      <tr > 
        <td colspan="4" bgcolor="#FFFFFF"> <div align="center"><font size="5" face="楷体_GB2312"><strong>&nbsp;</strong></font></div></td>
      </tr>
      
        
      </html:form>
        
    </table>

  </td>
</tr>
   
<tr>
 <td></td>
</tr>
</table>





 

</body>
</html>
<%@ include file="/common/footer.jsp"%>