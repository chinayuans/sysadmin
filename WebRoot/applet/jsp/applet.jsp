
<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


</head>

<!-- 
由于放置在web-inf目录下class文件受到保护不可以访问
所以applet的class文件不能放在web-inf目录下， 
因为这是个特殊目录，所以jsp不能找到applet的.class这个文件。
应该将applet的class放置到其他目录中去,使得jsp文件可以访问.

applet的运行机制:
根据codebase指定的目录去下载.class文件到client的浏览器的JVM容器中运行
由于web-inf目录下class文件受到保护不可以访问,所以applet的class文件无法
下载到本地的JVM中运行,所以出错.

Jsp的运行机制:
将Jsp文件生成成servlet后,再编译成.class文件,然后在服务器的JVM中运行,
再将生成的网页传给client的浏览器.

总结:从上面我们可以看到applet和jsp有本质的区别.注意client的浏览器使用
applet要将允许ActiveX执行这个选项选上.

codebase:
为了使Java程序可以从网络上下载类，我们需要使用codebase，codebase指定了Java程序在网络上何处可以找到需要的类。 

...................................................

在jsp文件的applet标签中使用相对目录方法: 
codebase="./classes/"  表示在当前目录下的classes目录中
codebase="../classes/" 表示当前目录的父目录的classes目录中


applet的class文件放置在http://127.0.0.1:8080/sysadmin/applet/classes/目录下面
那么使用绝对目录方法:
codebase="http://127.0.0.1:8080/sysadmin/applet/classes/" 或者使用
codebase="/sysadmin/applet/classes/"

包的使用:
Applet的完整类名为　　org.appfuse.web.applet.DrawString
class文件放置在/webapps/applet/classes/org/appfuse/web/applet/下面
使用这个applet的jsp文件放置在 /webapps/applet/ 目录中
此时jsp文件中的applet标签为
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
这是一个applet的执行页面.
<br>
</body>
</html>
