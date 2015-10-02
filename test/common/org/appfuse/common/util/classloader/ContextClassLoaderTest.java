package org.appfuse.common.util.classloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

import junit.framework.TestCase;


public class ContextClassLoaderTest
    extends TestCase
{

    public ContextClassLoaderTest(String name)
    {
        super(name);
    }


    public final void testGetAntClassLoader() throws Exception
    {
    	
    	//context classloader 1
        ClassLoader originalCL=Thread.currentThread().getContextClassLoader();
        System.out.println("current contextClassLoader(1):"+originalCL);
        
    	String jarsLocation="lib/dblib/mysql-driver/mysql-connector-java-3.0.14-production-bin.jar";
        URL[] _locators = new URL[] { new URL("file:" +jarsLocation) };
        URLClassLoader urlCL = new URLClassLoader(_locators);
        String driver = "com.mysql.jdbc.Driver";
        
        //context classloader 2
        Thread.currentThread().setContextClassLoader(urlCL);
        ClassLoader updatedCL=Thread.currentThread().getContextClassLoader();
        System.out.println("current contextClassLoader(2):"+updatedCL);
        
        //这是一个非常正确得用法。
        Class driverClass=updatedCL.loadClass(driver);
        
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

        //context classloader 3
        Thread.currentThread().setContextClassLoader(originalCL);
        System.out.println("current contextClassLoader(3):"+originalCL);
    }
}
