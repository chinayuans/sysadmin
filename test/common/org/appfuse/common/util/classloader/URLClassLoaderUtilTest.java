package org.appfuse.common.util.classloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

import junit.framework.TestCase;

public class URLClassLoaderUtilTest extends TestCase {

	/*
	 * Test method for 'org.appfuse.common.util.classloader.URLClassLoaderUtil.getInstance(String[], String)'
	 */
	public void testGetInstance() throws Exception {
		String[] jarsLocation= new String[] {"lib/dblib/mysql-driver/mysql-connector-java-3.0.14-production-bin.jar"};
        String className="com.mysql.jdbc.Driver";
        Driver validateDriver =(Driver)URLClassLoaderUtil.getInstance(jarsLocation,className);
        
        Properties info = new Properties();
        info.put("user", "root");
        info.put("password", "");

        String databaseName = "sysadmin"; //$NON-NLS-1$
        String url = "jdbc:mysql://localhost/" + databaseName;
        Connection conn = validateDriver.connect(url, info);        
        conn.close();
	}
	
	public void testURLClassLoaderUsingLoadClassMethod() throws Exception {
		String jarsLocation="lib/dblib/mysql-driver/mysql-connector-java-3.0.14-production-bin.jar";
        URL[] _locators = new URL[] { new URL("file:" +jarsLocation) };
        URLClassLoader ucl = new URLClassLoader(_locators);

        String driver = "com.mysql.jdbc.Driver";
        
        //special method.---------URLClassLoader.loadClass()
        Class driverClass = ucl.loadClass(driver);
        
        Driver validateDriver = (Driver) driverClass.newInstance();
        System.out.println("driver class is loaded by "+driverClass.getClassLoader());
        
        Properties info = new Properties();
        info.put("user", "root");
        info.put("password", "");

        String databaseName = "sysadmin"; //$NON-NLS-1$
        String url = "jdbc:mysql://localhost/" + databaseName;
        Connection conn = validateDriver.connect(url, info);        
        conn.close();
        
        System.out.println("URLClassLoader Id:"+ucl);
        System.out.println("SystemClassLoader Id:"+ucl.getParent());
        System.out.println("ExtClassLoader Id:"+ucl.getParent().getParent());
        System.out.println("BootStrapClassLoader Id:"+ucl.getParent().getParent().getParent());
	}
	
	public void testURLClassLoaderUsingForNameMethod() throws Exception {
		String jarsLocation="lib/dblib/mysql-driver/mysql-connector-java-3.0.14-production-bin.jar";
        URL[] _locators = new URL[] { new URL("file:" +jarsLocation) };
        URLClassLoader ucl = new URLClassLoader(_locators);

        String driver = "com.mysql.jdbc.Driver";
        
        //special method.---------Class.forName
        Class driverClass = Class.forName(driver,false,ucl);
        
        
        Driver validateDriver = (Driver) driverClass.newInstance();
        System.out.println("driver class is loaded by "+driverClass.getClassLoader());
        
        Properties info = new Properties();
        info.put("user", "root");
        info.put("password", "");

        String databaseName = "sysadmin"; //$NON-NLS-1$
        String url = "jdbc:mysql://localhost/" + databaseName;
        Connection conn = validateDriver.connect(url, info);        
        conn.close();
        
        System.out.println("URLClassLoader Id:"+ucl);
        System.out.println("SystemClassLoader Id:"+ucl.getParent());
        System.out.println("ExtClassLoader Id:"+ucl.getParent().getParent());
        System.out.println("BootStrapClassLoader Id:"+ucl.getParent().getParent().getParent());
	}
	
	
	/**
	 * class.forName(1,2,3) class.forName(1) :
	 * �҂��õ����½YՓ:������ʹ�õ���new ��a��ĳe�Č��w������ʹ��ֻ��һ��������
	 * forName()�������Ȳ����[���ˡ��d��e+�����o�B��ʼ���^�K���Ą�������ʹ�þ�������������
	 * forName()�����r������ڶ��������o������false�����N��ֻ������e�d�����d��ԓe������
	 * ���������o�B��ʼ���^�K��ֻ�еȵ�������ʽ��һ�Ό��w��ĳ��e�r���o�B��ʼ���^�K�ŕ�����
	 * �á�
	 * ֱ��ʹ��ClassLoader e��loadClass()�������d��e��ֻ����e�d��ӛ���w��
	 * �K��������ԓe���o�B��ʼ���^�K������횵ȵ���һ�Ό��w��ԓe�r��ԓe���o�B��ʼ���^�K�ŕ������á�
	 * �@�N�����cʹ��Class e��forName()�����r���ڶ�����������false�׺�����ͬ�ĽY����
	 */	
	
	/**
	 * ʹ���߳��������������, ������ִ���߳���, ����˫��ί�ɼ�����ģʽ, ʹ���߳�����������������������.
	 * ���͵�������, ͨ���߳������������ص�������jndiʵ��, ����������˫��ί��.
	 * �󲿷�java app������(jboss, tomcat..)Ҳ�ǲ���contextClassLoader������web����
	 * ��Class.forName������:
	 * Class.forNameʹ�õ��Ǳ������ߵ�����������������.
	 * ��������, ֤����java��������е����ƿռ���Ψһ��, �����໥����.
	 * ����һ�������, ��֤ͬһ�������������������඼���ɵ�ǰ���������������ص�.
	 */
	public void testContextClassLoader() throws Exception {
		String jarsLocation="lib/dblib/mysql-driver/mysql-connector-java-3.0.14-production-bin.jar";
        URL[] _locators = new URL[] { new URL("file:" +jarsLocation) };
        URLClassLoader uCL = new URLClassLoader(_locators);

        String driver = "com.mysql.jdbc.Driver";
        
//      context classloader 1
        ClassLoader contextCL=Thread.currentThread().getContextClassLoader();
        System.out.println("current contextClassLoader(1):"+Thread.currentThread().getContextClassLoader());
        
//      context classloader 2
        Thread.currentThread().setContextClassLoader(uCL);
        System.out.println("current contextClassLoader(2):"+Thread.currentThread().getContextClassLoader());
        
        //�������д��붼���ԡ�
        //Class driverClass = Class.forName(driver,false,Thread.currentThread().getContextClassLoader());
        
        Class driverClass=Thread.currentThread().getContextClassLoader().loadClass(driver);
        
        Driver validateDriver = (Driver) driverClass.newInstance();
        System.out.println("driver class is loaded by "+driverClass.getClassLoader());
        
        Properties info = new Properties();
        info.put("user", "root");
        info.put("password", "");

        String databaseName = "sysadmin"; //$NON-NLS-1$
        String url = "jdbc:mysql://localhost/" + databaseName;
        Connection conn = validateDriver.connect(url, info);        
        conn.close();
        
        System.out.println("Connection class name:"+conn.getClass().getName());
        System.out.println("Connection class's classloader:"+conn.getClass().getClassLoader());
        
        LaunchJar jar=new LaunchJar();
        System.out.println("LaunchJar class's classloader:"+jar.getClass().getClassLoader());
        
//      context classloader 3
        Thread.currentThread().setContextClassLoader(contextCL);
        System.out.println("current contextClassLoader(3):"+Thread.currentThread().getContextClassLoader());
	}
	
	public void testURLCLLoadLibrary() throws Exception {
		String jarsLocation="lib/dblib/mysql-driver/mysql-connector-java-3.0.14-production-bin.jar";
        URL[] _locators = new URL[] { new URL("file:" +jarsLocation) };
        URLClassLoader ucl = new URLClassLoader(_locators);
	}
}
