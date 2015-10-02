//============================使用方法示例============
//以下代码<input>等等放置在具体输入日期格式的表单中。
//<INPUT type="text" name="item_birth_date_1" size="20" maxlength="10" value=""  onFocusOld="setday(this,document.getElementsByName('item_birth_date_1')[0],'yyyymmdd');" onBlur="javascript:document_onclick(event);" ReadOnlyT/>
//&nbsp;
//<a HREF="javascript:void(0);">
//<img width="18" height="18" BORDER="0" ALIGN="ABSMIDDLE" src="images/calendar.gif" onClick="setday(this,document.getElementsByName('item_birth_date_1')[0],'yyyymmdd');">
//</a>
//&nbsp;
//<SPAN CLASS="comment"> Date Format: YYYY-MM-DD 
//====================================================


//==================================================== 参数设定部分 ======================================================= 
var bMoveable=true;                //设置日历是否可以拖动 
var _VersionInfo="";       //版本信息 
var outType="";           //日期输出样式
//==================================================== WEB 页面显示部分 ===================================================== 
               //存放日历层的HTML代码 
document.writeln('<iframe name=meizzDateLayer Author=wayx frameborder=0 style="position:absolute; width:144px; height:213px; z-index:9998; visibility:hidden"></iframe>'); 

var odatelayer; 
var outObject; 
var outButton;                //点击的按钮 
var outDate="";                //存放对象的日期 


