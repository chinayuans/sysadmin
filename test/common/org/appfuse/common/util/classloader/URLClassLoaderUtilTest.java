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
	 * 我們得到以下結論:不管您使用的是new 來產生某類別的實體、或是使用只有一個參數的
	 * forName()方法，內部都隱含了”載入類別+呼叫靜態初始化區塊”的動作。而使用具有三個參數的
	 * forName()方法時，如果第二個參數給定的是false，那麼就只會命令類別載入器載入該類別，但不
	 * 會叫用其靜態初始化區塊，只有等到整個程式第一次實體化某個類別時，靜態初始化區塊才會被叫
	 * 用。
	 * 直接使用ClassLoader 類別的loadClass()方法來載入類別，只會把類別載入記憶體，
	 * 並不會叫用該類別的靜態初始化區塊，而必須等到第一次實體化該類別時，該類別的靜態初始化區塊才會被叫用。
	 * 這種情形與使用Class 類別的forName()方法時，第二個參數傳入false幾乎是相同的結果。
	 */	
	
	/**
	 * 使用线程上下文类加载器, 可以在执行线程中, 抛弃双亲委派加载链模式, 使用线程上下文里的类加载器加载类.
	 * 典型的例子有, 通过线程上下文来加载第三方库jndi实现, 而不依赖于双亲委派.
	 * 大部分java app服务器(jboss, tomcat..)也是采用contextClassLoader来处理web服务。
	 * 用Class.forName加载类:
	 * Class.forName使用的是被调用者的类加载器来加载类的.
	 * 这种特性, 证明了java类加载器中的名称空间是唯一的, 不会相互干扰.
	 * 即在一般情况下, 保证同一个类中所关联的其他类都是由当前类的类加载器所加载的.
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
        
        //下面两行代码都可以。
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
