<%@ include file="/common/header.jsp"%>
<!--�����������-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<html>
<head>
<title>�б���ʾ</title>
<META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body>



<%@ include file="/common/banner.jsp"%>


<!--c:out value='${pageContext.request.requestURL}'/-->

<!-- ������δ���������ʾdisplaytag��ͬ����ʾ��� -->
<button onclick="changeStyle('row');">�ı�����</button>


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



//�鿴id��Ӧ�ļ�¼
function common_view(id){
location.href="<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&amp;method=detailOne";
}

//�༭id��Ӧ�ļ�¼
function common_edit(id){
location.href="<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&amp;method=preUpdateOne";
}

//ɾ��id��Ӧ�ļ�¼
function common_delete(id){
if (confirmDelete('������¼')){
   location.href="<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&amp;method=removeOne";
}
}


//����һ���¼�¼
function common_add(){
location.href="<c:out value='${pageContext.request.requestURL}'/>?method=preSaveOne";
}


//ɾ����ҳѡ�еļ�¼
function common_delete_checked(){
  if(validateForm()==false){
    return false;
  }
  
  //���û��ѡ��һ����¼����ɾ��Ҫ���������Ϣ��
  var element=document.all["ids"];
  var msg;
  //��û���˻���¼������²��ܽ���ɾ��������
  if(element==null){
    msg="û��Ҫɾ���ļ�¼!";
    alert(msg);
    return false;
  }
  
  var len = element.length;//��ֻ��һ��idsʱ�� element.length=null
  
  
  //��ʾֻ��һ��id=ids��ʱ��
  if (len==null){
      if(element.checked==false){
            msg="��û��ѡ��һ��Ҫɾ���ļ�¼!";
            alert(msg);
            return false;
      }
      
  }else{  //�������ϵļ�¼
      //������ ��ѡ���¼����ɾ��
      var flag=false;
      if (len != 0) {
         for (i = 0; i < len; i++) {
                if(element[i].checked == true){
                  flag=true ;
                  break;
                }
            }
        if(flag==false){
            msg="��û��ѡ��һ��Ҫɾ���ļ�¼!";
            alert(msg);
            return false;
        }
      }
  }
  
  
  //alert("ccc");
  //���������Ч����ύ����
  document.forms[0].action="<c:out value='${pageContext.request.requestURL}'/>"+"?method=removeSomeByIds";
  document.forms[0].submit();
}

//ɾ��ָ�������ļ�¼
function common_delete_condition(){
location.href="<c:out value='${pageContext.request.requestURL}'/>method=preRemoveSome";
}

//�����������в�ѯ,�ȵ�����������ҳ���ٽ��в�ѯ��
function common_query_condition(){
location.href="<c:out value='${pageContext.request.requestURL}'/>?method=preQueryStandard";
}

function validateForm(){
 return true;
}


//-->
</script>

<!--  request ��Χ�� -->
<button onclick="common_add();">����</button>
<button onclick="common_delete_condition();">ɾ��ָ��������¼</button>
<button onclick="common_delete_checked();">ɾ��ѡ�м�¼</button>
<button onclick="common_query_condition();">��ѯ</button>



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

<!--ҳ���Ͽ��ƣ�    �������csv��ʽ.  -->               
<display:setProperty name="export.csv" value="false"/>


<display:setProperty name="paging.banner.placement" value="top" /> 

     
<display:setProperty name="export.excel.include_header" value="false" /> 
     
     
     <display:column media="html" title="<input id='selectAll_CheckBox' type='checkbox' onclick=\"selectAll_CheckedBox('ids',this);\" /> ">
        <input id="ids"   name="ids" type="checkbox" value="<c:out value='${row.id}'/>"/>
     </display:column>
     
     <display:column title="���" media="html" sort="true"><%=pageContext.getAttribute("row_rowNum")%> </display:column>
     <display:column title="���" media="xxx" ><c:out value="${row_rowNum}"/> </display:column>
     
     <display:column property="id"
                     media="xxx"
                     title="ID" 
                     paramId="id" 
                     paramProperty="id"
                     href="user.do?method=preUpdateOne" 
                     > 
     </display:column>   
     
     <display:column property="firstName" title="��һ����" nulls="true" sort="true"/>
     <display:column property="lastName"  title="�ڶ�����" sort="true"/>
     <display:column property="fullName"  title="ȫ��" sort="true" media="xxx"/>
     <display:column title="ȫ��"    media="all"  sort="true">
       <c:out value="${row.firstName}"/>
       <c:out value="${row.lastName}"/>
     </display:column>
     <display:column property="birthday"  title="����"  sort="true"  />
     
     
     <!-- ����Ϊbutton���Ƚϵ�һ��Ҫ�ã���Ϊ��������javascript����
       <c:out value='${pageContext.request.requestURL}'/>��ʹ�ã�
       ���ǵ�һ�ַ��user.doд���ˡ�
       -->
     <display:column title="��������" media="html" >
      <a href="#" onclick="common_view('<c:out value='${row.id}'/>');">�鿴</a>
      <a href="#" onclick="common_edit('<c:out value='${row.id}'/>');">�༭</a>
      <a href="#" onclick="common_delete('<c:out value='${row.id}'/>');">ɾ��</a>
      <!--
      <a href="user.do?id=<c:out value='${row.id}'/>&amp;method=removeOne">ɾ��</a>
      -->
     </display:column>
     
     <!-- ҳ���ϲ���ʾ����-->
     <display:column property="button"  title="��������" media="xxx" />     
     
     
     <display:column title="��������" media="xxx" >
      <button onclick="common_view('<c:out value='${row.id}'/>');">�鿴</button>
      <button onclick="common_edit('<c:out value='${row.id}'/>');">�༭</button>
      <button onclick="common_delete('<c:out value='${row.id}'/>');">ɾ��</button> 
     </display:column>
     
     
     <!-- û���κ����ݵ���ʾ����̬�ı�-->
     <display:column title="��ע">##</display:column>   
     
     
     
     <display:caption>�û��б�</display:caption>
     <display:footer>
        <tr>
          <td colspan="2">��β���</td>
          <td>��β�ұ�</td>
        </tr>
     </display:footer>
     
</display:table>

</form >
<!--
����˵��:
request.setAttribute("user",user); //user��һ��bean,������ID,UserName.

����ϣ���ܹ�����һ�� user.do?method=saveOne&Name=yuanjs����һ��href.
��ô:
href="user.do?method=saveOne";   -------ָ��url
paramId="Name"                                  -------ָ����url�����ӵĲ�������
paramName="user"                              --------��������Ҫ��ֵ���Ǹ�bean����
paramProperty="UserName"   -------��������Ҫ��ֵ���Ǹ�bean����������
paramScope="request" ---------------��������Ҫ��ֵ���Ǹ�bean�ķ�Χ
-->

</body>
</html>


<%@ include file="/common/footer.jsp"%>