function initStr(pColor){
var strFrame; 
strFrame='<style>'; 
strFrame+='INPUT.button{BORDER-RIGHT: '+pColor+' 1px solid;BORDER-TOP:'+pColor+' 1px solid;BORDER-LEFT: '+pColor+'1px solid;'; 
strFrame+='BORDER-BOTTOM: '+pColor+' 1px solid;BACKGROUND-COLOR: #fff8ec;font-family:宋体;}'; 
strFrame+='TD{FONT-SIZE: 9pt;font-family:宋体;}'; 
strFrame+='A{text-decoration:none;font-weight:bold;color:black}'; 
strFrame+='</style>'; 
strFrame+='<scr' + 'ipt>'; 
strFrame+='var datelayerx,datelayery;        /*存放日历控件的鼠标位置*/'; 
strFrame+='var bDrag;        /*标记是否开始拖动*/'; 
strFrame+='function document_onmousemove(e){/*在鼠标移动事件中，如果开始拖动日历，则移动日历*/'; 
strFrame+='if(bDrag){'; 
strFrame+='        var DateLayer=parent.document.getElementsByName("meizzDateLayer")[0].style;'; 
strFrame+='                DateLayer.left = parseInt(DateLayer.left)+ e.clientX-datelayerx; /*由于每次移动以后鼠标位置都恢复为初始的位置，因此写法与div中不同*/'; 
strFrame+='if(DateLayer.top=="")DateLayer.top=0;'; 
strFrame+='                DateLayer.top = parseInt(DateLayer.top)+ e.clientY-datelayery;}}'; 
strFrame+='function DragStart(e){                /*开始日历拖动*/'; 
strFrame+="if ((document.all?e.button:e.which)!=1) return;\n"; 
strFrame+='var DateLayer=parent.document.getElementsByName("meizzDateLayer")[0].style;'; 
strFrame+='        datelayerx=e.clientX;'; 
strFrame+='        datelayery=e.clientY;'; 
strFrame+='        bDrag=true;}'; 
strFrame+='function DragEnd(){                /*结束日历拖动*/'; 
strFrame+='        bDrag=false;}'; 
strFrame+='</scr' + 'ipt>'; 
strFrame+='<body onmousemove="document_onmousemove(event)">'; 
strFrame+='<div style="z-index:9999;position: absolute; left:0; top:0;" onselectstart="return false"><span id=tmpSelectYearLayer Author=wayx style="z-index: 9999;position: absolute;top: 3; left: 19;visibility: hidden"></span>'; 
strFrame+='<span id=tmpSelectMonthLayer Author=wayx style="z-index: 9999;position: absolute;top: 3; left: 78;visibility: hidden"></span>'; 
strFrame+='<table border=1 cellspacing=0 cellpadding=0 width=142 height=160 bordercolor='+pColor+' bgcolor='+pColor+' Author="wayx">'; 
strFrame+='  <tr Author="wayx"><td width=142 height=23 Author="wayx" bgcolor=#FFFFFF><table border=0 cellspacing=1 cellpadding=0 width=140 Author="wayx" height=23>'; 
strFrame+='  <tr align=center Author="wayx"><td width=16 align=center bgcolor='+pColor ; 
strFrame+=' onclick="parent.meizzPrevM()" title="向前翻 1 月" Author=meizz><a href="javascript:;" style="font-size:12px;color: #ffffff" Author=meizz><</a>'; 
strFrame+='        </td><td width=60 align=center style="font-size:12px;cursor:default" Author=meizz '; 
strFrame+='onmouseover="style.backgroundColor=\'#FFD700\'" onmouseout="style.backgroundColor=\'white\'" '; 
strFrame+='onclick="parent.tmpSelectYearInnerHTML(innerHTML.match(/\\d{4}/).toString());" title="点击这里选择年份"><span Author=meizz id=meizzYearHead></span></td>'; 
strFrame+='<td width=48 align=center style="font-size:12px;cursor:default" Author=meizz onmouseover="style.backgroundColor=\'#FFD700\'" '; 
strFrame+=' onmouseout="style.backgroundColor=\'white\'" onclick="parent.tmpSelectMonthInnerHTML(innerHTML.match(/\\d\\d?/).toString())"'; 
strFrame+='        title="点击这里选择月份"><span id=meizzMonthHead Author=meizz></span></td>'; 
strFrame+='        <td width=16 bgcolor='+pColor+' align=center style="font-size:12px;cursor: hand;color: #ffffff" '; 
strFrame+=' onclick="parent.meizzNextM()" title="向后翻 1 月" Author=meizz><a href="javascript:;" style="font-size:12px;color: #ffffff;font:bold" Author=meizz>></a></td></tr>'; 
strFrame+='    </table></td></tr>'; 
strFrame+='  <tr Author="wayx"><td width=142 height=18 Author="wayx">'; 
strFrame+='<table border=1 cellspacing=0 bgcolor='+pColor+' cellpadding=0  ' + (bMoveable? 'onmousedown="DragStart(event)" onmouseup="DragEnd()"':''); 
strFrame+=' BORDERCOLORLIGHT='+pColor+' BORDERCOLORDARK=#FFFFFF width=140 height=20 Author="wayx" style="cursor:' + (bMoveable ? 'move':'default') + '">'; 
strFrame+='<tr Author="wayx" align=center valign=bottom><td style="font-size:12px;color:#FFFFFF" Author=meizz>日</td>'; 
strFrame+='<td style="font-size:12px;color:#FFFFFF" Author=meizz>一</td><td style="font-size:12px;color:#FFFFFF" Author=meizz>二</td>'; 
strFrame+='<td style="font-size:12px;color:#FFFFFF" Author=meizz>三</td><td style="font-size:12px;color:#FFFFFF" Author=meizz>四</td>'; 
strFrame+='<td style="font-size:12px;color:#FFFFFF" Author=meizz>五</td><td style="font-size:12px;color:#FFFFFF" Author=meizz>六</td></tr>'; 
strFrame+='</table></td></tr>'; 
strFrame+='  <tr Author="wayx"><td width=142 height=120 Author="wayx">'; 
strFrame+='    <table border=1 cellspacing=2 cellpadding=0 BORDERCOLORLIGHT='+pColor+' BORDERCOLORDARK=#FFFFFF bgcolor=#fff8ec width=140 height=120 Author="wayx">'; 
var n=0; for (j=0;j<5;j++){ strFrame+= ' <tr align=center Author="wayx">'; for (i=0;i<7;i++){ 
strFrame+='<td width=20 height=20 id=meizzDay'+n+' style="font-size:12px" Author=meizz onclick=parent.meizzDayClick(this.innerHTML.match(/\\d+/).toString(),0)></td>';n++;} 
strFrame+='</tr>';} 
strFrame+='      <tr align=center Author="wayx">'; 
for (i=35;i<39;i++)strFrame+='<td width=20 height=20 id=meizzDay'+i+' style="font-size:12px" Author=wayx onclick="parent.meizzDayClick(this.innerHTML.match(/\\d+/).toString(),0)"></td>'; 
strFrame+='        <td colspan=3 align=right Author=meizz><a href="javascript:;" onclick=parent.closeLayer() style="font-size:12px;text-decoration:underline;font-weight:300;"'; 
strFrame+='         Author=meizz title="' + _VersionInfo + '">关闭</a> </td></tr>'; 
strFrame+='    </table></td></tr><tr Author="wayx"><td Author="wayx">'; 
strFrame+='        <table border=0 cellspacing=1 cellpadding=0 width=100% Author="wayx" bgcolor=#FFFFFF>'; 
strFrame+='          <tr Author="wayx"><td Author=meizz align=left><input Author=meizz type=button class=button value="<<" title="向前翻 1 年" onclick="parent.meizzPrevY()" '; 
strFrame+='             onfocus="this.blur()" style="font-size: 12px; height: 20px" id=button2 name=button2><input Author=meizz class=button title="向前翻 1 月" type=button '; 
strFrame+='             value="< " onclick="parent.meizzPrevM()" onfocus="this.blur()" style="font-size: 12px; height: 20px" id=button3 name=button3></td><td '; 
strFrame+='             Author=meizz align=center><input Author=meizz type=button class=button value=今日 onclick="parent.meizzToday()" '; 
strFrame+='             onfocus="this.blur()" title="当前日期" style="font-size: 12px; height: 20px; cursor:hand" id=button4 name=button4></td><td '; 
strFrame+='             Author=meizz align=right><input Author=meizz type=button class=button value=" >" onclick="parent.meizzNextM()" '; 
strFrame+='             onfocus="this.blur()" title="向后翻 1 月" class=button style="font-size: 12px; height: 20px" id=button5 name=button5><input '; 
strFrame+='             Author=meizz type=button class=button value=">>" title="向后翻 1 年" onclick="parent.meizzNextY()"'; 
strFrame+='             onfocus="this.blur()" style="font-size: 12px; height: 20px" id=button6 name=button6></td>'; 
strFrame+='</tr></table></td></tr></table></div></body>';
return strFrame;
}


