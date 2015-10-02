package org.appfuse.common.util.classloader;

import junit.framework.TestCase;


public class AntClassLoaderUtilTest
    extends TestCase
{

    public AntClassLoaderUtilTest(String name)
    {
        super(name);
    }


    public final void testGetAntClassLoader()
    {
        String[] directorys=new String[2];
        directorys[0]="lib/dblib/mysql-driver";
        directorys[1]="lib/dblib/db2-driver";
        
        ClassLoader antClassLoader=AntClassLoaderUtil.getAntClassLoader(directorys);
        
        //print AntClassLoader hierarchy:
        System.out.println("AntClassLoader Id:"+antClassLoader);
        System.out.println("SystemClassLoader Id:"+antClassLoader.getParent());
        System.out.println("ExtClassLoader Id:"+antClassLoader.getParent().getParent());
        System.out.println("BootStrapClassLoader Id:"+antClassLoader.getParent().getParent().getParent());
        
        String mysqlDriver = "com.mysql.jdbc.Driver";
        try {
            antClassLoader.loadClass(mysqlDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
        }
    }
}
