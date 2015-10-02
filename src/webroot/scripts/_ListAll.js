<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>
<script type="text/javascript">
<!--
//***********************************
//** 以下js专用于_ListAll.jsp 使用******
//***********************************

var i=0;
/* param : object 为displaytag中的id的值。display:table id="row" */
function changeStyle(object){
  var styles=new Array("its","mars","simple","report","mark","isis");
  
  
  var element=document.getElementById(object);
  
  //如果根据id找不到object时;
  if (document.all[object]==undefined){
     //alert("xx");
     return false;
  }
  if (element==undefined) {
     return false;
  }
  
  swapClass(element,styles[i]);
  
  //element.className=styles[i];
  if (i==5){
      i=0;
  }
  else{
     i++;
  }
     
}



//查看id对应的记录
/*param: id 对应记录的id值*/
function common_view(id){
   displayFrame();
   //alert("<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&method=detailOne");
   parent.data.location.href="<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&method=detailOne";
   
   //下面的代码表示 单独显示"一条记录详细信息的浏览"
   //location.href="<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&amp;method=detailOne";
}

//编辑id对应的记录
/*param: id 对应记录的id值*/
function common_edit(id){
   displayFrame();
   parent.data.location.href="<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&method=preUpdateOne";
}

//删除id对应的记录
/*param: id 对应记录的id值*/
function common_delete(id){
    if (confirmDelete('这条记录')){
       location.href="<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&method=removeOne";
    }
}


//增加一条新纪录
function common_add(){
  displayFrame();
  parent.data.location.href="<c:out value='${pageContext.request.requestURL}'/>?method=preSaveOne";
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
  
  //确认是否删除这些数据
  if (confirmDelete()){
      //进行上面的效验后提交数据
      document.forms[0].action="<c:out value='${pageContext.request.requestURL}'/>?method=removeSomeByIds";
      document.forms[0].submit();
  }
}

//删除指定条件的记录
function common_delete_condition(){
  displayFrame();
  parent.data.location.href="<c:out value='${pageContext.request.requestURL}'/>?method=preRemoveSome";
}

//根据条件进行查询,先到输入条件网页，再进行查询。
function common_query_condition(){
 displayFrame();
 parent.data.location.href="<c:out value='${pageContext.request.requestURL}'/>?method=preQueryStandard";
}

function validateForm(){
 return true;
}
//-->
</script>