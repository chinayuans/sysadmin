function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

/* This function is used to change the style class of an element */
function swapClass(obj, newStyle) {
    obj.className = newStyle;
}

function isUndefined(value) {   
    var undef;   
    return value == undef; 
}

/* Function for showing and hiding elements that use 'display:none' to hide */
function toggleDisplay(targetId)
{
    if (document.getElementById) {
        target = document.getElementById(targetId);
        if (target.style.display == "none"){
            target.style.display = "";
        } else {
            target.style.display = "none";
        }
    }
  
   //var object=eval(targetId);
   //if(object.style.display=="none"){
   //   object.style.display="";
   // }else {
   //   object.style.display="none";
   // }
   
}

// toggle visibility 
function toggleVisibility(targetId) {
    if (document.getElementById) {
        target = document.getElementById(targetId);
        if (target.style.visibility == "hidden"){
            target.style.visibility = "visible";
        } else {
            target.style.visibility = "hidden";
        }
    }
}

// toggle display 
function toggleDisplayByImage(targetID, imageID, linkImage, linkImageCollapsed)
{
    if (document.getElementById){
        target = document.getElementById(targetID);
        if (target.style.display == "none") {
            target.style.display = "";
        } else {
            target.style.display = "none";
        }
        
        if (linkImageCollapsed != "") {
            image = document.getElementById(imageID);
            if (target.style.display == "none") {
                image.src = linkImageCollapsed;
            } else {
                image.src = linkImage;
            }
        }
    }
}



function checkAll(theForm) { // check all the checkboxes in the list
  for (var i=0;i<theForm.elements.length;i++) {
    var e = theForm.elements[i];
        var eName = e.name;
        if (eName != 'allbox' && 
            (e.type.indexOf("checkbox") == 0)) {
            e.checked = theForm.allbox.checked;     
        }
    } 
}

/* Function to clear a form of all it's values */
function clearForm(frmObj) {
    for (var i = 0; i < frmObj.length; i++) {
        var element = frmObj.elements[i];
        if(element.type.indexOf("text") == 0 || 
                element.type.indexOf("password") == 0) {
                    element.value="";
        } else if (element.type.indexOf("radio") == 0) {
            element.checked=false;
        } else if (element.type.indexOf("checkbox") == 0) {
            element.checked = false;
        } else if (element.type.indexOf("select") == 0) {
            for(var j = 0; j < element.length ; j++) {
                element.options[j].selected=false;
            }
            element.options[0].selected=true;
        }
    } 
}

/* Function to get a form's values in a string */
function getFormAsString(frmObj) {
    var query = "";
    for (var i = 0; i < frmObj.length; i++) {
        var element = frmObj.elements[i];
        if (element.type.indexOf("checkbox") == 0 || 
            element.type.indexOf("radio") == 0) { 
            if (element.checked) {
                query += element.name + '=' + escape(element.value) + "&";
            }
        } else if (element.type.indexOf("select") == 0) {
            for (var j = 0; j < element.length ; j++) {
                if (element.options[j].selected) {
                    query += element.name + '=' + escape(element.value) + "&";
                }
            }
        } else {
            query += element.name + '=' 
                  + escape(element.value) + "&"; 
        }
    } 
    return query;
}

/* Function to hide form elements that show through
   the search form when it is visible */
function toggleForm(frmObj, iState) // 1 visible, 0 hidden 
{
    for(var i = 0; i < frmObj.length; i++) {
        if (frmObj.elements[i].type.indexOf("select") == 0 || frmObj.elements[i].type.indexOf("checkbox") == 0) {
            frmObj.elements[i].style.visibility = iState ? "visible" : "hidden";
        }
    } 
}

/* Helper function for re-ordering options in a select */
function opt(txt,val,sel) {
    this.txt=txt;
    this.val=val;
    this.sel=sel;
}

/* Function for re-ordering <option>'s in a <select> */
function move(list,to) {     
    var total=list.options.length;
    index = list.selectedIndex;
    if (index == -1) return false;
    if (to == +1 && index == total-1) return false;
    if (to == -1 && index == 0) return false;
    to = index+to;
    var opts = new Array();
    for (i=0; i<total; i++) {
        opts[i]=new opt(list.options[i].text,list.options[i].value,list.options[i].selected);
    }
    tempOpt = opts[to];
    opts[to] = opts[index];
    opts[index] = tempOpt
    list.options.length=0; // clear
    
    for (i=0;i<opts.length;i++) {
        list.options[i] = new Option(opts[i].txt,opts[i].val);
        list.options[i].selected = opts[i].sel;
    }
    
    list.focus();
} 

