

<!--��ǰ���ڵ����⣺
1.�򿪡�+��ʱ�������Ա�ɡ�-�� 2.folderͼ�겻���Ա��openfolderͼ
-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="org.appfuse.web.tree.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Hashtable"%>
<%!
static Hashtable images=new Hashtable();
static Hashtable actions=new Hashtable();
static String script;
static {
    
    //images.put("IMAGE_PLUS", "images_1/plus.gif"); 		   //1"+"ͼ�����
    //images.put("IMAGE_PLUS_LAST", "images_1/pluslast.gif");  //2���һ����+��ͼ�����
    //images.put("IMAGE_MINUS", "images_1/minus.gif");         //3"-"ͼ�����
    //images.put("IMAGE_MINUS_LAST", "images_1/minuslast.gif");//4���һ����-��ͼ�����
    //images.put("IMAGE_MIDBLK", "images_1/midblk.gif");       //û������
    //images.put("IMAGE_BLANK", "images_1/blank.gif");         //5��+�л���+���ֵ�ʱ������м�հ�
    //images.put("IMAGE_LASTBLK", "images_1/lastblk.gif");     //û������
    //images.put("IMAGE_LINE", "images_1/line.gif");           //6����ͼ��
    //images.put("IMAGE_FOLDER", "images_1/folder.gif");       //7�ļ���ͼ��
    //images.put("IMAGES_FOLDER_OPEN","images_1/folderopen.gif");//8�ļ��б���ͼ��
    
    images.put("IMAGE_PLUS", "images/Tplus.gif");           //1"+"ͼ�����
    images.put("IMAGE_PLUS_LAST", "images/Lplus.gif");  //2���һ����+��ͼ�����
    images.put("IMAGE_MINUS", "images/Tminus.gif");         //3"-"ͼ�����
    images.put("IMAGE_MINUS_LAST", "images/Lminus.gif");//4���һ����-��ͼ�����
    images.put("IMAGE_MIDBLK", "images/midblk.gif");       //û������
    images.put("IMAGE_BLANK", "images/blank.gif");         //5��+�л���+���ֵ�ʱ������м�հ�
    images.put("IMAGE_LASTBLK", "images/lastblk.gif");     //û������
    images.put("IMAGE_LINE", "images/I.gif");           //6����ͼ��
    images.put("IMAGE_FOLDER", "images/folder.gif");       //7�ļ���ͼ��
    images.put("IMAGES_FOLDER_OPEN","images/folderopen.gif");//8�ļ��б���ͼ��
    
    StringBuffer sc=new StringBuffer("<script type=\"text/javascript\">\r\n");
    Iterator imgs=images.values().iterator();
    int k=0;
    while(imgs.hasNext()){
      sc.append("var image"+k+"=new Image();\r\n");
      sc.append("image"+k+".src=\""+(String)imgs.next()+"\";\r\n");
      k++;
    }
    sc.append("</script>\r\n");
    script=sc.toString();
    actions.put("CLICK_FOLDER","clickFolder");
    actions.put("CLICK_DOCUMENT","clickDocument");
    actions.put("CLICK_FOLDER_IMG","openFolder");
    actions.put("MOUSEOVER","overMouse");
    actions.put("MOUSEOUT","outMouse");
    
}

  void paintChilds(Iterator childs,Writer w) throws IOException{
    while(childs.hasNext()){
      paintNode((Node)childs.next(),w);
    }
  }

  void paintNode(Node n,Writer w) throws IOException{
    w.write("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr>");
    int level=n.getLevel();
    int id=n.getId();
    Node parent=null;
    String name=((File)n.getValue()).getName();
    boolean last=false;
    for(int i=level-1;i>0;i--){
      parent=n.getParent(i);
      last=parent.isLast();
      w.write("<td><img src=\"" +
              (String) images.get(last ? "IMAGE_BLANK":"IMAGE_LINE" ) +
              "\" border=\"0\"></td>");
    }
    last=n.isLast();
    if(n.hasChilds()){
      w.write("<td id=\"plus" + id + "\" style=\"cursor:hand\" onClick=\""+   
((String)actions.get("CLICK_FOLDER_IMG")+"(document,"+id+")")+"\"><img src=\"" +
                (String) images.get(last ? "IMAGE_PLUS_LAST" : "IMAGE_PLUS") +
                "\" border=\"0\"></td>");
    }
    else {
      w.write("<td id=\"plus" + id + "\"><img src=\"" +
              (String) images.get(last ? "IMAGE_MINUS_LAST" : "IMAGE_MINUS") +
              "\" border=\"0\"></td>");
    }
    w.write("<td id=\"f" + id + "\"><img src=\"" +
            (String) images.get("IMAGE_FOLDER") + "\" border=\"0\"></td>");
    if(n.hasChilds()){
      w.write("<td id=\"td" + id + "\" style=\"cursor:hand\" onClick=\""+
(String)actions.get("CLICK_FOLDER")+"(document,"+id+"),"+
(String)actions.get("CLICK_FOLDER_IMG")+"(document,"+id+")"+"\">" +
name + "</td>");
    }
    else{
      w.write("<td id=\"td" + id + "\" style=\"cursor:hand\" onClick=\""+
(String)actions.get("CLICK_FOLDER")+"(document,"+id+")"+"\">" +
  name + "</td>");
    }
    w.write("</tr></table>");
    if (n.hasChilds()) {
      w.write("<div id=\"div" + id + "\" style=\"display:none\">");
      paintChilds(n.getChilds(), w);
      w.write("</div>");
    }
    w.flush();
  }


