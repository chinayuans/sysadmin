//============================ʹ�÷���ʾ��============
//���´���<input>�ȵȷ����ھ����������ڸ�ʽ�ı��С�
//<INPUT type="text" name="item_birth_date_1" size="20" maxlength="10" value=""  onFocusOld="setday(this,document.getElementsByName('item_birth_date_1')[0],'yyyymmdd');" onBlur="javascript:document_onclick(event);" ReadOnlyT/>
//&nbsp;
//<a HREF="javascript:void(0);">
//<img width="18" height="18" BORDER="0" ALIGN="ABSMIDDLE" src="images/calendar.gif" onClick="setday(this,document.getElementsByName('item_birth_date_1')[0],'yyyymmdd');">
//</a>
//&nbsp;
//<SPAN CLASS="comment"> Date Format: YYYY-MM-DD 
//====================================================


//==================================================== �����趨���� ======================================================= 
var bMoveable=true;                //���������Ƿ�����϶� 
var _VersionInfo="";       //�汾��Ϣ 
var outType="";           //���������ʽ
//==================================================== WEB ҳ����ʾ���� ===================================================== 
               //����������HTML���� 
document.writeln('<iframe name=meizzDateLayer Author=wayx frameborder=0 style="position:absolute; width:144px; height:213px; z-index:9998; visibility:hidden"></iframe>'); 

var odatelayer; 
var outObject; 
var outButton;                //����İ�ť 
var outDate="";                //��Ŷ�������� 


