package org.appfuse.common.util.jdbc.java;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

import junit.framework.TestCase;


public class JDBCTaskTest extends TestCase {

	private JDBCTask jdbcTask;
		
	// some database keys
	public final static int NULL = 0;

	public final static int ORACLE = 1;

	public final static int MYSQL = 2;
		
    public JDBCTaskTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
		super.setUp();
		jdbcTask = new JDBCTask();
	}

    protected void tearDown() throws Exception {
    }
    
    public void testJDBCTask() throws Exception {
        String profilePath="C:/WASX/profiles/WPSProfile";
        String was_install_root="C:/WASX";
        String dbName="WPRCSDB";
        
        
        jdbcTask.setDriver("org.apache.derby.jdbc.EmbeddedDriver");
        jdbcTask.setUserid("app");
        jdbcTask.setPassword("app");
        
        String derbyDbName=profilePath+"/databases/"+dbName;
        jdbcTask.setUrl("jdbc:derby:"+derbyDbName);
        String jdbcDriverClasspath=was_install_root+"/derby/lib/derby.jar";
        jdbcTask.setClasspath(new String[]{jdbcDriverClasspath});
        
        jdbcTask.setCaching(true);
        jdbcTask.setPrint(true);

        jdbcTask.setOnerror("continue");
        
        
        /**
         * 1.run sql from  .addFileset()
         * 2.run sqls from .setSQLText()
         * 3.run sqls from .setSrc
         */
        jdbcTask.addFileset(new File("E:/eclipse-opensource/practice-db/test2.sql"));
        jdbcTask.addFileset(new File("E:/eclipse-opensource/practice-db/test3.sql"));
        jdbcTask.setSQLText("create table temp (a char(1))");
        jdbcTask.setSrc(new File("E:/eclipse-opensource/practice-db/test1.sql"));
        
        jdbcTask.setAutocommit(true);
        jdbcTask.execute(); 
        System.out.println(jdbcTask.getLog());
    }

	/*
	 * Test method for
	 * 'org.appfuse.common.util.jdbc.SQLExecUtil.executeSQL(boolean, boolean)'
	 */
	public void testExecuteSQL() {
		jdbcTask.setDriver("com.mysql.jdbc.Driver");
		jdbcTask.setUserid("root");
		jdbcTask.setPassword("");
		jdbcTask.setUrl("jdbc:mysql://localhost/sysadmin?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=gb2312");
		jdbcTask.setSQLText("select * from t_role");
		jdbcTask.execute();
		
		jdbcTask.setSQLText("select * from t_user");
		jdbcTask.execute();
		System.out.println(jdbcTask.getLog());
	}
	
	public void testSetClasspath() {
		jdbcTask.setDriver("com.mysql.jdbc.Driver");
		jdbcTask.setUserid("root");
		jdbcTask.setPassword("");
		jdbcTask.setUrl("jdbc:mysql://localhost/sysadmin?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=gb2312");
		jdbcTask.setSQLText("select * from t_role");
		String drivePath="/D:/work/eclipse3.1-work/work/sysadmin/lib/dblib/mysql-driver/mysql-connector-java-3.0.14-production-bin.jar";
		jdbcTask.setClasspath(new String[]{drivePath});
		jdbcTask.execute();	
		System.out.println(jdbcTask.getLog());
	}
	
    public void testNull() throws Exception {
        selectDbType(NULL);
        jdbcTask.execute();
        System.out.println("driverpath:"+jdbcTask.getClasspath());
    }

    public void testOracle(){
    	selectDbType(ORACLE);
    	jdbcTask.execute();
    	System.out.println("driverpath:"+jdbcTask.getClasspath());
    }

   
    public void testMySQL(){
    	selectDbType(MYSQL);
    	jdbcTask.execute();
    	System.out.println("driverpath:"+jdbcTask.getClasspath());
    }


	private void selectDbType(int database) {

		switch (database) {
		case ORACLE:
			jdbcTask.setDriver("oracle.jdbc.driver.OracleDriver");
			jdbcTask.setUserid("test");
			jdbcTask.setPassword("test");
			jdbcTask.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
			break;
		case MYSQL:
			jdbcTask.setDriver("com.mysql.jdbc.Driver");
			jdbcTask.setUserid("root");
			jdbcTask.setPassword("");
			jdbcTask.setUrl("jdbc:mysql://localhost/sysadmin?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=gb2312");
			break;
		case NULL:
		default:
			jdbcTask.setDriver(NULL_DRIVER);
			jdbcTask.setUserid("test");
			jdbcTask.setPassword("test");
			jdbcTask.setUrl("jdbc:database://hostname:port/name");
		}
		
		jdbcTask.setSQLText("create table OOME_TEST(X INTEGER NOT NULL);\ndrop table if exists OOME_TEST;");
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
