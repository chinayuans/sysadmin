<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>
<script type="text/javascript">
<!--
//***********************************
//** ����jsר����_ListAll.jsp ʹ��******
//***********************************

var i=0;
/* param : object Ϊdisplaytag�е�id��ֵ��display:table id="row" */
function changeStyle(object){
  var styles=new Array("its","mars","simple","report","mark","isis");
  
  
  var element=document.getElementById(object);
  
  //�������id�Ҳ���objectʱ;
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



//�鿴id��Ӧ�ļ�¼
/*param: id ��Ӧ��¼��idֵ*/
function common_view(id){
   displayFrame();
   //alert("<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&method=detailOne");
   parent.data.location.href="<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&method=detailOne";
   
   //����Ĵ����ʾ ������ʾ"һ����¼��ϸ��Ϣ�����"
   //location.href="<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&amp;method=detailOne";
}

//�༭id��Ӧ�ļ�¼
/*param: id ��Ӧ��¼��idֵ*/
function common_edit(id){
   displayFrame();
   parent.data.location.href="<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&method=preUpdateOne";
}

//ɾ��id��Ӧ�ļ�¼
/*param: id ��Ӧ��¼��idֵ*/
function common_delete(id){
    if (confirmDelete('������¼')){
       location.href="<c:out value='${pageContext.request.requestURL}'/>?id="+id+"&method=removeOne";
    }
}


//����һ���¼�¼
function common_add(){
  displayFrame();
  parent.data.location.href="<c:out value='${pageContext.request.requestURL}'/>?method=preSaveOne";
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
  
  //ȷ���Ƿ�ɾ����Щ����
  if (confirmDelete()){
      //���������Ч����ύ����
      document.forms[0].action="<c:out value='${pageContext.request.requestURL}'/>?method=removeSomeByIds";
      document.forms[0].submit();
  }
}

//ɾ��ָ�������ļ�¼
function common_delete_condition(){
  displayFrame();
  parent.data.location.href="<c:out value='${pageContext.request.requestURL}'/>?method=preRemoveSome";
}

//�����������в�ѯ,�ȵ�����������ҳ���ٽ��в�ѯ��
function common_query_condition(){
 displayFrame();
 parent.data.location.href="<c:out value='${pageContext.request.requestURL}'/>?method=preQueryStandard";
}

function validateForm(){
 return true;
}
//-->
</script>