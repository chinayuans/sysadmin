<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>

<style>
<!--
#slidemenubar, #slidemenubar2{
position:absolute;
border:1.5px solid black;
line-height:20px;
}
-->
</style>

<html>
<head>
<title>Slide 风格</title>
<META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body>
<script language="JavaScript1.2">

/***********************************************
* Fold-out external menu- ? Dynamic Drive (www.dynamicdrive.com)
* This notice must stay intact for use
* Visit http://www.dynamicdrive.com/ for full source code
***********************************************/
var slidemenu_url="menu.html" //specify path to menu file
var slidemenu_height='315px' //specify height of menu (in pixels throughout)
var slidemenu_width='160px' //specify width of menu
var slidemenu_reveal='12px' //specify amount that menu should protrude initially
var slidemenu_top='170px'   //specify vertical offset of menu on page


var ns4=document.layers?1:0
var ie4=document.all
var ns6=document.getElementById&&!document.all?1:0

if (ie4||ns6)
document.write('<iframe id="slidemenubar2" style="left:'+((parseInt(slidemenu_width)-parseInt(slidemenu_reveal))*-1)+'px; top:'+slidemenu_top+'; width:'+slidemenu_width+'; height:'+slidemenu_height+'" src="'+slidemenu_url+'"></iframe>')
else if (ns4){
document.write('<style>\n#slidemenubar{\nwidth:'+slidemenu_width+';}\n<\/style>\n')
document.write('<layer id="slidemenubar" left=0 top='+slidemenu_top+' width='+slidemenu_width+' height='+slidemenu_height+' onMouseover="pull()" onMouseout="draw()" src="'+slidemenu_url+'" visibility=hide></layer>')
}
function regenerate(){
window.location.reload()
}
function regenerate2(){
if (ns4){
document.slidemenubar.left=((parseInt(slidemenu_width)-parseInt(slidemenu_reveal))*-1)
document.slidemenubar.visibility="show"
setTimeout("window.onresize=regenerate",400)
}
}

window.onload=regenerate2

rightboundary=0
leftboundary=(parseInt(slidemenu_width)-parseInt(slidemenu_reveal))*-1

if (ie4||ns6){
document.write('</div>')
themenu=(ns6)? document.getElementById("slidemenubar2").style : document.all.slidemenubar2.style
}
else if (ns4){
document.write('</layer>')
themenu=document.layers.slidemenubar
}

function pull(){
if (window.drawit)
clearInterval(drawit)
pullit=setInterval("pullengine()",10)
}
function draw(){
clearInterval(pullit)
drawit=setInterval("drawengine()",10)
}
function pullengine(){
if ((ie4||ns6)&&parseInt(themenu.left)<rightboundary)
themenu.left=parseInt(themenu.left)+10+"px"
else if(ns4&&themenu.left<rightboundary)
themenu.left+=10
else if (window.pullit){
themenu.left=0
clearInterval(pullit)
}
}

function drawengine(){
if ((ie4||ns6)&&parseInt(themenu.left)>leftboundary)
themenu.left=parseInt(themenu.left)-10+"px"
else if(ns4&&themenu.left>leftboundary)
themenu.left-=10
else if (window.drawit){
themenu.left=leftboundary
clearInterval(drawit)
}
}

</script>
</body>
</html>
<%@ include file="/common/footer.jsp"%>