/*  This function is to select all options in a multi-valued <select> */
function selectAll(elementId) {
    var element = document.getElementById(elementId);
    len = element.length;
    if (len != 0) {
        for (i = 0; i < len; i++) {
            element.options[i].selected = true;
        }
    }
}

/*  这个函数用来控制是否选择所有的 checkedbox */
//elementId 是指所有的checkedbox 这个对象
//target    是指 选中或者不选中的开关 由他来决定。
function selectAll_CheckedBox(elementId,target) {
    //var element = document.getElementById(elementId);
    //var element = object;
    var element=document.all[elementId];
    
    len = element.length;
    //如果同一个id有多个checkbox.
    if (len != 0) {
        for (i = 0; i < len; i++) {
        
           if(target.checked==true){
             element[i].checked = true;
             }
           else{
             element[i].checked = false;
           }
        }
    }
    //只有同一个id一个checkbox
    if (len==null){
         //alert(element.checked);
    	 if(target.checked==true){
             element.checked = true;
             }
         else{
             element.checked = false;
         }
    }
}

/* This function is used to select a checkbox by passing
 * in the checkbox id
 */
function toggleChoice(elementId) {
    var element = document.getElementById(elementId);
    if (element.checked) {
        element.checked = false;
    } else {
        element.checked = true;
    }
}

/* This function is used to select a radio button by passing
 * in the radio button id and index you want to select
 */
function toggleRadio(elementId, index) {
    var element = document.getElementsByName(elementId)[index];
    element.checked = true;
}


/* This function is used to open a pop-up window */
function openWindow(url, winTitle, winParams) {
    winName = window.open(url, winTitle, winParams);
    winName.focus();
}


/* This function is to open search results in a pop-up window */
function openSearch(url, winTitle) {
    var screenWidth = parseInt(screen.availWidth);
    var screenHeight = parseInt(screen.availHeight);

    var winParams = "width=" + screenWidth + ",height=" + screenHeight;
        winParams += ",left=0,top=0,toolbar,scrollbars,resizable,status=yes";

    openWindow(url, winTitle, winParams);
}

/* This function is used to set cookies */
function setCookie(name,value,expires,path,domain,secure) {
  document.cookie = name + "=" + escape (value) +
    ((expires) ? "; expires=" + expires.toGMTString() : "") +
    ((path) ? "; path=" + path : "") +
    ((domain) ? "; domain=" + domain : "") + ((secure) ? "; secure" : "");
}

/* This function is used to get cookies */
function getCookie(name) {
    var prefix = name + "=" 
    var start = document.cookie.indexOf(prefix) 

    if (start==-1) {
        return null;
    }
    
    var end = document.cookie.indexOf(";", start+prefix.length) 
    if (end==-1) {
        end=document.cookie.length;
    }

    var value=document.cookie.substring(start+prefix.length, end) 
    return unescape(value);
}

/* This function is used to delete cookies */
function deleteCookie(name,path,domain) {
  if (getCookie(name)) {
    document.cookie = name + "=" +
      ((path) ? "; path=" + path : "") +
      ((domain) ? "; domain=" + domain : "") +
      "; expires=Thu, 01-Jan-70 00:00:01 GMT";
  }
}

// This function is for stripping leading and trailing spaces
function trim(str) { 
    if (str != null) {
        var i; 
        for (i=0; i<str.length; i++) {
            if (str.charAt(i)!=" ") {
                str=str.substring(i,str.length); 
                break;
            } 
        } 
    
        for (i=str.length-1; i>=0; i--) {
            if (str.charAt(i)!=" ") {
                str=str.substring(0,i+1); 
                break;
            } 
        } 
        
        if (str.charAt(0)==" ") {
            return ""; 
        } else {
            return str; 
        }
    }
} 

// This function is used by the login screen to validate user/pass
// are entered. 
function validateRequired(form) {                                    
    var bValid = true;
    var focusField = null;
    var i = 0;                                                                                          
    var fields = new Array();                                                                           
    oRequired = new required();                                                                         
                                                                                                        
    for (x in oRequired) {                                                                              
        if ((form[oRequired[x][0]].type == 'text' || form[oRequired[x][0]].type == 'textarea' || form[oRequired[x][0]].type == 'select-one' || form[oRequired[x][0]].type == 'radio' || form[oRequired[x][0]].type == 'password') && form[oRequired[x][0]].value == '') {
           if (i == 0)
              focusField = form[oRequired[x][0]]; 
              
           fields[i++] = oRequired[x][1];
            
           bValid = false;                                                                             
        }                                                                                               
    }                                                                                                   
                                                                                                       
    if (fields.length > 0) {
       focusField.focus();
       alert(fields.join('\n'));                                                                      
    }                                                                                                   
                                                                                                       
    return bValid;                                                                                      
}