function initStr(pColor){
var strFrame; 
strFrame='<style>'; 
strFrame+='INPUT.button{BORDER-RIGHT: '+pColor+' 1px solid;BORDER-TOP:'+pColor+' 1px solid;BORDER-LEFT: '+pColor+'1px solid;'; 
strFrame+='BORDER-BOTTOM: '+pColor+' 1px solid;BACKGROUND-COLOR: #fff8ec;font-family:����;}'; 
strFrame+='TD{FONT-SIZE: 9pt;font-family:����;}'; 
strFrame+='A{text-decoration:none;font-weight:bold;color:black}'; 
strFrame+='</style>'; 
strFrame+='<scr' + 'ipt>'; 
strFrame+='var datelayerx,datelayery;        /*��������ؼ������λ��*/'; 
strFrame+='var bDrag;        /*����Ƿ�ʼ�϶�*/'; 
strFrame+='function document_onmousemove(e){/*������ƶ��¼��У������ʼ�϶����������ƶ�����*/'; 
strFrame+='if(bDrag){'; 
strFrame+='        var DateLayer=parent.document.getElementsByName("meizzDateLayer")[0].style;'; 
strFrame+='                DateLayer.left = parseInt(DateLayer.left)+ e.clientX-datelayerx; /*����ÿ���ƶ��Ժ����λ�ö��ָ�Ϊ��ʼ��λ�ã����д����div�в�ͬ*/'; 
strFrame+='if(DateLayer.top=="")DateLayer.top=0;'; 
strFrame+='                DateLayer.top = parseInt(DateLayer.top)+ e.clientY-datelayery;}}'; 
strFrame+='function DragStart(e){                /*��ʼ�����϶�*/'; 
strFrame+="if ((document.all?e.button:e.which)!=1) return;\n"; 
strFrame+='var DateLayer=parent.document.getElementsByName("meizzDateLayer")[0].style;'; 
strFrame+='        datelayerx=e.clientX;'; 
strFrame+='        datelayery=e.clientY;'; 
strFrame+='        bDrag=true;}'; 
strFrame+='function DragEnd(){                /*���������϶�*/'; 
strFrame+='        bDrag=false;}'; 
strFrame+='</scr' + 'ipt>'; 
strFrame+='<body onmousemove="document_onmousemove(event)">'; 
strFrame+='<div style="z-index:9999;position: absolute; left:0; top:0;" onselectstart="return false"><span id=tmpSelectYearLayer Author=wayx style="z-index: 9999;position: absolute;top: 3; left: 19;visibility: hidden"></span>'; 
strFrame+='<span id=tmpSelectMonthLayer Author=wayx style="z-index: 9999;position: absolute;top: 3; left: 78;visibility: hidden"></span>'; 
strFrame+='<table border=1 cellspacing=0 cellpadding=0 width=142 height=160 bordercolor='+pColor+' bgcolor='+pColor+' Author="wayx">'; 
strFrame+='  <tr Author="wayx"><td width=142 height=23 Author="wayx" bgcolor=#FFFFFF><table border=0 cellspacing=1 cellpadding=0 width=140 Author="wayx" height=23>'; 
strFrame+='  <tr align=center Author="wayx"><td width=16 align=center bgcolor='+pColor ; 
strFrame+=' onclick="parent.meizzPrevM()" title="��ǰ�� 1 ��" Author=meizz><a href="javascript:;" style="font-size:12px;color: #ffffff" Author=meizz><</a>'; 
strFrame+='        </td><td width=60 align=center style="font-size:12px;cursor:default" Author=meizz '; 
strFrame+='onmouseover="style.backgroundColor=\'#FFD700\'" onmouseout="style.backgroundColor=\'white\'" '; 
strFrame+='onclick="parent.tmpSelectYearInnerHTML(innerHTML.match(/\\d{4}/).toString());" title="�������ѡ�����"><span Author=meizz id=meizzYearHead></span></td>'; 
strFrame+='<td width=48 align=center style="font-size:12px;cursor:default" Author=meizz onmouseover="style.backgroundColor=\'#FFD700\'" '; 
strFrame+=' onmouseout="style.backgroundColor=\'white\'" onclick="parent.tmpSelectMonthInnerHTML(innerHTML.match(/\\d\\d?/).toString())"'; 
strFrame+='        title="�������ѡ���·�"><span id=meizzMonthHead Author=meizz></span></td>'; 
strFrame+='        <td width=16 bgcolor='+pColor+' align=center style="font-size:12px;cursor: hand;color: #ffffff" '; 
strFrame+=' onclick="parent.meizzNextM()" title="��� 1 ��" Author=meizz><a href="javascript:;" style="font-size:12px;color: #ffffff;font:bold" Author=meizz>></a></td></tr>'; 
strFrame+='    </table></td></tr>'; 
strFrame+='  <tr Author="wayx"><td width=142 height=18 Author="wayx">'; 
strFrame+='<table border=1 cellspacing=0 bgcolor='+pColor+' cellpadding=0  ' + (bMoveable? 'onmousedown="DragStart(event)" onmouseup="DragEnd()"':''); 
strFrame+=' BORDERCOLORLIGHT='+pColor+' BORDERCOLORDARK=#FFFFFF width=140 height=20 Author="wayx" style="cursor:' + (bMoveable ? 'move':'default') + '">'; 
strFrame+='<tr Author="wayx" align=center valign=bottom><td style="font-size:12px;color:#FFFFFF" Author=meizz>��</td>'; 
strFrame+='<td style="font-size:12px;color:#FFFFFF" Author=meizz>һ</td><td style="font-size:12px;color:#FFFFFF" Author=meizz>��</td>'; 
strFrame+='<td style="font-size:12px;color:#FFFFFF" Author=meizz>��</td><td style="font-size:12px;color:#FFFFFF" Author=meizz>��</td>'; 
strFrame+='<td style="font-size:12px;color:#FFFFFF" Author=meizz>��</td><td style="font-size:12px;color:#FFFFFF" Author=meizz>��</td></tr>'; 
strFrame+='</table></td></tr>'; 
strFrame+='  <tr Author="wayx"><td width=142 height=120 Author="wayx">'; 
strFrame+='    <table border=1 cellspacing=2 cellpadding=0 BORDERCOLORLIGHT='+pColor+' BORDERCOLORDARK=#FFFFFF bgcolor=#fff8ec width=140 height=120 Author="wayx">'; 
var n=0; for (j=0;j<5;j++){ strFrame+= ' <tr align=center Author="wayx">'; for (i=0;i<7;i++){ 
strFrame+='<td width=20 height=20 id=meizzDay'+n+' style="font-size:12px" Author=meizz onclick=parent.meizzDayClick(this.innerHTML.match(/\\d+/).toString(),0)></td>';n++;} 
strFrame+='</tr>';} 
strFrame+='      <tr align=center Author="wayx">'; 
for (i=35;i<39;i++)strFrame+='<td width=20 height=20 id=meizzDay'+i+' style="font-size:12px" Author=wayx onclick="parent.meizzDayClick(this.innerHTML.match(/\\d+/).toString(),0)"></td>'; 
strFrame+='        <td colspan=3 align=right Author=meizz><a href="javascript:;" onclick=parent.closeLayer() style="font-size:12px;text-decoration:underline;font-weight:300;"'; 
strFrame+='         Author=meizz title="' + _VersionInfo + '">�ر�</a> </td></tr>'; 
strFrame+='    </table></td></tr><tr Author="wayx"><td Author="wayx">'; 
strFrame+='        <table border=0 cellspacing=1 cellpadding=0 width=100% Author="wayx" bgcolor=#FFFFFF>'; 
strFrame+='          <tr Author="wayx"><td Author=meizz align=left><input Author=meizz type=button class=button value="<<" title="��ǰ�� 1 ��" onclick="parent.meizzPrevY()" '; 
strFrame+='             onfocus="this.blur()" style="font-size: 12px; height: 20px" id=button2 name=button2><input Author=meizz class=button title="��ǰ�� 1 ��" type=button '; 
strFrame+='             value="< " onclick="parent.meizzPrevM()" onfocus="this.blur()" style="font-size: 12px; height: 20px" id=button3 name=button3></td><td '; 
strFrame+='             Author=meizz align=center><input Author=meizz type=button class=button value=���� onclick="parent.meizzToday()" '; 
strFrame+='             onfocus="this.blur()" title="��ǰ����" style="font-size: 12px; height: 20px; cursor:hand" id=button4 name=button4></td><td '; 
strFrame+='             Author=meizz align=right><input Author=meizz type=button class=button value=" >" onclick="parent.meizzNextM()" '; 
strFrame+='             onfocus="this.blur()" title="��� 1 ��" class=button style="font-size: 12px; height: 20px" id=button5 name=button5><input '; 
strFrame+='             Author=meizz type=button class=button value=">>" title="��� 1 ��" onclick="parent.meizzNextY()"'; 
strFrame+='             onfocus="this.blur()" style="font-size: 12px; height: 20px" id=button6 name=button6></td>'; 
strFrame+='</tr></table></td></tr></table></div></body>';
return strFrame;
}


