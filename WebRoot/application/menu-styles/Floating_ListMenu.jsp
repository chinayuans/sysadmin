<%@ include file="/common/header.jsp"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" pageEncoding="GB2312"%>


<!-- ListMenu菜单样式 -->
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/global.css'/>" />  
<link rel="stylesheet" type="text/css" media="screen, print" href="<c:url value='/css/tree/menuExpandable.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/tree/menuExpandable.js'/>"></script>


<html>
<head>
<title>浮动的ListMenu 风格菜单</title>
     <META http-equiv="Content-Type" content="text/html;charset=GB2312">
</head>
<body >
<script>
if (!document.layers)
document.write('<div id="divStayTopLeft" style="position:absolute">')
</script>

<layer id="divStayTopLeft">

        <!--EDIT BELOW CODE TO YOUR OWN MENU-->
        ListMenu:
        <menu:useMenuDisplayer name="ListMenu" 
            repository="repository">
            <c:forEach var="menu" items="${repository.topMenus}">
                <menu-el:displayMenu name="${menu.name}"/>
            </c:forEach>
        </menu:useMenuDisplayer>
        
        <!--END OF EDIT-->

</layer>


<script type="text/javascript">

/*
Floating Menu script-  Roy Whittle (http://www.javascript-fx.com/)
Script featured on/available at http://www.dynamicdrive.com/
This notice must stay intact for use
*/

//Enter "frombottom" or "fromtop"
var verticalpos="frombottom"

if (!document.layers)
document.write('</div>')

function JSFX_FloatTopDiv()
{
    var startX = 3,
    startY = document.body.clientHeight;
    var ns = (navigator.appName.indexOf("Netscape") != -1);
    var d = document;
    function ml(id)
    {
        var el=d.getElementById?d.getElementById(id):d.all?d.all[id]:d.layers[id];
        if(d.layers)el.style=el;
        el.sP=function(x,y){this.style.left=x;this.style.top=y;};
        el.x = startX;
        if (verticalpos=="fromtop")
        el.y = startY;
        else{
        el.y = ns ? pageYOffset + innerHeight : document.body.scrollTop + document.body.clientHeight;
        el.y -= startY;
        }
        return el;
    }
    window.stayTopLeft=function()
    {
        if (verticalpos=="fromtop"){
        var pY = ns ? pageYOffset : document.body.scrollTop;
        ftlObj.y += (pY + startY - ftlObj.y)/8;
        }
        else{
        var pY = ns ? pageYOffset + innerHeight : document.body.scrollTop + document.body.clientHeight;
        ftlObj.y += (pY - startY - ftlObj.y)/8;
        }
        ftlObj.sP(ftlObj.x, ftlObj.y);
        setTimeout("stayTopLeft()", 10);
    }
    ftlObj = ml("divStayTopLeft");
    stayTopLeft();
}
JSFX_FloatTopDiv();
</script>



</body>
</html>
<%@ include file="/common/footer.jsp"%>