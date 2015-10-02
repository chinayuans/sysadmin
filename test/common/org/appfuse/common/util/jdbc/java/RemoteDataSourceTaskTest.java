package org.appfuse.common.util.jdbc.java;

import java.io.File;

import junit.framework.TestCase;

public class RemoteDataSourceTaskTest extends TestCase {

	public RemoteDataSourceTaskTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testRemoteDatasourceTask() throws Exception {
	        
        RemoteDataSourceTask jdbcTask = new RemoteDataSourceTask();
        
        jdbcTask.setProvider_Url("iiop://9.125.63.216:2809");////9.125.63.216   localhost 127.0.0.1
        jdbcTask.setFactory_Initial("com.ibm.websphere.naming.WsnInitialContextFactory");
        
        //cell/nodes/WPSNode/servers/server1/jdbc/WPSDB
        //cell/nodes/wbi-recoveryNode01/servers/server1/jdbc/WPSDB
//      jdbcTask.setJndiName("cell/nodes/wbi-recoveryNode01/servers/server1/jdbc/WPSDB");
        jdbcTask.setJndiName("test");       
        jdbcTask.setPrint(true);
        jdbcTask.setOnerror("continue");
        
        
        /**
         * 1.run sql from  .addFileset()
         * 2.run sqls from .setSQLText()
         * 3.run sqls from .setSrc
         */
        jdbcTask.addFileset(new File("E:/eclipse-opensource/practice-jdbc/test2.sql"));
        jdbcTask.addFileset(new File("E:/eclipse-opensource/practice-jdbc/test3.sql"));
        jdbcTask.setSQLText("create table temp (a char(1))");
        jdbcTask.setSrc(new File("E:/eclipse-opensource/practice-jdbc/test1.sql"));
        
        jdbcTask.setAutocommit(true);
        jdbcTask.execute(); 
        System.out.println(jdbcTask.getLog());
	}
}
