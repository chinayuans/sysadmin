/* 摘抄自www.chinahr.com
***********************************************************************
	COMMENT: 校园系统客户端验证基础类
	VER    : 1.0
	AUTHOR : 张晓延

	基础类说明
	对象:
	属性:
	方法:
	--------------------------------------------------------------------
	1.setElementsName(elementsName)
	参数     : elementsName
	功能     : 设置当前元素名称
	调用格式 : obj.setElementsName("input1")
	返回值   : 无
	--------------------------------------------------------------------
	2.setErrorMessage()
	参数     : errorString
	功能     : 设置当前信息,发生错误时,提示的信息
	调用格式 : obj.setErrorMessage("请输入正确的Email格式!")
	返回值   : 无
	--------------------------------------------------------------------
	3.getAnwserCount()
	参数     : 
	功能     : 获取元素个数(通过getElementsByName()方法对元素进行分组)
	调用格式 : obj.getAnwserCount()
	返回值   : 元素个数
	--------------------------------------------------------------------
	4.getObj(e)
	参数     : e 默认调用值=0
	功能     : 获取对象
	调用格式 : obj.getObj(0)
	返回值   : obj对象
	--------------------------------------------------------------------
	5.elementsType()
	参数     : 
	功能     : 获取类型
	调用格式 : obj.elementsType()
	返回值   : 对象类型
	--------------------------------------------------------------------
	6.getValue()
	参数     :  
	功能     : 获取Input对象的值
	调用格式 : obj.getValue()
	返回值   : 对象值
	--------------------------------------------------------------------
	7.doValidate()
	参数     :  
	功能     : 进行空值验证
	调用格式 : obj.doValidate()
	返回值   : 布尔值
	--------------------------------------------------------------------
	8.alertMessage()
	参数     :  
	功能     : alert错误的信息
	调用格式 : obj.alertMessage()
	返回值   : 
	--------------------------------------------------------------------
	9.getOtherInfo()
	参数     :  
	功能     : 获取其它这种类型的基本数据
	调用格式 : obj.getOtherInfo()
	返回值   : 5维数组
	--------------------------------------------------------------------
	10.doOtherValidate()
	参数     :  
	功能     : 验证类型为其它的空值判断
	调用格式 : obj.doOtherValidate()
	返回值   : 布尔值
	--------------------------------------------------------------------
	11.isASCII()
	参数     :  
	功能     : 
	调用格式 : 
	返回值   : 
	--------------------------------------------------------------------
	12.isDigit()
	参数     :  
	功能     : 
	调用格式 : 
	返回值   : 
	--------------------------------------------------------------------
	13.TrimStr()
	参数     :  
	功能     : 
	调用格式 : 
	返回值   : 
	--------------------------------------------------------------------
	14.isEmail()
	参数     :  
	功能     : 
	调用格式 : 
	返回值   : 
	--------------------------------------------------------------------
	15.isIDNumber()
	参数     :  
	功能     : 
	调用格式 : 
	返回值   : 
	--------------------------------------------------------------------
	16.isMobile()
	参数     :  
	功能     : 
	调用格式 : 
	返回值   : 
	--------------------------------------------------------------------
	17.isDate()
	参数     :  
	功能     : 
	调用格式 : 
	返回值   : 
	--------------------------------------------------------------------
	18.SBCtoDBC()
	参数     :  
	功能     : 全角转换半角
	调用格式 : 
	返回值   : 字符串
	--------------------------------------------------------------------
	19.limitStrLen()
	参数     :  
	功能     : 
	调用格式 : 
	返回值   : 
	--------------------------------------------------------------------
	20.isYear()
	参数     :  
	功能     : 
	调用格式 : 
	返回值   : 
	--------------------------------------------------------------------
	21.isMonth()
	参数     :  
	功能     : 
	调用格式 : 
	返回值   : 
***********************************************************************
*/
function classValidate(){
	this.elementsName="";
	this.setElementsName=function(elementsName){    //方法:设置elementsName
		this.elementsName=elementsName;
	}
	this.errorMessage="";
	this.setErrorMessage=function(errorString){     //方法:设置errorMessage
		this.errorMessage=errorString;
	}
	this.getAnwserCount=function(){    //方法：获取问题答案个数
		return document.getElementsByName(this.elementsName).length;
	}
    this.getObj=function (e){//方法：获取对象
        /*
		获取对象，根据元素个数的不同，而有不同的写法。
		==1,document.all[obj]
		!=1,document.all[obj][e]
		*/
        var obj;
        ((this.getAnwserCount())==1?obj=document.all[this.elementsName]:obj=document.all[this.elementsName][e]);
        return obj;
    }
	this.elementsType=function(){    //方法：获取该元素类型
		
        var strIdType;
        strIdType=this.getObj(0).type;
		return 	strIdType;
	}
    
	this.getValue=function(){	//方法：获取Input对象的值
		if(this.getAnwserCount()==1){
			return this.getObj(0).value;
		}else{
			for (var i=0;i<this.getAnwserCount();i++ ){
				if(this.getObj(i).checked==true){
					return this.getObj(i).value;
				}else{
					return "";
				}
			}
		}
		
	}
    this.doValidate=function(){
		var i;
        var intNoChecked=0;
        var intYesChecked=0;

        //if (this.elementsType().indexOf("select")!=-1){
        //    for(i=0;i<=this.getAnwserCount()-1;i++){
        //        strOptionValue=this.getObj(0).options.value;
		//		if(strOptionValue.length==0){
		//			return false;
		//			break;
		//		}else{
		//			return true;
        //        }
        //    }
        //}else{
			for(i=0;i<=this.getAnwserCount()-1;i++){
				if((this.elementsType()=="checkbox")||(this.elementsType()=="radio")){
					((this.getObj(i).checked==false)?intNoChecked++:intYesChecked++);
				}
				if((this.elementsType()=="text")||(this.elementsType()=="textarea")){
					strDigit=this.getObj(i).value;
					var nIndex,cCheck;
					bEmpty=false
					for (nIndex=0; nIndex<strDigit.length; nIndex++){	
						cCheck = strDigit.charAt(nIndex);
						if ((cCheck != ' ' && cCheck != '  ') )	bEmpty=true;
					}
					if (bEmpty==false) intNoChecked++;
				}
				if(this.elementsType().indexOf("select")!=-1){
					if(this.getObj(0).options.value.length==0){
						intNoChecked=this.getAnwserCount();
					}
				}
			}
			if(intNoChecked==this.getAnwserCount()){
				return false;
			}else{
				return true;
			}
		//}
    }
    this.alertMessage=function(){   //方法：弹出信息，返回到焦点对象
        alert(this.errorMessage);
        this.getObj(0).focus();
    }
	this.getOtherInfo=function(){	//方法：获取其它这种类型的基本数据
		intIsOtherText = this.getObj(0).style.isOtherText;
		if(intIsOtherText==1){
			strOtherId=this.getObj(0).style.otherID;
			strCheckId=this.getObj(0).style.checkID;
			strCheckIdValue=document.all[strCheckId].value;

			//对空值进行判断
			var nIndex,cCheck,bEmpty;
			bEmpty=false;
			for (nIndex=0; nIndex<strCheckIdValue.length; nIndex++){	
				cCheck = strCheckIdValue.charAt(nIndex);
				if ((cCheck != ' ' && cCheck != '  ') )	bEmpty=true;
			}
			arrayName= new Array(intIsOtherText,strOtherId,strCheckId,strCheckIdValue,bEmpty);
			
		}else{
			arrayName= new Array(-1,0,0,0,false);
		}
		return(arrayName);
	}
	this.doOtherValidate=function(){
		var aOtherInfo=this.getOtherInfo();
		if (aOtherInfo[0]!=-1){
			if(this.elementsType()=="radio"){
				for(j=0;j<=this.getAnwserCount()-1;j++){
					//alert(j)
					//判断当其它选项被选择，Text文本框是否已经填入数值。
					if((this.getObj(j).style.otherVarStat==1)&&(this.getObj(j).checked==true))
						return aOtherInfo[4];
				}
				return -1;
			}
			if(this.elementsType()=="checkbox"){
				if(document.all[aOtherInfo[1]].checked==true){
					return aOtherInfo[4];
				}else{
					return -1;
				}
			}
			if(this.elementsType().indexOf("select")!=-1){
				var strSelectInd,strChecked
				strSelectInd=this.getObj(0).options.selectedIndex;
				strChecked=this.getObj(0).options[strSelectInd].style.otherVarStat;
				if(strChecked==1){
					return aOtherInfo[4];
				}
			}
		}else{
			return true;
		}
		//return aOtherInfo[0];
	}
	this.isASCII=function(str){
		var alphaChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		var digitChars = "0123456789";
		var asciiChars = alphaChars + digitChars + "!\"#$%&'()*+,-./:;<=>?@[\]^_`{}~";
		var v_len = str.length;
		var i;
		for (i = 0; i < v_len; i++){
			if (asciiChars.indexOf(str.charAt(i)) == -1)
				return false;
		}
		return true;
	}
	this.isDigit=function(str){
		var v_len = str.length;
		var digitChars = "0123456789";
		var i;
		for (i = 0; i < v_len; i++){
		if (digitChars.indexOf(str.charAt(i)) == -1)
			return false;
		}
		return true;
	}
	this.TrimStr=function(str){
		var len=str.length;
		var i=0;
		var itemp1=0;
		var itemp2=0;
		for (i=0;i<len;i++){
			if (str.charAt(i)!=' ' && str.charAt(i)!='　')   {
				itemp1=i; 
				break;
			}
		}
		for(i=len-1;i>0;i--) {
			if(str.charAt(i)!=' ' && str.charAt(i)!='　')   {
				itemp2=i+1;
				break;
			}
		}
		if (len==1 ) itemp2=1;
			return str.substring(itemp1,itemp2);
	}
	this.isEmail=function(str){
		var pass=0;
		if (window.RegExp) {
			var tempS="a";
			var tempReg=new RegExp(tempS);
			if (tempReg.test(tempS)) pass=1;
		}
		if (!pass)
			return (str.indexOf(".") > 2) && (str.indexOf("@") > 0);
		var r1=new RegExp("(@.*@)|(\\.\\.)|(@\\.)|(^\\.)");
		var r2=new RegExp("^[a-zA-Z0-9\\.\\!\\#\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\}\\~]*[a-zA-Z0-9\\!\\#\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\}\\~]\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$");
		return (!r1.test(str) && r2.test(str));
	}
	this.isIDNumber=function(str){
		var mID     = this.TrimStr(str);
		var mLen    = mID.length;

		if ( mLen != 15 && mLen != 18 )   return false;

		if ( mLen == 15 && mID.search(/\d{15}/gi) == -1 ) //not 15 digits
			return false;
			
		if ( mLen == 18 && mID.search(/\d{17}[0-9,x,X]/gi) == -1 ) //not 17 digits + 1 digits or X
			return false;
			
		return true;
	}
	this.isMobile=function( str ){
		var mID     = this.TrimStr(str);
		var mLen    = mID.length;

		if ( mLen != 11 )   return false;   // 11 digits
		
		if ( mID.search(/13[0-9]\d{8}/gi) == -1 ) // not china mobile
			return false;
			
		return true;
	}
	//yyyy-mm-dd
	this.isDate=function(sDate) {
		var mDate   = this.TrimStr(sDate);
		var mLen    = mDate.length;

		if ( mLen < 8 || mLen > 10 )   return false;

		mDate = mDate.replace(/(\/)/g, "-");
		if ( mDate.search(/\d{4}-\d{1,2}-\d{1,2}/gi) == -1 ) return false;
		var arrDate = mDate.split('-');
		var mYear   = parseInt(arrDate[0],10);
		var mMonth  = parseInt(arrDate[1],10) - 1;  
		var mDay    = parseInt(arrDate[2],10);

		if (mYear<1900 || mYear>3000) return false;

		var objDate = new Date(mYear,mMonth,mDay);
		if(objDate.getFullYear() != mYear || objDate.getMonth() != mMonth || objDate.getDate() != mDay )
			return false;

		return true;
	}
	this.SBCtoDBC=function(str){
		var tmp = "";
    
		for(var i=0;i<str.length;i++)
		{
			if(str.charCodeAt(i)>65248)
			{
				tmp+=String.fromCharCode(str.charCodeAt(i)-65248);
			}
			else
			{
				tmp+=str.charAt(i);
			}
		}
		return tmp;
	}
	this.limitStrLen=function(str,limitNum){
		var mStr = this.TrimStr(str);
		var mLen = mStr.length;
		if(mLen>limitNum){
			return false;
		}
		return true;
	}
	this.isYear=function(str){
		var mStr = this.TrimStr(str);
		if(this.isDigit(mStr)==false) return false;
		if(str.length!=4) return false;
		if (parseInt(mStr)<1900 || parseInt(mStr)>9999) return false;
		return true;
	}

	this.isMonth=function(str){
		var mStr = this.TrimStr(str);
		if(this.isDigit(mStr)==false) return false;
		if (parseInt(mStr)>12) return false;
		return true;
	}
	//yyyy-mm
	this.isDateNoDay=function(sDate) {
		var mDate   = this.TrimStr(sDate);
		var mLen    = mDate.length;

		if ( mLen < 6 || mLen > 7 )   return false;
		if (!this.isDigit(mDate.charAt(mLen-1)))  return false;

		mDate = mDate.replace(/(\/)/g, "-");
		if ( mDate.search(/\d{4}-\d{1,2}/gi) == -1 ) return false;

		var arrDate = mDate.split('-');
		var mYear   = parseInt(arrDate[0],10);
		var mMonth  = parseInt(arrDate[1],10) - 1;  

		if (mYear<1900 || mYear>3000) return false;

		var objDate = new Date(mYear,mMonth,1);
		if(objDate.getFullYear() != mYear || objDate.getMonth() != mMonth)
			return false;

		return true;
	}
	this.isEmpty=function(elementsName,errMessage){
		this.setElementsName(elementsName);
		this.setErrorMessage(errMessage);
		if(this.doValidate()==false){
    
        this.alertMessage();
    
        return false;
    
    }
	}
}

