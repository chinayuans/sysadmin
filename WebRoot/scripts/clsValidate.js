/* ժ����www.chinahr.com
***********************************************************************
	COMMENT: У԰ϵͳ�ͻ�����֤������
	VER    : 1.0
	AUTHOR : ������

	������˵��
	����:
	����:
	����:
	--------------------------------------------------------------------
	1.setElementsName(elementsName)
	����     : elementsName
	����     : ���õ�ǰԪ������
	���ø�ʽ : obj.setElementsName("input1")
	����ֵ   : ��
	--------------------------------------------------------------------
	2.setErrorMessage()
	����     : errorString
	����     : ���õ�ǰ��Ϣ,��������ʱ,��ʾ����Ϣ
	���ø�ʽ : obj.setErrorMessage("��������ȷ��Email��ʽ!")
	����ֵ   : ��
	--------------------------------------------------------------------
	3.getAnwserCount()
	����     : 
	����     : ��ȡԪ�ظ���(ͨ��getElementsByName()������Ԫ�ؽ��з���)
	���ø�ʽ : obj.getAnwserCount()
	����ֵ   : Ԫ�ظ���
	--------------------------------------------------------------------
	4.getObj(e)
	����     : e Ĭ�ϵ���ֵ=0
	����     : ��ȡ����
	���ø�ʽ : obj.getObj(0)
	����ֵ   : obj����
	--------------------------------------------------------------------
	5.elementsType()
	����     : 
	����     : ��ȡ����
	���ø�ʽ : obj.elementsType()
	����ֵ   : ��������
	--------------------------------------------------------------------
	6.getValue()
	����     :  
	����     : ��ȡInput�����ֵ
	���ø�ʽ : obj.getValue()
	����ֵ   : ����ֵ
	--------------------------------------------------------------------
	7.doValidate()
	����     :  
	����     : ���п�ֵ��֤
	���ø�ʽ : obj.doValidate()
	����ֵ   : ����ֵ
	--------------------------------------------------------------------
	8.alertMessage()
	����     :  
	����     : alert�������Ϣ
	���ø�ʽ : obj.alertMessage()
	����ֵ   : 
	--------------------------------------------------------------------
	9.getOtherInfo()
	����     :  
	����     : ��ȡ�����������͵Ļ�������
	���ø�ʽ : obj.getOtherInfo()
	����ֵ   : 5ά����
	--------------------------------------------------------------------
	10.doOtherValidate()
	����     :  
	����     : ��֤����Ϊ�����Ŀ�ֵ�ж�
	���ø�ʽ : obj.doOtherValidate()
	����ֵ   : ����ֵ
	--------------------------------------------------------------------
	11.isASCII()
	����     :  
	����     : 
	���ø�ʽ : 
	����ֵ   : 
	--------------------------------------------------------------------
	12.isDigit()
	����     :  
	����     : 
	���ø�ʽ : 
	����ֵ   : 
	--------------------------------------------------------------------
	13.TrimStr()
	����     :  
	����     : 
	���ø�ʽ : 
	����ֵ   : 
	--------------------------------------------------------------------
	14.isEmail()
	����     :  
	����     : 
	���ø�ʽ : 
	����ֵ   : 
	--------------------------------------------------------------------
	15.isIDNumber()
	����     :  
	����     : 
	���ø�ʽ : 
	����ֵ   : 
	--------------------------------------------------------------------
	16.isMobile()
	����     :  
	����     : 
	���ø�ʽ : 
	����ֵ   : 
	--------------------------------------------------------------------
	17.isDate()
	����     :  
	����     : 
	���ø�ʽ : 
	����ֵ   : 
	--------------------------------------------------------------------
	18.SBCtoDBC()
	����     :  
	����     : ȫ��ת�����
	���ø�ʽ : 
	����ֵ   : �ַ���
	--------------------------------------------------------------------
	19.limitStrLen()
	����     :  
	����     : 
	���ø�ʽ : 
	����ֵ   : 
	--------------------------------------------------------------------
	20.isYear()
	����     :  
	����     : 
	���ø�ʽ : 
	����ֵ   : 
	--------------------------------------------------------------------
	21.isMonth()
	����     :  
	����     : 
	���ø�ʽ : 
	����ֵ   : 
***********************************************************************
*/
function classValidate(){
	this.elementsName="";
	this.setElementsName=function(elementsName){    //����:����elementsName
		this.elementsName=elementsName;
	}
	this.errorMessage="";
	this.setErrorMessage=function(errorString){     //����:����errorMessage
		this.errorMessage=errorString;
	}
	this.getAnwserCount=function(){    //��������ȡ����𰸸���
		return document.getElementsByName(this.elementsName).length;
	}
    this.getObj=function (e){//��������ȡ����
        /*
		��ȡ���󣬸���Ԫ�ظ����Ĳ�ͬ�����в�ͬ��д����
		==1,document.all[obj]
		!=1,document.all[obj][e]
		*/
        var obj;
        ((this.getAnwserCount())==1?obj=document.all[this.elementsName]:obj=document.all[this.elementsName][e]);
        return obj;
    }
	this.elementsType=function(){    //��������ȡ��Ԫ������
		
        var strIdType;
        strIdType=this.getObj(0).type;
		return 	strIdType;
	}
    
	this.getValue=function(){	//��������ȡInput�����ֵ
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
    this.alertMessage=function(){   //������������Ϣ�����ص��������
        alert(this.errorMessage);
        this.getObj(0).focus();
    }
	this.getOtherInfo=function(){	//��������ȡ�����������͵Ļ�������
		intIsOtherText = this.getObj(0).style.isOtherText;
		if(intIsOtherText==1){
			strOtherId=this.getObj(0).style.otherID;
			strCheckId=this.getObj(0).style.checkID;
			strCheckIdValue=document.all[strCheckId].value;

			//�Կ�ֵ�����ж�
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
					//�жϵ�����ѡ�ѡ��Text�ı����Ƿ��Ѿ�������ֵ��
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
			if (str.charAt(i)!=' ' && str.charAt(i)!='��')   {
				itemp1=i; 
				break;
			}
		}
		for(i=len-1;i>0;i--) {
			if(str.charAt(i)!=' ' && str.charAt(i)!='��')   {
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

/*====================��ʾʹ�÷���===========================
<SCRIPT LANGUAGE="javascript">
<!--
    function validate()
    {
        var objValidate = new classValidate();
        
            if(objValidate.isEmpty('item_start_date_1',"FromΪ����(ѡ)��!")==false){
                return false;
            }
            
            if(objValidate.limitStrLen(objValidate.getValue(),7)==false){
                objValidate.setErrorMessage("�����From�����涨����,����������!");
                objValidate.alertMessage();
                return false;
            }
            
            if(objValidate.isDateNoDay(objValidate.getValue())==false){
                objValidate.setErrorMessage('ʱ���ʽ����ȷ,����������!');
                objValidate.alertMessage();
                return false;
            }
            
            if(objValidate.isEmpty('item_end_date_1',"ToΪ����(ѡ)��!")==false){
                return false;
            }
            
            if(objValidate.limitStrLen(objValidate.getValue(),7)==false){
                objValidate.setErrorMessage("�����To�����涨����,����������!");
                objValidate.alertMessage();
                return false;
            }
            
            if(objValidate.isDateNoDay(objValidate.getValue())==false){
                objValidate.setErrorMessage('ʱ���ʽ����ȷ,����������!');
                objValidate.alertMessage();
                return false;
            }
            
            if(objValidate.isEmpty('item_school_1',"SchoolΪ����(ѡ)��!")==false){
                return false;
            }
            
            if (objValidate.getValue()==252){
                if(objValidate.isEmpty('item_other_school_1',"SchoolΪ����(ѡ)��!")==false){
                    return false;
                }
            }
            
            objValidate.setElementsName('item_other_school_1');
            
            if(objValidate.limitStrLen(objValidate.getValue(),150)==false){
                objValidate.setErrorMessage("�����Other School�����涨����,����������!");
                objValidate.alertMessage();
                return false;
            }
            
            if(objValidate.isEmpty('item_major_type',"Major TypeΪ����(ѡ)��!")==false){
                return false;
            }
            
            if(objValidate.isEmpty('item_major_1',"MajorΪ����(ѡ)��!")==false){
                return false;
            }
            
            if(objValidate.limitStrLen(objValidate.getValue(),150)==false){
                objValidate.setErrorMessage("�����Major�����涨����,����������!");
                objValidate.alertMessage();
                return false;
            }
            
            if(objValidate.isEmpty('item_degree',"DegreeΪ����(ѡ)��!")==false){
                return false;
            }
            
            objValidate.setElementsName('item_d_degree_1');
            
            if(objValidate.limitStrLen(objValidate.getValue(),150)==false){
                objValidate.setErrorMessage("�����Double degree�����涨����,����������!");
                objValidate.alertMessage();
                return false;
            }
            
            objValidate.setElementsName('courses');
            
            if(objValidate.limitStrLen(objValidate.getValue(),500)==false){
                objValidate.setErrorMessage("�����Courses<br/>(500 letters)�����涨����,����������!");
                objValidate.alertMessage();
                return false;
            }
            document.all.subAdd.disabled=true;
        return true;
    }
    
    function DeleteAndSubmit(id){
        if(confirm("����ȷ���Ƿ�׼��ɾ��ѡ�еĽ���������")==true)
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
                var cf = confirm('���Ѿ���д�����ݣ���û�б��档\n\n������������ '+frm.subAdd.value+' ��ť��\n��������棬���� ȡ�� ��ť');
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