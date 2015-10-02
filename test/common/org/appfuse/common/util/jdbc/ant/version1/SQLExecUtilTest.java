package org.appfuse.common.util.jdbc.ant.version1;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

import org.appfuse.common.util.jdbc.ant.version1.SQLExecUtil;

import junit.framework.TestCase;

public class SQLExecUtilTest extends TestCase {

	private SQLExecUtil sqlExecUtil;
	
	// some database keys
	public final static int NULL = 0;

	public final static int ORACLE = 1;

	public final static int MYSQL = 2;

	protected void setUp() throws Exception {
		super.setUp();
		sqlExecUtil = new SQLExecUtil();
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
		sqlExecUtil.executeSQL(true,false,true);
		
		sqlExecUtil.setSqlText("select * from t_user");
		sqlExecUtil.executeSQL(true,false,true);
	}
	
	public void testSetPath() {
		sqlExecUtil.setDriver("com.mysql.jdbc.Driver");
		sqlExecUtil.setUser("root");
		sqlExecUtil.setPassword("");
		sqlExecUtil.setUrl("jdbc:mysql://localhost/sysadmin?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=gb2312");
		sqlExecUtil.setSqlText("select * from t_role");
		String drivePath="/D:/work/eclipse3.1-work/work/sysadmin/lib/dblib/mysql-driver/mysql-connector-java-3.0.14-production-bin.jar";
		sqlExecUtil.setPath(drivePath);
		sqlExecUtil.executeSQL(true,true,true);		
	}
	
    public void testNull() throws Exception {
        selectDbType(NULL);
        sqlExecUtil.executeSQL(true,true,true);
        System.out.println("driverpath:"+sqlExecUtil.getPath());
    }

    public void testOracle(){
    	selectDbType(ORACLE);
    	sqlExecUtil.executeSQL(true,false,true);
    	System.out.println("driverpath:"+sqlExecUtil.getPath());
    }

   
    public void testMySQL(){
    	selectDbType(MYSQL);
    	sqlExecUtil.executeSQL(true,false,true);
    	System.out.println("driverpath:"+sqlExecUtil.getPath());
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