/*====================显示使用方法===========================
<SCRIPT LANGUAGE="javascript">
<!--
    function validate()
    {
        var objValidate = new classValidate();
        
            if(objValidate.isEmpty('item_start_date_1',"From为必填(选)项!")==false){
                return false;
            }
            
            if(objValidate.limitStrLen(objValidate.getValue(),7)==false){
                objValidate.setErrorMessage("输入的From超过规定长度,请重新输入!");
                objValidate.alertMessage();
                return false;
            }
            
            if(objValidate.isDateNoDay(objValidate.getValue())==false){
                objValidate.setErrorMessage('时间格式不正确,请重新输入!');
                objValidate.alertMessage();
                return false;
            }
            
            if(objValidate.isEmpty('item_end_date_1',"To为必填(选)项!")==false){
                return false;
            }
            
            if(objValidate.limitStrLen(objValidate.getValue(),7)==false){
                objValidate.setErrorMessage("输入的To超过规定长度,请重新输入!");
                objValidate.alertMessage();
                return false;
            }
            
            if(objValidate.isDateNoDay(objValidate.getValue())==false){
                objValidate.setErrorMessage('时间格式不正确,请重新输入!');
                objValidate.alertMessage();
                return false;
            }
            
            if(objValidate.isEmpty('item_school_1',"School为必填(选)项!")==false){
                return false;
            }
            
            if (objValidate.getValue()==252){
                if(objValidate.isEmpty('item_other_school_1',"School为必填(选)项!")==false){
                    return false;
                }
            }
            
            objValidate.setElementsName('item_other_school_1');
            
            if(objValidate.limitStrLen(objValidate.getValue(),150)==false){
                objValidate.setErrorMessage("输入的Other School超过规定长度,请重新输入!");
                objValidate.alertMessage();
                return false;
            }
            
            if(objValidate.isEmpty('item_major_type',"Major Type为必填(选)项!")==false){
                return false;
            }
            
            if(objValidate.isEmpty('item_major_1',"Major为必填(选)项!")==false){
                return false;
            }
            
            if(objValidate.limitStrLen(objValidate.getValue(),150)==false){
                objValidate.setErrorMessage("输入的Major超过规定长度,请重新输入!");
                objValidate.alertMessage();
                return false;
            }
            
            if(objValidate.isEmpty('item_degree',"Degree为必填(选)项!")==false){
                return false;
            }
            
            objValidate.setElementsName('item_d_degree_1');
            
            if(objValidate.limitStrLen(objValidate.getValue(),150)==false){
                objValidate.setErrorMessage("输入的Double degree超过规定长度,请重新输入!");
                objValidate.alertMessage();
                return false;
            }
            
            objValidate.setElementsName('courses');
            
            if(objValidate.limitStrLen(objValidate.getValue(),500)==false){
                objValidate.setErrorMessage("输入的Courses<br/>(500 letters)超过规定长度,请重新输入!");
                objValidate.alertMessage();
                return false;
            }
            document.all.subAdd.disabled=true;
        return true;
    }
    
    function DeleteAndSubmit(id){
        if(confirm("请你确认是否准备删除选中的教育背景？")==true)
        {
            document.frmMain._action.value='delete';
            document.frmMain.id.value = id;
            document.frmMain.submit();
        }
    }
    
    function SaveAndNext(ObjSrc){
        var boolSubmit = false;
        var frm = document.frmMain;
        var Obj = frm.item_school_1;
        if (Obj) {
            if (Obj.value != '') {
                var cf = confirm('你已经填写了数据，但没有保存。\n\n保存数据请点击 '+frm.subAdd.value+' 按钮；\n如果不保存，请点击 取消 按钮');
                if (cf == false) {
                 boolSubmit = true;
                }
            } else {
                boolSubmit = true;
            }
        } else {
            boolSubmit = true;
        }
        if (boolSubmit == true) {
         ObjSrc.disabled=true;
         document.frmMain._action.value='save';
         document.frmMain.submit();
        }
    }
    
    function showSchoolName(schoolName,otherSchoolName){
        if(schoolName==252){
            return otherSchoolName;
        }else{
            for (var iCount = 0; iCount < intCollegeCityCount; iCount++){
                for (var intIndex = 0; intIndex <  arrCollegeID[iCount].length; intIndex++){
                    if(arrCollegeID[iCount][intIndex]==schoolName){
                        return arrCollegeValue[iCount][intIndex];
                     }
                }
            }
        }
    }
    
    function DisplayDD(pObject,pOther){
        if (pObject.value == 30){
            pOther.style.display = ""
            }
        else{
            pOther.style.display = "none"
            }
    }
    //-->
</SCRIPT>
*/