function init(pColor){ 
        window.frames.meizzDateLayer.document.writeln(initStr(pColor)); 
        window.frames.meizzDateLayer.document.close();                //���ie������������������ 
        odatelayer=window.frames.meizzDateLayer.document;                //����������� 
} 
//==================================================== WEB ҳ����ʾ���� ====================================================== 

function setday(tt,obj,pOutType) //�������� 
{       
        if (arguments.length >  3){alert("�Բ��𣡴��뱾�ؼ��Ĳ���̫�࣡");return;} 
        if (arguments.length == 0){alert("�Բ�����û�д��ر��ؼ��κβ�����");return;} 
        outType = pOutType;
        var dads  = document.getElementsByName("meizzDateLayer")[0].style; 
	if (outObject!=obj || dads.visibility=='hidden'){
        var th = tt; 
        var ttop  = tt.offsetTop;     //TT�ؼ��Ķ�λ��� 
        var thei  = tt.offsetHeight;  //TT�ؼ�����ĸ� 
        var tleft = tt.offsetLeft;    //TT�ؼ��Ķ�λ��� 
        var ttyp  = tt.type;          //TT�ؼ������� 
        while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;} 
        dads.top  = ttop+thei ; 
        dads.left = tleft; 
        outObject = (arguments.length == 1) ? th : obj; 
        outButton = (arguments.length == 1) ? null : th;        //�趨�ⲿ����İ�ť 
        //���ݵ�ǰ������������ʾ���������� 
        if (outType == 'yyyymm'){
			var reg = /^(\d+)-(\d{1,2})$/; 
        }
        else
			var reg = /^(\d+)-(\d{1,2})-(\d{1,2})$/; 
			
        var r = outObject.value.match(reg); 
        if(r!=null){ 
                r[2]=r[2]-1; 
               
                if (outType == 'yyyymm'){ 
                var d= new Date(r[1], r[2],1); 
                if(d.getFullYear()==r[1] && d.getMonth()==r[2]){ 
                        outDate=d;                //�����ⲿ��������� 
                } 
                else outDate=""; 
                        meizzSetDay(r[1],r[2]+1); 
                }
                else{
               
                 var d= new Date(r[1], r[2],r[3]); 
                if(d.getFullYear()==r[1] && d.getMonth()==r[2] && d.getDate()==r[3]){ 
                        outDate=d;                //�����ⲿ��������� 
                } 
                else outDate=""; 
                        meizzSetDay(r[1],r[2]+1);
                }        
        } 
        else{ 
                outDate=""; 
                meizzSetDay(new Date().getFullYear(), new Date().getMonth() + 1); 
        } 
        dads.visibility="visible"; 
        document.getElementsByName("meizzDateLayer")[0].focus(); 
        event.returnValue=false; 
	}
} 

