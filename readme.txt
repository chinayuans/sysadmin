http://127.0.0.1:8080/sysadmin

重要提示:一定要注意版本问题.
规定如下: 
struts1.2.9+spring1.2RC+hibernate2.1.7
J2ee1.3+JSTL1.0

一个完整的Test-Driven Development：
spring中的
classpath:  默认情况下为/web-inf/classes,/web-inf/lib 两个目录下，
              如果要指明/web-inf目录下的具体内容，必须这样使用：不可以使用classpath.
            /WEB-INF/applicationContext*.xml。
example举例：
/WEB-INF/applicationContext-*.xml    （ 明确的绝对路径）
classpath*:META-INF/applicationContext-*.xml 
（在/classes目录下的位置，或者/lib中具体jar包的位置）
  


以下为测试驱动开发的详细案例：
==================================
DAO层：
==================================
0.通过建立UserDAOTest开始.
1.必须建立BaseDAOTest.通过他来得到初始环境。
2.通过testXXX方法，要求建立Model中的User,UserDAO
3.建立User，又得产生BaseObject.
4.建立UserDAO接口,又得产生DAO接口。
5.映射文件user.hbm.xml的生成。
6.applicationContext.xml的生成。
   主要声明sessionFactory,transactionManager,dataSource
7.UserDAO的implementation->UserDAOHibernate
8.applicationContext.xml继续写：
  UserDAO -----UserDAOHibernate.
9.准备测试。可以使用ant或者eclipse的junit。
  这里我使用JUnit运行UserDAOTest。马上可以看到结果。

到此阶段DAO层写完成了。
完成了DAOtestcase,DAO,DAOImpl。

=================================
Service层：
=================================
10.写UserManagerTest开始。
11.写BaseServiceTest类。
12.UserManagerTest--->接口UserManager.
13.根据UserManager--->产生UserManagerImpl的实现类。
14.applicationContext.xml继续写：
   UserManagerImpl-----中注入UserDAO接口
   UserManager---------对UserManagerImpl进行了事务封装。
其中这里面事务的aop上用了继承的思想来实现。
15.准备测试。可以使用ant或者eclipse的junit。
    这里我使用JUnit运行UserManagerTest。马上可以看到结果。
注意：以上测试为integration test.因为他是建立在DAO基础上进行的测试。
     可以采用unit test，使用EasyMock 可以进行单元测试，脱离DAO的环境，
     因为EasyMock可以伪造DAO。
     
     
=================================
Web层：
=================================
注意：这个测试包括也两种，一种Mock Testing(模拟测试)，
	另外一种为In-Container Testing(容器内测试，在
	真实的servlet容器内测试).这两种测试都属于strutsTestCase技术。
    本次测试采用Mock Testing.
    Mock Testing------MockStrutsTestCase
    In-Container Testing----CatusStrutsTestCase
16.写UserActionTest测试case。
17.根据UserActionTest,由此我们可以得到struts-config.xml中的<form-bean/>和<action-mapping/>
18.根据UserActionTest得到UserAction。
19.根据UserAction中的内容得到messages.properties。
20.根据UserAction使用spring粘合剂的强大作用，在UserAction中
   注入service层的UserManager，提供给Action调用service层的服务。
   同时生成applicationContext-web.xml文件。修改struts-config中
   <controller/> <message-resources />内容。

21.MockStrutsTestCase环境为：
   /WEB-INF/struts-config.xml，
   /WEB-INF/web.xml,
   /WEB-INF/applicationContext-dao.xml,
   /WEB-INF/applicationContext-service.xml,
   /WEB-INF/applicationContext-web.xml
   /WEB-INF/classes/messages.properties
   
   注意： applicationContext-dao.xml，
        applicationContext-service.xml，
        applicationContext-web.xml，
        messages.properties必须与其他"source folder"中的内容保持一致。
        
22.准备测试。可以使用ant或者eclipse的junit。
    这里我使用JUnit运行UserActionTest。马上可以看到结果。
    
    
注意：在"source folder"为test的下面（test/web,test/service,test/dao）
	 都有一个log4j.xml,注意这三个log4j.xml的内容不同，主要为的是查看不同的显示内容。
	 
工程中的build.xml暂时没有用处.以后进行改进.	 
	 
============================
hibernate2与hibernate3的区别：
============================
hibernate2:   net.sf.hibernate.
hibernate3:   org.hibernate.

============================
spring1.2.7之后的一些变化：
============================
file: classpath: classpath*:
1.Must support fully qualified URLs, e.g. "file:C:/test.dat".
2.Must support classpath pseudo-URLs, e.g. "classpath:test.dat","classpath:**/test.dat".
3.Should support relative file paths, e.g. "WEB-INF/test.dat", "".
4.最广泛的支持："classpath*:META-INF/*-beans.xml" will find all 
  *-beans.xml files in the class path, be it in "classes" directories
  or in JAR files.
		
String[] paths = {"classpath*:**/bean*.xml" }; // 在jar中，还有classes目录下去寻找相关的东西。
String[] paths = {"classpath:/com/sample/Ioc/bean.xml" }; // 只在classpath中去寻找相关的东西。
String[] paths = {"classpath:**/*bean.xml" }; // bad expression。
String[] paths = {"file:D:/**/bean*.xml" };    // good expression
String[] paths = {"/com/sample/Ioc/*bean*.xml" }; // good expression 必须使用确切的路径
String[] paths = {"com/sample/Ioc/*bean*.xml" }; // 与上面相同的表示式，必须使用确切的路径
String[] paths = {"/WEB-INF/*-context.xml"}; // 与上面相同的表示式，必须使用确切的路径


============================
深入学习Java机制：
============================
1.java.lang.ClassLoader 学习他的很多知识内容。

  classloader四种类型： 
  Bootstrap classloader(null), 
  Extended classloader(Launcher$ExtClassLoader.class),
  System classLoader(Launcher$AppClassLoader.class)
  URLClassLoader (自定义的classloader)
  
  classloader导入三个class的方法：
  (1)在classpath中找class文件：
      Normally, the Java virtual machine loads classes from the local file
      system in a platform-dependent manner. For example, on UNIX systems, the
      virtual machine loads classes from the directory defined by the
      <tt>CLASSPATH</tt> environment variable.
  (2)找到byte[]数据,构造class文件,使用方法defineClass()：
      However, some classes may not originate from a file; they may originate
      from other sources, such as the network, or they could be constructed by an
      application.  The method {@link #defineClass(String, byte[], int, int) 
      <tt>defineClass</tt>} converts an array of bytes into an instance of class 
      <tt>Class</tt>. Instances of this newly defined class can be created using 
      {@link Class#newInstance <tt>Class.newInstance</tt>}.
  (3)一个class引用另外一个类,使用loadClass()方法导入需要的class:
      The methods and constructors of objects created by a class loader may
      reference other classes.  To determine the class(es) referred to, the Java
      virtual machine invokes the {@link #loadClass <tt>loadClass</tt>} method of
      the class loader that originally created the class.
   
   一些重要的方法：
   InputStream classLoader.getResourceAsStream(String name)
   InputStream getSystemResourceAsStream(String name)
   URL getResource(String name)
   URL getSystemResource(String name)
   Enumeration getResources(String name)
   Enumeration getSystemResources(String name)
      
      
2.java.lang.Runtime.

3.java.lang.System 

4.java.lang.Class
  Class forName(String className);