// This function is a generic function to create form elements
function createFormElement(element, type, name, id, value, parent) {
    var e = document.createElement(element);
    e.setAttribute("name", name);
    e.setAttribute("type", type);
    e.setAttribute("id", id);
    e.setAttribute("value", value);
    parent.appendChild(e);
}

function confirmDelete(obj) {   
    var msg = "你是否确认要删除" + obj + "?";
    if (obj==null) {
     msg = "你是否确认要删除?";
    }
    
    ans = confirm(msg);
    if (ans) {
        return true;
    } else {
        return false;
    }
}

function highlightTableRows(tableId) {
    var previousClass = null;
    var table = document.getElementById(tableId); 
    var tbody = table.getElementsByTagName("tbody")[0];
    if (tbody == null) {
        var rows = table.getElementsByTagName("tr");
    } else {
        var rows = tbody.getElementsByTagName("tr");
    }
    // add event handlers so rows light up and are clickable
    for (i=0; i < rows.length; i++) {
        rows[i].onmouseover = function() { previousClass=this.className;this.className+=' over' };
        rows[i].onmouseout = function() { this.className=previousClass };
        rows[i].onclick = function() {
            var cell = this.getElementsByTagName("td")[0];
            var link = cell.getElementsByTagName("a")[0];
            location.href = link.getAttribute("href");
            this.style.cursor="wait";
        }
    }
}

function highlightFormElements() {
    // add input box highlighting 
    addFocusHandlers(document.getElementsByTagName("input"));
    addFocusHandlers(document.getElementsByTagName("textarea"));
}

function addFocusHandlers(elements) {
    for (i=0; i < elements.length; i++) {
        if (elements[i].type != "button" && elements[i].type != "submit" &&
            elements[i].type != "reset" && elements[i].type != "checkbox") {
            elements[i].onfocus=function() {this.className='focus';this.select()};
            elements[i].onclick=function() {this.select()};
            elements[i].onblur=function() {this.className=''};
        }
    }
}

function radio(clicked){
    var form = clicked.form;
    var checkboxes = form.elements[clicked.name];
    if (!clicked.checked || !checkboxes.length) {
        clicked.parentNode.parentNode.className="";
        return false;
    }

    for (i=0; i<checkboxes.length; i++) {
        if (checkboxes[i] != clicked) {
            checkboxes[i].checked=false;
            checkboxes[i].parentNode.parentNode.className="";
        }
    }

    // highlight the row    
    clicked.parentNode.parentNode.className="over";
}

function changeFramePosition(object){
  //alert(object.src.lastIndexOf("images/solid_arrow_up.jpg"));
  
  //找到
  if(object.src.lastIndexOf("images/solid_arrow_up.jpg")!=-1){
  	   object.src="images/solid_arrow_down.jpg";
       //parent.parent.mainFrame.rows="0,*";
       if (document.getElementById) {
        target = parent.document.getElementById("mainFrame");
  		target.rows="0,*";
       }		
       
  }//没有找到
  else{
       object.src="images/solid_arrow_up.jpg";
       //parent.parent.mainFrame.rows="40%,*";       
       if (document.getElementById) {
        target = parent.document.getElementById("mainFrame");
  		target.rows="40%,*";
       }		
  }
};

function displayFrame(){
   //parent.parent.mainFrame.rows="40%,*";
   if (document.getElementById) {
        target = parent.document.getElementById("mainFrame");
  		target.rows="40%,*";
   }		
};
function hideFrame(){
   //parent.parent.mainFrame.rows="0,*";
   if (document.getElementById) {
        target = parent.document.getElementById("mainFrame");
  		target.rows="0,*";
   }		
};

function goback(){
   history.go(-1);
}


//以下内容的使用放置到每个单独的jsp的<script type="text/javascript"/>中去使用；
//本global.js中拒绝使用

// 对当前页面的所有的表单中的input,area元素高亮度显示
// window.onload = highlightFormElements;

// Show the document's title on the status bar
// window.defaultStatus=document.title;