var MonHead = new Array(12);                       //����������ÿ���µ�������� 
    MonHead[0] = 31; MonHead[1] = 28; MonHead[2] = 31; MonHead[3] = 30; MonHead[4]  = 31; MonHead[5]  = 30; 
    MonHead[6] = 31; MonHead[7] = 31; MonHead[8] = 30; MonHead[9] = 31; MonHead[10] = 30; MonHead[11] = 31; 

var meizzTheYear=new Date().getFullYear(); //������ı����ĳ�ʼֵ 
var meizzTheMonth=new Date().getMonth()+1; //�����µı����ĳ�ʼֵ 
var meizzWDay=new Array(39);               //����д���ڵ����� 

function document_onclick(e) //������ʱ�رոÿؼ�        //ie6�����������������л����㴦����� 
{ 
        var elm = (document.all)?(e.srcElement):(e.target); 
        if ((elm.getAttribute("Author")==null || elm.getAttribute("Author")=="") && elm != outObject && elm != outButton) 
    closeLayer(); 
} 

function document_onkeyup(e)                //��Esc���رգ��л�����ر� 
  { 
    if (window.event.keyCode==27){ 
                if(outObject)outObject.blur(); 
                closeLayer(); 
        } 
        else if(document.activeElement) 
                if(document.activeElement.getAttribute("Author")==null && document.activeElement != outObject && document.activeElement != outButton) 
                { 
                        closeLayer(); 
                } 
  } 

function meizzWriteHead(yy,mm)  //�� head ��д�뵱ǰ�������� 
  { 
        odatelayer.getElementById("meizzYearHead").innerHTML  = yy + " ��"; 
    odatelayer.getElementById("meizzMonthHead").innerHTML = mm + " ��"; 
  } 

function tmpSelectYearInnerHTML(strYear) //��ݵ������� 
{ 
  if (strYear.match(/\D/)!=null){alert("�����������������֣�");return;} 
  var m = (strYear) ? strYear : new Date().getFullYear(); 
  if (m < 1000 || m > 9999) {alert("���ֵ���� 1000 �� 9999 ֮�䣡");return;} 
  var n = m - 54; 
  if (n < 1000) n = 1000; 
  if (n + 26 > 9999) n = 9974; 
  var s = "<select Author=meizz name=tmpSelectYear style='font-size: 12px' " 
     s += "onblur='document.getElementById(\"tmpSelectYearLayer\").style.visibility=\"hidden\"' " 
     s += "onchange='blur();" 
     s += "parent.meizzTheYear = this.value; parent.meizzSetDay(parent.meizzTheYear,parent.meizzTheMonth)'>\r\n"; 
  var selectInnerHTML = s; 
  for (var i = n; i < n + 61; i++) 
  { 
    if (i == m) 
       {selectInnerHTML += "<option Author=wayx value='" + i + "' selected>" + i + "��" + "</option>\r\n";} 
    else {selectInnerHTML += "<option Author=wayx value='" + i + "'>" + i + "��" + "</option>\r\n";} 
  } 
  selectInnerHTML += "</select>"; 
  odatelayer.getElementById("tmpSelectYearLayer").innerHTML = selectInnerHTML; 
  odatelayer.getElementById("tmpSelectYearLayer").style.visibility="visible"; 
  odatelayer.getElementsByName("tmpSelectYear")[0].focus(); 

} 

