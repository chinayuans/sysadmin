//<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=0 VIEWASTEXT></OBJECT>    
function printsetup(){
    // 打印页面设置
    wb.execwb(8,1);
}

function printpreview(){
    // 打印页面预览        
    wb.execwb(7,1);        
}

function printit(){
    if (confirm('确定打印吗？')) {
    wb.execwb(6,6)
    }
}
/*
关于这个组件还有其他的用法，列举如下：
WebBrowser.ExecWB(1,1) 打开
Web.ExecWB(2,1) 关闭现在所有的IE窗口，并打开一个新窗口
Web.ExecWB(4,1) 保存网页
Web.ExecWB(6,1) 打印
Web.ExecWB(7,1) 打印预览
Web.ExecWB(8,1) 打印页面设置
Web.ExecWB(10,1) 查看页面属性
Web.ExecWB(15,1) 好像是撤销，有待确认
Web.ExecWB(17,1) 全选
Web.ExecWB(22,1) 刷新
Web.ExecWB(45,1) 关闭窗体无提示
*/