
<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


</head>

<!-- 
���ڷ�����web-infĿ¼��class�ļ��ܵ����������Է���
����applet��class�ļ����ܷ���web-infĿ¼�£� 
��Ϊ���Ǹ�����Ŀ¼������jsp�����ҵ�applet��.class����ļ���
Ӧ�ý�applet��class���õ�����Ŀ¼��ȥ,ʹ��jsp�ļ����Է���.

applet�����л���:
����codebaseָ����Ŀ¼ȥ����.class�ļ���client���������JVM����������
����web-infĿ¼��class�ļ��ܵ����������Է���,����applet��class�ļ��޷�
���ص����ص�JVM������,���Գ���.

Jsp�����л���:
��Jsp�ļ����ɳ�servlet��,�ٱ����.class�ļ�,Ȼ���ڷ�������JVM������,
�ٽ����ɵ���ҳ����client�������.

�ܽ�:���������ǿ��Կ���applet��jsp�б��ʵ�����.ע��client�������ʹ��
appletҪ������ActiveXִ�����ѡ��ѡ��.

codebase:
Ϊ��ʹJava������Դ������������࣬������Ҫʹ��codebase��codebaseָ����Java�����������Ϻδ������ҵ���Ҫ���ࡣ 

...................................................

��jsp�ļ���applet��ǩ��ʹ�����Ŀ¼����: 
codebase="./classes/"  ��ʾ�ڵ�ǰĿ¼�µ�classesĿ¼��
codebase="../classes/" ��ʾ��ǰĿ¼�ĸ�Ŀ¼��classesĿ¼��


applet��class�ļ�������http://127.0.0.1:8080/sysadmin/applet/classes/Ŀ¼����
��ôʹ�þ���Ŀ¼����:
codebase="http://127.0.0.1:8080/sysadmin/applet/classes/" ����ʹ��
codebase="/sysadmin/applet/classes/"

����ʹ��:
Applet����������Ϊ����org.appfuse.web.applet.DrawString
class�ļ�������/webapps/applet/classes/org/appfuse/web/applet/����
ʹ�����applet��jsp�ļ������� /webapps/applet/ Ŀ¼��
��ʱjsp�ļ��е�applet��ǩΪ
 code="org.appfuse.web.applet.DrawString.class"
 codebase="./classes/"

-->

<applet code="org.appfuse.web.applet.DrawString.class"  codebase="<%=request.getContextPath()%>/applet/classes/" width="200" height="100">
<param name="para1" value="This is para1">
<param name="para2" value="This is para2">
If you can see this message,your brower doesn't support Java
</applet>



<body>
<hr>
����һ��applet��ִ��ҳ��.
<br>
</body>
</html>
