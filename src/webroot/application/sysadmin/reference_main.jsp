<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<html>
<head>
<title>列表显示</title>
<META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body>



<%@ include file="/common/banner.jsp"%>


<!--c:out value='${pageContext.request.requestURL}'/-->

<!-- 下面这段代码用来显示displaytag不同的显示风格 -->
<button onclick="changeStyle('row');">改变表格风格</button>


<script type="text/javascript">
<!--
var i=0;

function changeStyle(object){
  var styles=new Array("its","mars","simple","report","mark","isis");
  swapClass(eval(object),styles[i]);
  //eval(object).className=styles[i];
  if (i==5){
      i=0;
  }
  else{
     i++;
  }
     
}



//查看id对应的记录
function common_view(id){
location.href="<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&amp;method=detailOne";
}

//编辑id对应的记录
function common_edit(id){
location.href="<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&amp;method=preUpdateOne";
}

//删除id对应的记录
function common_delete(id){
if (confirmDelete('这条记录')){
   location.href="<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&amp;method=removeOne";
}
}


//增加一条新纪录
function common_add(){
location.href="<c:out value='${pageContext.request.requestURL}'/>?method=preSaveOne";
}


//删除本页选中的记录
function common_delete_checked(){
  if(validateForm()==false){
    return false;
  }
  
  //针对没有选中一个记录进行删除要报告错误信息。
  var element=document.all["ids"];
  var msg;
  //在没有人户记录的情况下不能进行删除动作。
  if(element==null){
    msg="没有要删除的记录!";
    alert(msg);
    return false;
  }
  
  var len = element.length;//当只有一个ids时， element.length=null
  
  
  //表示只有一个id=ids的时候。
  if (len==null){
      if(element.checked==false){
            msg="您没有选中一个要删除的记录!";
            alert(msg);
            return false;
      }
      
  }else{  //两条以上的记录
      //不允许 不选择记录进行删除
      var flag=false;
      if (len != 0) {
         for (i = 0; i < len; i++) {
                if(element[i].checked == true){
                  flag=true ;
                  break;
                }
            }
        if(flag==false){
            msg="您没有选中一个要删除的记录!";
            alert(msg);
            return false;
        }
      }
  }
  
  
  //alert("ccc");
  //进行上面的效验后提交数据
  document.forms[0].action="<c:out value='${pageContext.request.requestURL}'/>"+"?method=removeSomeByIds";
  document.forms[0].submit();
}

//删除指定条件的记录
function common_delete_condition(){
location.href="<c:out value='${pageContext.request.requestURL}'/>method=preRemoveSome";
}

//根据条件进行查询,先到输入条件网页，再进行查询。
function common_query_condition(){
location.href="<c:out value='${pageContext.request.requestURL}'/>?method=preQueryStandard";
}

function validateForm(){
 return true;
}


//-->
</script>

<!--  request 范围的 -->
<button onclick="common_add();">增加</button>
<button onclick="common_delete_condition();">删除指定条件记录</button>
<button onclick="common_delete_checked();">删除选中记录</button>
<button onclick="common_query_condition();">查询</button>



<form  method="post">
<display:table id="row"
               name="data" 
               pagesize="9"
               styleClass="list"
               export="true"
               requestURI="user.do?method=queryAll"
               length=""
               defaultsort="2"
               offset="1"  
               class="mars"
               decorator="org.displaytag.sample.OperatorWrapper"
               >

<!--页面上控制：    不输出到csv格式.  -->               
<display:setProperty name="export.csv" value="false"/>


<display:setProperty name="paging.banner.placement" value="top" /> 

     
<display:setProperty name="export.excel.include_header" value="false" /> 
     
     
     <display:column media="html" title="<input id='selectAll_CheckBox' type='checkbox' onclick=\"selectAll_CheckedBox('ids',this);\" /> ">
        <input id="ids"   name="ids" type="checkbox" value="<c:out value='${row.id}'/>"/>
     </display:column>
     
     <display:column title="序号" media="html" sort="true"><%=pageContext.getAttribute("row_rowNum")%> </display:column>
     <display:column title="序号" media="xxx" ><c:out value="${row_rowNum}"/> </display:column>
     
     <display:column property="id"
                     media="xxx"
                     title="ID" 
                     paramId="id" 
                     paramProperty="id"
                     href="user.do?method=preUpdateOne" 
                     > 
     </display:column>   
     
     <display:column property="firstName" title="第一姓名" nulls="true" sort="true"/>
     <display:column property="lastName"  title="第二姓名" sort="true"/>
     <display:column property="fullName"  title="全名" sort="true" media="xxx"/>
     <display:column title="全名"    media="all"  sort="true">
       <c:out value="${row.firstName}"/>
       <c:out value="${row.lastName}"/>
     </display:column>
     <display:column property="birthday"  title="生日"  sort="true"  />
     
     
     <!-- 我认为button风格比较第一种要好，因为更加灵活。再javascript中有
       <c:out value='${pageContext.request.requestURL}'/>的使用，
       但是第一种风格user.do写死了。
       -->
     <display:column title="操作类型" media="html" >
      <a href="#" onclick="common_view('<c:out value='${row.id}'/>');">查看</a>
      <a href="#" onclick="common_edit('<c:out value='${row.id}'/>');">编辑</a>
      <a href="#" onclick="common_delete('<c:out value='${row.id}'/>');">删除</a>
      <!--
      <a href="user.do?id=<c:out value='${row.id}'/>&amp;method=removeOne">删除</a>
      -->
     </display:column>
     
     <!-- 页面上不显示该列-->
     <display:column property="button"  title="操作类型" media="xxx" />     
     
     
     <display:column title="操作类型" media="xxx" >
      <button onclick="common_view('<c:out value='${row.id}'/>');">查看</button>
      <button onclick="common_edit('<c:out value='${row.id}'/>');">编辑</button>
      <button onclick="common_delete('<c:out value='${row.id}'/>');">删除</button> 
     </display:column>
     
     
     <!-- 没有任何内容的显示，静态文本-->
     <display:column title="备注">##</display:column>   
     
     
     
     <display:caption>用户列表</display:caption>
     <display:footer>
        <tr>
          <td colspan="2">表尾左边</td>
          <td>表尾右边</td>
        </tr>
     </display:footer>
     
</display:table>

</form >
<!--
举例说明:
request.setAttribute("user",user); //user是一个bean,里面有ID,UserName.

现在希望能够生成一个 user.do?method=saveOne&Name=yuanjs这样一个href.
那么:
href="user.do?method=saveOne";   -------指定url
paramId="Name"                                  -------指定往url中增加的参数名字
paramName="user"                              --------往参数中要赋值的那个bean名字
paramProperty="UserName"   -------往参数中要赋值的那个bean的属性名字
paramScope="request" ---------------往参数中要赋值的那个bean的范围
-->

</body>
</html>


<%@ include file="/common/footer.jsp"%>
