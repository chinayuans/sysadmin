//<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=0 VIEWASTEXT></OBJECT>    
function printsetup(){
    // ��ӡҳ������
    wb.execwb(8,1);
}

function printpreview(){
    // ��ӡҳ��Ԥ��        
    wb.execwb(7,1);        
}

function printit(){
    if (confirm('ȷ����ӡ��')) {
    wb.execwb(6,6)
    }
}
/*
���������������������÷����о����£�
WebBrowser.ExecWB(1,1) ��
Web.ExecWB(2,1) �ر��������е�IE���ڣ�����һ���´���
Web.ExecWB(4,1) ������ҳ
Web.ExecWB(6,1) ��ӡ
Web.ExecWB(7,1) ��ӡԤ��
Web.ExecWB(8,1) ��ӡҳ������
Web.ExecWB(10,1) �鿴ҳ������
Web.ExecWB(15,1) �����ǳ������д�ȷ��
Web.ExecWB(17,1) ȫѡ
Web.ExecWB(22,1) ˢ��
Web.ExecWB(45,1) �رմ�������ʾ
*/