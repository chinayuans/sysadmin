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
win = "on";
function switchSysBar() {
    if (document.getElementById) {
        var target1 = document.getElementById("leftfrm");     
        var target2 = document.getElementById("switchPoint");      
    }
    if (win == "on") {
        win = "off";
        target2.src = "images/arrow02.gif";
        target1.style.display = "none";
    } else {
        win = "on";
        target2.src = "images/arrow01.gif";
        target1.style.display = "";
    }
}
//-->
</script>

<BODY >
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
    <TBODY>
        <TR>
            <TD id=leftfrm valign=top>
                <IFRAME
                style="Z-INDEX: 2; VISIBILITY: inherit; WIDTH: 220px; HEIGHT: 100%"
                name=left src=<c:out value="${pageContext.request.contextPath}/menuItem.do?method=getMainMenu"/> frameBorder=0>
                </IFRAME>
            </TD>
            <TD bgColor=#c0c0c0 >
                <TABLE height="100%" cellSpacing=0 cellPadding=0 border=0>
                    <TBODY>
                        <TR>
                            <TD width=1 style="CURSOR: hand" onclick=switchSysBar()>
                               <IMG id=switchPoint src="images/arrow01.gif">
                            </TD>
                            <TD bgColor=#090c11 width=1></TD>
                        </TR>
                    </TBODY>
                </TABLE>
            </TD>
<!-- ��ҳ�����ʾ ע�� iframe �е�name="main"  menuitem�е�target�������main�ſ�������ҳ������ʾ������-->
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
</BODY>
</HTML>

<%--@ include file="/common/footer.jsp"--%>