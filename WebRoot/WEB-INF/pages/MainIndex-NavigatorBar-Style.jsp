<%@ include file="/common/common_taglibs.jsp"%>
<!--�����������-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<html>
<head>
<title>��ӭ��</title>

</head>
<script type="text/javascript">
<!--
function moveAlong(layerName, paceLeft, paceTop, fromLeft, fromTop){
    clearTimeout(eval(layerName).timer)
    if(eval(layerName).curLeft != fromLeft){
        if((Math.max(eval(layerName).curLeft, fromLeft) - Math.min(eval(layerName).curLeft, fromLeft)) < paceLeft){eval(layerName).curLeft = fromLeft}
        else if(eval(layerName).curLeft < fromLeft){eval(layerName).curLeft = eval(layerName).curLeft + paceLeft}
            else if(eval(layerName).curLeft > fromLeft){eval(layerName).curLeft = eval(layerName).curLeft - paceLeft}
        if(ie){document.all[layerName].style.left = eval(layerName).curLeft}
        if(ns){document[layerName].left = eval(layerName).curLeft}
    }
    if(eval(layerName).curTop != fromTop){
   if((Math.max(eval(layerName).curTop, fromTop) - Math.min(eval(layerName).curTop, fromTop)) < paceTop){eval(layerName).curTop = fromTop}
        else if(eval(layerName).curTop < fromTop){eval(layerName).curTop = eval(layerName).curTop + paceTop}
            else if(eval(layerName).curTop > fromTop){eval(layerName).curTop = eval(layerName).curTop - paceTop}
        if(ie){document.all[layerName].style.top = eval(layerName).curTop}
        if(ns){document[layerName].top = eval(layerName).curTop}
    }
    eval(layerName).timer=setTimeout('moveAlong("'+layerName+'",'+paceLeft+','+paceTop+','+fromLeft+','+fromTop+')',30)
}

function setPace(layerName, fromLeft, fromTop, motionSpeed){
    eval(layerName).gapLeft = (Math.max(eval(layerName).curLeft, fromLeft) - Math.min(eval(layerName).curLeft, fromLeft))/motionSpeed
    eval(layerName).gapTop = (Math.max(eval(layerName).curTop, fromTop) - Math.min(eval(layerName).curTop, fromTop))/motionSpeed
    moveAlong(layerName, eval(layerName).gapLeft, eval(layerName).gapTop, fromLeft, fromTop)
}
function FixY(){
    if(ie){sidemenu.style.top = document.body.scrollTop+10}
    if(ns){sidemenu.top = window.pageYOffset+10}
}


//���ε�����JS����
var expandState = 0;
function expand(){
  if(expandState == 0){setPace('master', 0, 10, 10); if(ie){document.menutop.src = 'images/menui.gif'}; expandState = 1;}
  else{setPace('master', -200, 10, 10); if(ie){document.menutop.src='images/menuo.gif'}; expandState = 0;}
}
document.write("<style type=text/css>#master {LEFT: -200px; POSITION: absolute; TOP: 25px; VISIBILITY: visible; Z-INDEX: 999}</style>")

//width='218' ��ʾ͸����Ŀ�ȡ�
document.write("<table id=master width='218' border='0' cellspacing='0' cellpadding='0'><tr><td ><img border=0 height=6 src='images/menutop.gif'  width=200></td><td  rowspan='2' valign='top'><img id=menu onMouseOver=javascript:expand() border=0 height=70 name=menutop src='images/menuo.gif' width=18></td></tr>");
document.write("<tr><td valign='top'><table width='100%' border='0' cellspacing='5' cellpadding='0'><tr><td width='240' height='400' valign='top'><table width='100%' height='100%' border=1 cellpadding=0 cellspacing=5 bordercolor='#666666' bgcolor=#ecf6f5 style=FILTER: alpha(opacity=90)><tr>");
document.write("<td height='10' align='center' bordercolor='#ecf6f5'><font color=999900><strong>�� Ŀ �� �� �� ��</strong></font></td></tr><tr><td valign='top' bordercolor='#ecf6f5'>");

//�ص�ط���src  ָ��Ҫ�����Ǹ�ҳ�档
var src="<%=request.getContextPath()%>/"+"menuItem.do?method=getMainMenu";
document.write("<iframe width='100%' height='100%' src='"+src+"' frameborder=0></iframe></td></tr></table></td></tr></table></td></tr></table>");

var ie = document.all ? 1 : 0
var ns = document.layers ? 1 : 0
var master = new Object('element')
master.curLeft = -200;   master.curTop = 10;
master.gapLeft = 0;      master.gapTop = 0;
master.timer = null;
if(ie){var sidemenu = document.all.master;}
if(ns){var sidemenu = document.master;}
setInterval('FixY()',100);//setIntervalΪwindow����ķ�����


//-->
</script>

<!-- ��ҳ�����ʾ ע�� iframe �е�name="main"  menuitem�е�target�������main�ſ�������ҳ������ʾ������-->
<body>
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
   <TBODY>
        <TR>
            <TD style="WIDTH: 100%">
               <IFRAME
                style="Z-INDEX: 1; VISIBILITY: inherit; WIDTH: 100%; HEIGHT: 100%"
                name="main"
                src=<c:out value="${pageContext.request.contextPath}/index.jsp"/>
                frameBorder=0 scrolling=yes>
                </IFRAME>
            </TD>
        </TR>
    </TBODY>
</TABLE>

</body>
</html>
<%--@ include file="/common/footer.jsp"--%>