http://127.0.0.1:8080/sysadmin

��Ҫ��ʾ:һ��Ҫע��汾����.
�涨����: 
struts1.2.9+spring1.2RC+hibernate2.1.7
J2ee1.3+JSTL1.0

һ��������Test-Driven Development��
spring�е�
classpath:  Ĭ�������Ϊ/web-inf/classes,/web-inf/lib ����Ŀ¼�£�
              ���Ҫָ��/web-infĿ¼�µľ������ݣ���������ʹ�ã�������ʹ��classpath.
            /WEB-INF/applicationContext*.xml��
example������
/WEB-INF/applicationContext-*.xml    �� ��ȷ�ľ���·����
classpath*:META-INF/applicationContext-*.xml 
����/classesĿ¼�µ�λ�ã�����/lib�о���jar����λ�ã�
  


����Ϊ����������������ϸ������
==================================
DAO�㣺
==================================
0.ͨ������UserDAOTest��ʼ.
1.���뽨��BaseDAOTest.ͨ�������õ���ʼ������
2.ͨ��testXXX������Ҫ����Model�е�User,UserDAO
3.����User���ֵò���BaseObject.
4.����UserDAO�ӿ�,�ֵò���DAO�ӿڡ�
5.ӳ���ļ�user.hbm.xml�����ɡ�
6.applicationContext.xml�����ɡ�
   ��Ҫ����sessionFactory,transactionManager,dataSource
7.UserDAO��implementation->UserDAOHibernate
8.applicationContext.xml����д��
  UserDAO -----UserDAOHibernate.
9.׼�����ԡ�����ʹ��ant����eclipse��junit��
  ������ʹ��JUnit����UserDAOTest�����Ͽ��Կ��������

���˽׶�DAO��д����ˡ�
�����DAOtestcase,DAO,DAOImpl��

=================================
Service�㣺
=================================
10.дUserManagerTest��ʼ��
11.дBaseServiceTest�ࡣ
12.UserManagerTest--->�ӿ�UserManager.
13.����UserManager--->����UserManagerImpl��ʵ���ࡣ
14.applicationContext.xml����д��
   UserManagerImpl-----��ע��UserDAO�ӿ�
   UserManager---------��UserManagerImpl�����������װ��
���������������aop�����˼̳е�˼����ʵ�֡�
15.׼�����ԡ�����ʹ��ant����eclipse��junit��
    ������ʹ��JUnit����UserManagerTest�����Ͽ��Կ��������
ע�⣺���ϲ���Ϊintegration test.��Ϊ���ǽ�����DAO�����Ͻ��еĲ��ԡ�
     ���Բ���unit test��ʹ��EasyMock ���Խ��е�Ԫ���ԣ�����DAO�Ļ�����
     ��ΪEasyMock����α��DAO��
     
     
=================================
Web�㣺
=================================
ע�⣺������԰���Ҳ���֣�һ��Mock Testing(ģ�����)��
	����һ��ΪIn-Container Testing(�����ڲ��ԣ���
	��ʵ��servlet�����ڲ���).�����ֲ��Զ�����strutsTestCase������
    ���β��Բ���Mock Testing.
    Mock Testing------MockStrutsTestCase
    In-Container Testing----CatusStrutsTestCase
16.дUserActionTest����case��
17.����UserActionTest,�ɴ����ǿ��Եõ�struts-config.xml�е�<form-bean/>��<action-mapping/>
18.����UserActionTest�õ�UserAction��
19.����UserAction�е����ݵõ�messages.properties��
20.����UserActionʹ��springճ�ϼ���ǿ�����ã���UserAction��
   ע��service���UserManager���ṩ��Action����service��ķ���
   ͬʱ����applicationContext-web.xml�ļ����޸�struts-config��
   <controller/> <message-resources />���ݡ�

21.MockStrutsTestCase����Ϊ��
   /WEB-INF/struts-config.xml��
   /WEB-INF/web.xml,
   /WEB-INF/applicationContext-dao.xml,
   /WEB-INF/applicationContext-service.xml,
   /WEB-INF/applicationContext-web.xml
   /WEB-INF/classes/messages.properties
   
   ע�⣺ applicationContext-dao.xml��
        applicationContext-service.xml��
        applicationContext-web.xml��
        messages.properties����������"source folder"�е����ݱ���һ�¡�
        
22.׼�����ԡ�����ʹ��ant����eclipse��junit��
    ������ʹ��JUnit����UserActionTest�����Ͽ��Կ��������
    
    
ע�⣺��"source folder"Ϊtest�����棨test/web,test/service,test/dao��
	 ����һ��log4j.xml,ע��������log4j.xml�����ݲ�ͬ����ҪΪ���ǲ鿴��ͬ����ʾ���ݡ�
	 
�����е�build.xml��ʱû���ô�.�Ժ���иĽ�.	 
	 
============================
hibernate2��hibernate3������
============================
hibernate2:   net.sf.hibernate.
hibernate3:   org.hibernate.

============================
spring1.2.7֮���һЩ�仯��
============================
file: classpath: classpath*:
1.Must support fully qualified URLs, e.g. "file:C:/test.dat".
2.Must support classpath pseudo-URLs, e.g. "classpath:test.dat","classpath:**/test.dat".
3.Should support relative file paths, e.g. "WEB-INF/test.dat", "".
4.��㷺��֧�֣�"classpath*:META-INF/*-beans.xml" will find all 
  *-beans.xml files in the class path, be it in "classes" directories
  or in JAR files.
		
String[] paths = {"classpath*:**/bean*.xml" }; // ��jar�У�����classesĿ¼��ȥѰ����صĶ�����
String[] paths = {"classpath:/com/sample/Ioc/bean.xml" }; // ֻ��classpath��ȥѰ����صĶ�����
String[] paths = {"classpath:**/*bean.xml" }; // bad expression��
String[] paths = {"file:D:/**/bean*.xml" };    // good expression
String[] paths = {"/com/sample/Ioc/*bean*.xml" }; // good expression ����ʹ��ȷ�е�·��
String[] paths = {"com/sample/Ioc/*bean*.xml" }; // ��������ͬ�ı�ʾʽ������ʹ��ȷ�е�·��
String[] paths = {"/WEB-INF/*-context.xml"}; // ��������ͬ�ı�ʾʽ������ʹ��ȷ�е�·��


============================
����ѧϰJava���ƣ�
============================
1.java.lang.ClassLoader ѧϰ���ĺܶ�֪ʶ���ݡ�

  classloader�������ͣ� 
  Bootstrap classloader(null), 
  Extended classloader(Launcher$ExtClassLoader.class),
  System classLoader(Launcher$AppClassLoader.class)
  URLClassLoader (�Զ����classloader)
  
  classloader��������class�ķ�����
  (1)��classpath����class�ļ���
      Normally, the Java virtual machine loads classes from the local file
      system in a platform-dependent manner. For example, on UNIX systems, the
      virtual machine loads classes from the directory defined by the
      <tt>CLASSPATH</tt> environment variable.
  (2)�ҵ�byte[]����,����class�ļ�,ʹ�÷���defineClass()��
      However, some classes may not originate from a file; they may originate
      from other sources, such as the network, or they could be constructed by an
      application.  The method {@link #defineClass(String, byte[], int, int) 
      <tt>defineClass</tt>} converts an array of bytes into an instance of class 
      <tt>Class</tt>. Instances of this newly defined class can be created using 
      {@link Class#newInstance <tt>Class.newInstance</tt>}.
  (3)һ��class��������һ����,ʹ��loadClass()����������Ҫ��class:
      The methods and constructors of objects created by a class loader may
      reference other classes.  To determine the class(es) referred to, the Java
      virtual machine invokes the {@link #loadClass <tt>loadClass</tt>} method of
      the class loader that originally created the class.
   
   һЩ��Ҫ�ķ�����
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