function init(pColor){ 
        window.frames.meizzDateLayer.document.writeln(initStr(pColor)); 
        window.frames.meizzDateLayer.document.close();                //解决ie进度条不结束的问题 
        odatelayer=window.frames.meizzDateLayer.document;                //存放日历对象 
} 
//==================================================== WEB 页面显示部分 ====================================================== 

function setday(tt,obj,pOutType) //主调函数 
{       
        if (arguments.length >  3){alert("对不起！传入本控件的参数太多！");return;} 
        if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;} 
        outType = pOutType;
        var dads  = document.getElementsByName("meizzDateLayer")[0].style; 
	if (outObject!=obj || dads.visibility=='hidden'){
        var th = tt; 
        var ttop  = tt.offsetTop;     //TT控件的定位点高 
        var thei  = tt.offsetHeight;  //TT控件本身的高 
        var tleft = tt.offsetLeft;    //TT控件的定位点宽 
        var ttyp  = tt.type;          //TT控件的类型 
        while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;} 
        dads.top  = ttop+thei ; 
        dads.left = tleft; 
        outObject = (arguments.length == 1) ? th : obj; 
        outButton = (arguments.length == 1) ? null : th;        //设定外部点击的按钮 
        //根据当前输入框的日期显示日历的年月 
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
                        outDate=d;                //保存外部传入的日期 
                } 
                else outDate=""; 
                        meizzSetDay(r[1],r[2]+1); 
                }
                else{
               
                 var d= new Date(r[1], r[2],r[3]); 
                if(d.getFullYear()==r[1] && d.getMonth()==r[2] && d.getDate()==r[3]){ 
                        outDate=d;                //保存外部传入的日期 
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

var MonHead = new Array(12);                       //定义阳历中每个月的最大天数 
    MonHead[0] = 31; MonHead[1] = 28; MonHead[2] = 31; MonHead[3] = 30; MonHead[4]  = 31; MonHead[5]  = 30; 
    MonHead[6] = 31; MonHead[7] = 31; MonHead[8] = 30; MonHead[9] = 31; MonHead[10] = 30; MonHead[11] = 31; 

var meizzTheYear=new Date().getFullYear(); //定义年的变量的初始值 
var meizzTheMonth=new Date().getMonth()+1; //定义月的变量的初始值 
var meizzWDay=new Array(39);               //定义写日期的数组 

function document_onclick(e) //任意点击时关闭该控件        //ie6的情况可以由下面的切换焦点处理代替 
{ 
        var elm = (document.all)?(e.srcElement):(e.target); 
        if ((elm.getAttribute("Author")==null || elm.getAttribute("Author")=="") && elm != outObject && elm != outButton) 
    closeLayer(); 
} 

function document_onkeyup(e)                //按Esc键关闭，切换焦点关闭 
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

function meizzWriteHead(yy,mm)  //往 head 中写入当前的年与月 
  { 
        odatelayer.getElementById("meizzYearHead").innerHTML  = yy + " 年"; 
    odatelayer.getElementById("meizzMonthHead").innerHTML = mm + " 月"; 
  } 

function tmpSelectYearInnerHTML(strYear) //年份的下拉框 
{ 
  if (strYear.match(/\D/)!=null){alert("年份输入参数不是数字！");return;} 
  var m = (strYear) ? strYear : new Date().getFullYear(); 
  if (m < 1000 || m > 9999) {alert("年份值不在 1000 到 9999 之间！");return;} 
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
       {selectInnerHTML += "<option Author=wayx value='" + i + "' selected>" + i + "年" + "</option>\r\n";} 
    else {selectInnerHTML += "<option Author=wayx value='" + i + "'>" + i + "年" + "</option>\r\n";} 
  } 
  selectInnerHTML += "</select>"; 
  odatelayer.getElementById("tmpSelectYearLayer").innerHTML = selectInnerHTML; 
  odatelayer.getElementById("tmpSelectYearLayer").style.visibility="visible"; 
  odatelayer.getElementsByName("tmpSelectYear")[0].focus(); 

} 

function tmpSelectMonthInnerHTML(strMonth) //月份的下拉框 
{ 
  if (strMonth.match(/\D/)!=null){alert("月份输入参数不是数字！");return;} 
  var m = (strMonth) ? strMonth : new Date().getMonth() + 1; 
  var s = "<select Author=meizz name=tmpSelectMonth style='font-size: 12px' " 
     s += "onblur='document.getElementById(\"tmpSelectMonthLayer\").style.visibility=\"hidden\"' " 
     s += "onchange='blur();" 
     s += "parent.meizzTheMonth = this.value; parent.meizzSetDay(parent.meizzTheYear,parent.meizzTheMonth)'>\r\n"; 
  var selectInnerHTML = s; 
  for (var i = 1; i < 13; i++) 
  { 
    if (i == m) 
       {selectInnerHTML += "<option Author=wayx value='"+i+"' selected>"+i+"月"+"</option>\r\n";} 
    else {selectInnerHTML += "<option Author=wayx value='"+i+"'>"+i+"月"+"</option>\r\n";} 
  } 
  selectInnerHTML += "</select>"; 
  odatelayer.getElementById("tmpSelectMonthLayer").style.visibility="visible"; 
  odatelayer.getElementById("tmpSelectMonthLayer").innerHTML = selectInnerHTML; 
  odatelayer.getElementsByName("tmpSelectMonth")[0].focus(); 
} 

function closeLayer()               //这个层的关闭 
  { 
    document.getElementsByName("meizzDateLayer")[0].style.visibility="hidden"; 
  } 

function IsPinYear(year)            //判断是否闰平年 
  { 
    if (0==year%4&&((year%100!=0)||(year%400==0))) return true;else return false; 
  } 

function GetMonthCount(year,month)  //闰年二月为29天 
  { 
    var c=MonHead[month-1];if((month==2)&&IsPinYear(year)) c++;return c; 
  } 
function GetDOW(day,month,year)     //求某天的星期几 
  { 
    var dt=new Date(year,month-1,day).getDay()/7; return dt; 
  } 

function meizzPrevY()  //往前翻 Year 
  { 
    if(meizzTheYear > 999 && meizzTheYear <10000){meizzTheYear--;} 
    else{alert("年份超出范围（1000-9999）！");} 
    meizzSetDay(meizzTheYear,meizzTheMonth); 
  } 
function meizzNextY()  //往后翻 Year 
  { 
    if(meizzTheYear > 999 && meizzTheYear <10000){meizzTheYear++;} 
    else{alert("年份超出范围（1000-9999）！");} 
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
function meizzPrevM()  //往前翻月份 
  { 
    if(meizzTheMonth>1){meizzTheMonth--}else{meizzTheYear--;meizzTheMonth=12;} 
    meizzSetDay(meizzTheYear,meizzTheMonth); 
  } 
function meizzNextM()  //往后翻月份 
  { 
    if(meizzTheMonth==12){meizzTheYear++;meizzTheMonth=1}else{meizzTheMonth++} 
    meizzSetDay(meizzTheYear,meizzTheMonth); 
  } 

function meizzSetDay(yy,mm)   //主要的写程序********** 
{ 
  meizzWriteHead(yy,mm); 
  //设置当前年月的公共变量为传入值 
  meizzTheYear=yy; 
  meizzTheMonth=mm; 
  for (var i = 0; i < 39; i++){meizzWDay[i]=""};  //将显示框的内容全部清空 
  var day1 = 1,day2=1,firstday = new Date(yy,mm-1,1).getDay();  //某月第一天的星期几 
  for (i=0;i<firstday;i++)meizzWDay[i]=GetMonthCount(mm==1?yy-1:yy,mm==1?12:mm-1)-firstday+i+1        //上个月的最后几天 
  for (i = firstday; day1 < GetMonthCount(yy,mm)+1; i++){meizzWDay[i]=day1;day1++;} 
  for (i=firstday+GetMonthCount(yy,mm);i<39;i++){meizzWDay[i]=day2;day2++} 
  for (i = 0; i < 39; i++) 
  { var da = odatelayer.getElementById("meizzDay"+i);     //书写新的一个月的日期星期排列 
    if (meizzWDay[i]!="") 
      { 
                //初始化边框 
                da.style.borderWidth="1px"; 
                da.style.borderStyle="solid"; 
                da.style.borderTopColor="#FFFFFF"; 
                da.style.borderRightColor="#01457c"; 
                da.style.borderBottomColor="#01457c"; 
                da.style.borderLeftColor="#FFFFFF"; 
                if(i<firstday)                //上个月的部分 
                { 
                        da.innerHTML="<a href='javascript:;'><font color=gray>" + meizzWDay[i] + "</font></a>"; 
                        da.title=(mm==1?12:mm-1) +"月" + meizzWDay[i] + "日"; 
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
                                //将选中的日期显示为凹下去 
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
                else if (i>=firstday+GetMonthCount(yy,mm))                //下个月的部分 
                { 
                        da.innerHTML="<a href='javascript:;'><font color=gray>" + meizzWDay[i] + "</font></a>"; 
                        da.title=(mm%12+1) +"月" + meizzWDay[i] + "日"; 
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
                                //将选中的日期显示为凹下去 
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
                else                //本月的部分 
                { 
                        da.innerHTML="<a href='javascript:;'>" + meizzWDay[i] + "</a>"; 
                        da.title=mm +"月" + meizzWDay[i] + "日"; 
                        da.onclick=Function("meizzDayClick(this.innerHTML.match(/\\d+/).toString(),0)");                //给td赋予onclick事件的处理 
                        //如果是当前选择的日期，则显示亮蓝色的背景；如果是当前日期，则显示暗黄色背景 
                        if(!outDate) 
                                da.style.backgroundColor = (yy == new Date().getFullYear() && mm == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate())? 
                                        "#FFD700":"#e0e0e0"; 
                        else 
                        { 
                                da.style.backgroundColor =(yy==outDate.getFullYear() && mm== outDate.getMonth() + 1 && meizzWDay[i]==outDate.getDate())? 
                                        "#00ffff":((yy == new Date().getFullYear() && mm == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate())? 
                                        "#FFD700":"#e0e0e0"); 
                                //将选中的日期显示为凹下去 
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

function meizzDayClick(n,ex)  //点击显示框选取日期，主输入函数************* 
{ 
  var yy=meizzTheYear; 
  var mm = parseInt(meizzTheMonth)+ex;        //ex表示偏移量，用于选择上个月份和下个月份的日期 
        //判断月份，并进行对应的处理 
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
     
    //判断是用哪种输出方式
    switch(outType){
        case 'yyyymm':
            outObject.value= yy + "-" + mm ;
            break;
        default:      
            outObject.value= yy + "-" + mm + "-" + n ;
    }
    
    closeLayer(); 
  } 
  else {closeLayer(); alert("您所要输出的控件对象并不存在！");} 
} 