function tmpSelectMonthInnerHTML(strMonth) //�·ݵ������� 
{ 
  if (strMonth.match(/\D/)!=null){alert("�·���������������֣�");return;} 
  var m = (strMonth) ? strMonth : new Date().getMonth() + 1; 
  var s = "<select Author=meizz name=tmpSelectMonth style='font-size: 12px' " 
     s += "onblur='document.getElementById(\"tmpSelectMonthLayer\").style.visibility=\"hidden\"' " 
     s += "onchange='blur();" 
     s += "parent.meizzTheMonth = this.value; parent.meizzSetDay(parent.meizzTheYear,parent.meizzTheMonth)'>\r\n"; 
  var selectInnerHTML = s; 
  for (var i = 1; i < 13; i++) 
  { 
    if (i == m) 
       {selectInnerHTML += "<option Author=wayx value='"+i+"' selected>"+i+"��"+"</option>\r\n";} 
    else {selectInnerHTML += "<option Author=wayx value='"+i+"'>"+i+"��"+"</option>\r\n";} 
  } 
  selectInnerHTML += "</select>"; 
  odatelayer.getElementById("tmpSelectMonthLayer").style.visibility="visible"; 
  odatelayer.getElementById("tmpSelectMonthLayer").innerHTML = selectInnerHTML; 
  odatelayer.getElementsByName("tmpSelectMonth")[0].focus(); 
} 

function closeLayer()               //�����Ĺر� 
  { 
    document.getElementsByName("meizzDateLayer")[0].style.visibility="hidden"; 
  } 

function IsPinYear(year)            //�ж��Ƿ���ƽ�� 
  { 
    if (0==year%4&&((year%100!=0)||(year%400==0))) return true;else return false; 
  } 

function GetMonthCount(year,month)  //�������Ϊ29�� 
  { 
    var c=MonHead[month-1];if((month==2)&&IsPinYear(year)) c++;return c; 
  } 
function GetDOW(day,month,year)     //��ĳ������ڼ� 
  { 
    var dt=new Date(year,month-1,day).getDay()/7; return dt; 
  } 

function meizzPrevY()  //��ǰ�� Year 
  { 
    if(meizzTheYear > 999 && meizzTheYear <10000){meizzTheYear--;} 
    else{alert("��ݳ�����Χ��1000-9999����");} 
    meizzSetDay(meizzTheYear,meizzTheMonth); 
  } 
function meizzNextY()  //���� Year 
  { 
    if(meizzTheYear > 999 && meizzTheYear <10000){meizzTheYear++;} 
    else{alert("��ݳ�����Χ��1000-9999����");} 
    meizzSetDay(meizzTheYear,meizzTheMonth); 
  } 
function meizzToday()  //Today Button 
  { 
        var today; 
    meizzTheYear = new Date().getFullYear(); 
    meizzTheMonth = new Date().getMonth()+1; 
    today=new Date().getDate(); 
    //meizzSetDay(meizzTheYear,meizzTheMonth); 
    if(outObject){ 
        switch(outType){
            case 'yyyymm':
                outObject.value=meizzTheYear + "-" + meizzTheMonth ;
                break;
            default:      
                outObject.value=meizzTheYear + "-" + meizzTheMonth + "-" + today;
        }
                 
    } 
    closeLayer(); 
  } 
function meizzPrevM()  //��ǰ���·� 
  { 
    if(meizzTheMonth>1){meizzTheMonth--}else{meizzTheYear--;meizzTheMonth=12;} 
    meizzSetDay(meizzTheYear,meizzTheMonth); 
  } 
