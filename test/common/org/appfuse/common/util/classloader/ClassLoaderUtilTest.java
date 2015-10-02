package org.appfuse.common.util.classloader;

import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

import junit.framework.TestCase;

public class ClassLoaderUtilTest extends TestCase {

	/*
	 * Test method for 'org.appfuse.common.util.classloader.ClassLoaderUtil.loadClass(String)'
	 */
	public void testLoadClass() {
		ClassLoaderUtil.loadClass("java.util.Vector");
	}

	/*
	 * Test method for 'org.appfuse.common.util.classloader.ClassLoaderUtil.getClassLoader()'
	 */
	public void testGetClassLoader() {
		System.out.println(ClassLoaderUtil.getClassLoader());
	}

	/*
	 * Test method for 'org.appfuse.common.util.classloader.ClassLoaderUtil.getResource(String)'
	 */
	public void testGetResource() {
		ClassLoaderUtil.getResource("/org/../../web.xml");
		ClassLoaderUtil.getResource("/../web.xml");
		ClassLoaderUtil.getResource("../web.xml");
		
		ClassLoaderUtil.getResource("/org/../hibernate.properties");
		ClassLoaderUtil.getResource("/hibernate.properties");
	}

	/*
	 * Test method for 'org.appfuse.common.util.classloader.ClassLoaderUtil.getResourceAsStream(String)'
	 */
	public void testGetResourceAsStream() {
//		ClassLoaderUtil.getResourceAsStream("/org/../../web.xml");
//		ClassLoaderUtil.getResourceAsStream("/../web.xml");
//		ClassLoaderUtil.getResourceAsStream("../web.xml");
		ClassLoaderUtil.getResourceAsStream("/hibernate.properties");
	}

	/*
	 * Test method for 'org.appfuse.common.util.classloader.ClassLoaderUtil.getProperties(String)'
	 */
	public void testGetProperties() {
		Properties props=ClassLoaderUtil.getProperties("/org/../log4j.properties");
		props.list(System.out);
		
		props=ClassLoaderUtil.getProperties("/log4j.properties");
		props.list(System.out);
	}

	/*
	 * Test method for 'org.appfuse.common.util.classloader.ClassLoaderUtil.getAbsolutePathOfClassLoaderClassPath()'
	 */
	public void testGetAbsolutePathOfClassLoaderClassPath() {
		System.out.println(ClassLoaderUtil.getAbsolutePathOfClassLoaderClassPath());
		
	}

	public void testClassLoaderClasspath() throws Exception {
	 	System.out.println("BootStrap classloader:"+ClassLoaderUtil.getBootStrapClassLoader());
        System.out.println("System classloader:"+ClassLoaderUtil.getSystemClassLoader());
        System.out.println("Ext classloader:"+ClassLoaderUtil.getExtClassLoader());
        
        System.out.println("--------print various classpath-------------");
        
        System.out.println(ClassLoaderUtil.getExtClassLoaderClasspath());
        System.out.println(ClassLoaderUtil.getSystemClassLoaderClasspath());
        
        System.out.println("--------add some files to different classpath-------------");
        
        String file="lib/dblib/db2-driver/db2java.zip";
        ClassLoaderUtil.addFileToSystemClassLoaderClasspath(file);
        System.out.println(ClassLoaderUtil.getSystemClassLoaderClasspath());
        
        ClassLoaderUtil.addFileToExtClassLoaderClasspath(file);
        System.out.println(ClassLoaderUtil.getExtClassLoaderClasspath());
        
        System.out.println("--------add all jars in a directory to different classpath-------------");
        String dir="lib/dblib/db2-driver";
        ClassLoaderUtil.addAllJarsToSystemClassLoaderClasspath(dir);
        System.out.println(ClassLoaderUtil.getSystemClassLoaderClasspath());
        
        ClassLoaderUtil.addAllJarsToExtClassLoaderClasspath(dir);
        System.out.println(ClassLoaderUtil.getExtClassLoaderClasspath());
        
        System.out.println("--------add all zips in a directory to different classpath-------------");
        String dir_filtered="lib/dblib/db2-driver";
        String S_ZIP_FILE_PATTERN = "^.*\\.zip$";
        
        ClassLoaderUtil.addAllFilesToSystemClassLoaderClasspathWithReg(dir_filtered,S_ZIP_FILE_PATTERN);
        System.out.println(ClassLoaderUtil.getSystemClassLoaderClasspath());
        
        ClassLoaderUtil.addAllFilesToExtClassLoaderClasspathWithReg(dir_filtered,S_ZIP_FILE_PATTERN);
        System.out.println(ClassLoaderUtil.getExtClassLoaderClasspath());
	}
	
	public void testAddFileToExtClassLoaderClasspath() throws Exception {
		String jarsLocation="D:/work/eclipse3.1-work/work/sysadmin/lib/dblib/mysql-driver/mysql-connector-java-3.0.14-production-bin.jar";
        ClassLoaderUtil.addFileToExtClassLoaderClasspath(jarsLocation);
        
        String driver = "com.mysql.jdbc.Driver";
        Class driverClass = Class.forName(driver);
        Driver validateDriver = (Driver) driverClass.newInstance();
        System.out.println("driver class is loaded by "+driverClass.getClassLoader());
        Properties info = new Properties();
        info.put("user", "root");
        info.put("password", "");
        String databaseName = "sysadmin"; //$NON-NLS-1$
        String url = "jdbc:mysql://localhost/" + databaseName;
        Connection conn = validateDriver.connect(url, info);        
        conn.close();
	}
	
	public void testAddFileToSystemClassLoaderClasspath() throws Exception {
		String jarsLocation="D:/work/eclipse3.1-work/work/sysadmin/lib/dblib/mysql-driver/mysql-connector-java-3.0.14-production-bin.jar";
        ClassLoaderUtil.addFileToSystemClassLoaderClasspath(jarsLocation);
        
        String driver = "com.mysql.jdbc.Driver";
        Class driverClass = Class.forName(driver);
        Driver validateDriver = (Driver) driverClass.newInstance();
        System.out.println("driver class is loaded by "+driverClass.getClassLoader());
        Properties info = new Properties();
        info.put("user", "root");
        info.put("password", "");
        String databaseName = "sysadmin"; //$NON-NLS-1$
        String url = "jdbc:mysql://localhost/" + databaseName;
        Connection conn = validateDriver.connect(url, info);        
        conn.close();
	}
}
