
package org.appfuse.common.util.jdbc.ant.version2;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

import junit.framework.TestCase;

import org.appfuse.common.util.jdbc.ant.version2.SQLExecUtil;


public class SQLExecUtilTest extends TestCase {

	private SQLExecUtil sqlExecUtil;
		
	// some database keys
	public final static int NULL = 0;

	public final static int ORACLE = 1;

	public final static int MYSQL = 2;

	
    public SQLExecUtilTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
		super.setUp();
		sqlExecUtil = new SQLExecUtil();
	}

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSQLExecUtil() {
        String profilePath="C:/WASX/profiles/WPSProfile";
        String was_install_root="C:/WASX";
        String dbName="WPRCSDB";
        
        sqlExecUtil.setDriver("org.apache.derby.jdbc.EmbeddedDriver");
        sqlExecUtil.setUser("app");
        sqlExecUtil.setPassword("app");
        
        String derbyDbName=profilePath+"/databases/"+dbName;
        sqlExecUtil.setUrl("jdbc:derby:"+derbyDbName);
        String drivePath=was_install_root+"/derby/lib/derby.jar";
        sqlExecUtil.setClasspath(drivePath);
        
        sqlExecUtil.setCaching(true);
        sqlExecUtil.setPrint(true);
        sqlExecUtil.setThrowException(false);
        sqlExecUtil.setOnerror("continue");
        
//        sqlExecUtil.setSqlText("select * from test");
        sqlExecUtil.setSrc("E:/eclipse-opensource/practice-ant/test1.sql");
        
        
        //first execute .setSqlText() and execute all sql statements from .setSrc()
        sqlExecUtil.executeSQL(); 
        
        sqlExecUtil.setSrc("E:/eclipse-opensource/practice-ant/test2.sql");
        
        //first execute .setSqlText() and execute all sql statements from .setSrc()
        sqlExecUtil.executeSQL(); 
    }
    

	/*
	 * Test method for
	 * 'org.appfuse.common.util.jdbc.SQLExecUtil.executeSQL(boolean, boolean)'
	 */
	public void testExecuteSQL() {
		sqlExecUtil.setDriver("com.mysql.jdbc.Driver");
		sqlExecUtil.setUser("root");
		sqlExecUtil.setPassword("");
		sqlExecUtil.setUrl("jdbc:mysql://localhost/sysadmin?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=gb2312");
		sqlExecUtil.setSqlText("select * from t_role");
		sqlExecUtil.executeSQL();
		
		sqlExecUtil.setSqlText("select * from t_user");
		sqlExecUtil.executeSQL();
	}
	
	public void testSetClasspath() {
		sqlExecUtil.setDriver("com.mysql.jdbc.Driver");
		sqlExecUtil.setUser("root");
		sqlExecUtil.setPassword("");
		sqlExecUtil.setUrl("jdbc:mysql://localhost/sysadmin?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=gb2312");
		sqlExecUtil.setSqlText("select * from t_role");
		String drivePath="/D:/work/eclipse3.1-work/work/sysadmin/lib/dblib/mysql-driver/mysql-connector-java-3.0.14-production-bin.jar";
		sqlExecUtil.setClasspath(drivePath);
		sqlExecUtil.executeSQL();		
	}
	
    public void testNull() throws Exception {
        selectDbType(NULL);
        sqlExecUtil.executeSQL();
        System.out.println("driverpath:"+sqlExecUtil.getClasspath());
    }

    public void testOracle(){
    	selectDbType(ORACLE);
    	sqlExecUtil.executeSQL();
    	System.out.println("driverpath:"+sqlExecUtil.getClasspath());
    }

   
    public void testMySQL(){
    	selectDbType(MYSQL);
    	sqlExecUtil.executeSQL();
    	System.out.println("driverpath:"+sqlExecUtil.getClasspath());
    }


	private void selectDbType(int database) {

		switch (database) {
		case ORACLE:
			sqlExecUtil.setDriver("oracle.jdbc.driver.OracleDriver");
			sqlExecUtil.setUser("test");
			sqlExecUtil.setPassword("test");
			sqlExecUtil.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
			break;
		case MYSQL:
			sqlExecUtil.setDriver("com.mysql.jdbc.Driver");
			sqlExecUtil.setUser("root");
			sqlExecUtil.setPassword("");
			sqlExecUtil.setUrl("jdbc:mysql://localhost/sysadmin?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=gb2312");
			break;
		case NULL:
		default:
			sqlExecUtil.setDriver(NULL_DRIVER);
			sqlExecUtil.setUser("test");
			sqlExecUtil.setPassword("test");
			sqlExecUtil.setUrl("jdbc:database://hostname:port/name");
		}
		
		sqlExecUtil.setSqlText("create table OOME_TEST(X INTEGER NOT NULL);\ndrop table if exists OOME_TEST;");
	}
	
//	--- NULL JDBC driver just for simple test since there are no db driver
//	 available as a default in Ant :)

	    public final static String NULL_DRIVER = NullDriver.class.getName();

	    public static class NullDriver implements Driver {
	        public Connection connect(String url, Properties info)
	                throws SQLException {
	            return null;
	        }

	        public boolean acceptsURL(String url) throws SQLException {
	            return false;
	        }

	        public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
	                throws SQLException {
	            return new DriverPropertyInfo[0];
	        }

	        public int getMajorVersion() {
	            return 0;
	        }

	        public int getMinorVersion() {
	            return 0;
	        }

	        public boolean jdbcCompliant() {
	            return false;
	        }
	    }
}