function meizzNextM()  //�����·� 
  { 
    if(meizzTheMonth==12){meizzTheYear++;meizzTheMonth=1}else{meizzTheMonth++} 
    meizzSetDay(meizzTheYear,meizzTheMonth); 
  } 

function meizzSetDay(yy,mm)   //��Ҫ��д����********** 
{ 
  meizzWriteHead(yy,mm); 
  //���õ�ǰ���µĹ�������Ϊ����ֵ 
  meizzTheYear=yy; 
  meizzTheMonth=mm; 
  for (var i = 0; i < 39; i++){meizzWDay[i]=""};  //����ʾ�������ȫ����� 
  var day1 = 1,day2=1,firstday = new Date(yy,mm-1,1).getDay();  //ĳ�µ�һ������ڼ� 
  for (i=0;i<firstday;i++)meizzWDay[i]=GetMonthCount(mm==1?yy-1:yy,mm==1?12:mm-1)-firstday+i+1        //�ϸ��µ������ 
  for (i = firstday; day1 < GetMonthCount(yy,mm)+1; i++){meizzWDay[i]=day1;day1++;} 
  for (i=firstday+GetMonthCount(yy,mm);i<39;i++){meizzWDay[i]=day2;day2++} 
  for (i = 0; i < 39; i++) 
  { var da = odatelayer.getElementById("meizzDay"+i);     //��д�µ�һ���µ������������� 
    if (meizzWDay[i]!="") 
      { 
                //��ʼ���߿� 
                da.style.borderWidth="1px"; 
                da.style.borderStyle="solid"; 
                da.style.borderTopColor="#FFFFFF"; 
                da.style.borderRightColor="#01457c"; 
                da.style.borderBottomColor="#01457c"; 
                da.style.borderLeftColor="#FFFFFF"; 
                if(i<firstday)                //�ϸ��µĲ��� 
                { 
                        da.innerHTML="<a href='javascript:;'><font color=gray>" + meizzWDay[i] + "</font></a>"; 
                        da.title=(mm==1?12:mm-1) +"��" + meizzWDay[i] + "��"; 
                        da.onclick=Function("meizzDayClick(this.innerHTML.match(/\\d+/).toString(),-1)"); 
                        if(!outDate) 
                                da.style.backgroundColor = ((mm==1?yy-1:yy) == new Date().getFullYear() && 
                                        (mm==1?12:mm-1) == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate()) ? 
                                         "#FFD700":"#e0e0e0"; 
                        else 
                        { 
                                da.style.backgroundColor =((mm==1?yy-1:yy)==outDate.getFullYear() && (mm==1?12:mm-1)== outDate.getMonth() + 1 && 
                                meizzWDay[i]==outDate.getDate())? "#00ffff" : 
                                (((mm==1?yy-1:yy) == new Date().getFullYear() && (mm==1?12:mm-1) == new Date().getMonth()+1 && 
                                meizzWDay[i] == new Date().getDate()) ? "#FFD700":"#e0e0e0"); 
                                //��ѡ�е�������ʾΪ����ȥ 
                                if((mm==1?yy-1:yy)==outDate.getFullYear() && (mm==1?12:mm-1)== outDate.getMonth() + 1 && 
                                meizzWDay[i]==outDate.getDate()) 
                                { 
                                        da.style.borderTopColor="#01457c"; 
                                        da.style.borderRightColor="#FFFFFF"; 
                                        da.style.borderBottomColor="#FFFFFF"; 
                                        da.style.borderLeftColor="#01457c"; 
                                } 
                        } 
                } 
                else if (i>=firstday+GetMonthCount(yy,mm))                //�¸��µĲ��� 
                { 
                        da.innerHTML="<a href='javascript:;'><font color=gray>" + meizzWDay[i] + "</font></a>"; 
                        da.title=(mm%12+1) +"��" + meizzWDay[i] + "��"; 
                        da.onclick=Function("meizzDayClick(this.innerHTML.match(/\\d+/).toString(),1)"); 
                        if(!outDate) 
                                da.style.backgroundColor = ((mm==12?yy+1:yy) == new Date().getFullYear() && 
                                        (mm==12?1:mm+1) == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate()) ? 
                                         "#FFD700":"#e0e0e0"; 
                        else 
                        { 
                                da.style.backgroundColor =((mm==12?yy+1:yy)==outDate.getFullYear() && (mm==12?1:mm+1)== outDate.getMonth() + 1 && 
                                meizzWDay[i]==outDate.getDate())? "#00ffff" : 
                                (((mm==12?yy+1:yy) == new Date().getFullYear() && (mm==12?1:mm+1) == new Date().getMonth()+1 && 
                                meizzWDay[i] == new Date().getDate()) ? "#FFD700":"#e0e0e0"); 
                                //��ѡ�е�������ʾΪ����ȥ 
                                if((mm==12?yy+1:yy)==outDate.getFullYear() && (mm==12?1:mm+1)== outDate.getMonth() + 1 && 
                                meizzWDay[i]==outDate.getDate()) 
                                { 
                                        da.style.borderTopColor="#01457c"; 
                                        da.style.borderRightColor="#FFFFFF"; 
                                        da.style.borderBottomColor="#FFFFFF"; 
                                        da.style.borderLeftColor="#01457c"; 
                                } 
                        } 
                } 
                else                //���µĲ��� 
                { 
                        da.innerHTML="<a href='javascript:;'>" + meizzWDay[i] + "</a>"; 
                        da.title=mm +"��" + meizzWDay[i] + "��"; 
                        da.onclick=Function("meizzDayClick(this.innerHTML.match(/\\d+/).toString(),0)");                //��td����onclick�¼��Ĵ��� 
                        //����ǵ�ǰѡ������ڣ�����ʾ����ɫ�ı���������ǵ�ǰ���ڣ�����ʾ����ɫ���� 
                        if(!outDate) 
                                da.style.backgroundColor = (yy == new Date().getFullYear() && mm == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate())? 
                                        "#FFD700":"#e0e0e0"; 
                        else 
                        { 
                                da.style.backgroundColor =(yy==outDate.getFullYear() && mm== outDate.getMonth() + 1 && meizzWDay[i]==outDate.getDate())? 
                                        "#00ffff":((yy == new Date().getFullYear() && mm == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate())? 
                                        "#FFD700":"#e0e0e0"); 
                                //��ѡ�е�������ʾΪ����ȥ 
                                if(yy==outDate.getFullYear() && mm== outDate.getMonth() + 1 && meizzWDay[i]==outDate.getDate()) 
                                { 
                                        da.style.borderTopColor="#01457c"; 
                                        da.style.borderRightColor="#FFFFFF"; 
                                        da.style.borderBottomColor="#FFFFFF"; 
                                        da.style.borderLeftColor="#01457c"; 
                                } 
                        } 
                } 
        da.style.cursor="hand" 
      } 
    else{da.innerHTML="";da.style.backgroundColor="";da.style.cursor="default"} 
  } 
} 

function meizzDayClick(n,ex)  //�����ʾ��ѡȡ���ڣ������뺯��************* 
{ 
  var yy=meizzTheYear; 
  var mm = parseInt(meizzTheMonth)+ex;        //ex��ʾƫ����������ѡ���ϸ��·ݺ��¸��·ݵ����� 
        //�ж��·ݣ������ж�Ӧ�Ĵ��� 
        if(mm<1){ 
                yy--; 
                mm=12+mm; 
        } 
        else if(mm>12){ 
                yy++; 
                mm=mm-12; 
        } 
         
  if (mm < 10){mm = "0" + mm;} 
  if (outObject) 
  { 
    if (!n) {//outObject.value=""; 
      return;} 
    if ( n < 10){n = "0" + n;}
     
    //�ж��������������ʽ
    switch(outType){
        case 'yyyymm':
            outObject.value= yy + "-" + mm ;
            break;
        default:      
            outObject.value= yy + "-" + mm + "-" + n ;
    }
    
    closeLayer(); 
  } 
  else {closeLayer(); alert("����Ҫ����Ŀؼ����󲢲����ڣ�");} 
} 