%>
<html>
<head>
<title>
tree
</title>
<style type="text/css">
td{font:13px/16px;}
A:link {text-decoration: none;font-size: 12px; color: #0000ff}
A:visited {text-decoration: none;font-size: 12px; color: #0000ff}
A:active {text-decoration: none;font-size: 12px}
A:hover {text-decoration: underline;font-size: 12px}
img{vertical-align: bottom;}
</style>
<%=script%>
<script type="text/javascript">
function changeColor(doc,k){
  var old=doc.thisForm.selectedNode.value;
  if(old!=k){
    if(old!=""){
      doc.all("td"+old).style.backgroundColor=doc.thisForm.bgColor.value;
    }
    doc.all("td"+k).style.backgroundColor=doc.thisForm.selectedColor.value;
    doc.thisForm.selectedNode.value=k;
  }
}

function clickDocument(doc,k){
  changeColor(doc,k);
  alert("Click document "+doc.all("td"+k).innerText+".");//���ĵ��Ĵ���
}
function openFolder(doc,k){
  var o=doc.all("div"+k);
  if(o.style.display=="none"){
    o.style.display="block";
  }
  else{
    o.style.display="none";
  }
  replaceImg(doc,k);
  var k=0;
  while((o=o.parentElement)!=doc&&o!=null){
    k=o.id.indexOf("div");
    if(k!=-1){
      if(o.style.display=="none"){
        o.style.display="block";
      replaceImg(doc,o.id.substring(k));
      }
    }
  }
}
function clickFolder(doc,k){
  changeColor(doc,k);
  alert("Click folder "+doc.all("td"+k).innerText+".");
}
//��һ��javascriptǷȱ�����ڸı�folder��+
function replaceImg(doc,k){
    document.all("plus"+k).img=image5;
    document.all("f"+k).img=image7;
}

//document.
</script>
</head>

<body>
<%
out.flush();
File f=new File(this.getServletConfig().getServletContext().getRealPath("")).getParentFile();
Tree t=Tree.getTree(f,new FileContainer());//��һ������Ŀ¼ת��ΪĿ¼������Ŀ¼���е�ÿ����㽫��Ӧ�����е�һ��Ŀ¼���ļ���
 
 //Tree t=Tree.getTree(Group.getGroups(),0);
paintChilds(t.getChilds(),response.getWriter());
%>
<form name="thisForm">
  <input type="hidden" name="selectedNode">
  <input type="hidden" name="bgColor" value="#FFFFFF">
  <input type="hidden" name="selectedColor" value="#9999FF">
</form>
</body>
</html>
