/*BEGIN OCO COPYRIGHT
 *************************************************************************
 *
 * IBM Confidential
 * OCO Source Materials
 * 5724-L01, 5655-N53, 5724-I82, 5655-R15
 * (C) Copyright IBM Corporation 2006.
 * The source code for this program is not published or otherwise
 * divested of its trade secrets, irrespective of what has been
 * deposited with the U.S. Copyright Office.
 *
 **************************************************************************
 *END OCO COPYRIGHT
 */
package org.appfuse.common.util.ant;


import java.util.Properties;

import org.apache.tools.ant.Project;



public class CommonDBTargetTestCase
    extends BuildFileTestBase
{

    public CommonDBTargetTestCase(String name)
    {
        super(name);
    }


    //clear up all resources
    protected void setUp()
        throws Exception
    {
        super.setUp();
        
        Properties props=new Properties();
        String WAS_HOME=props.getProperty("WAS_HOME");
        String WBI_HOME=props.getProperty("WAS_HOME");
        String wbi_install_root=props.getProperty("wbi.install.root");
        String was_install_root=props.getProperty("WAS_HOME");
        String cellName=props.getProperty("cellName");
        String nodeName=props.getProperty("nodeName");
        String templatePath=props.getProperty("templatePath");
        String profilePath=props.getProperty("profilePath");
        String profileName=props.getProperty("profileName");
        
        String dbCreateNew=props.getProperty("dbCreateNew");
        String dbDelayConfig=props.getProperty("dbDelayConfig");
        String dbType=props.getProperty("dbType");
        String dbDriverType=props.getProperty("dbDriverType");
        String dbName=props.getProperty("dbName");        
        String dbLocation=props.getProperty("dbLocation");
        String dbUserId=props.getProperty("dbUserId");
        String dbPassword=props.getProperty("dbPassword");
        
        String dbServerPort=props.getProperty("dbServerPort");
        String dbHostName=props.getProperty("dbHostName");
        String dbServerName=props.getProperty("dbServerName");
        String dbInstance=props.getProperty("dbInstance");
        String dbSchemaName=props.getProperty("dbSchemaName");
        String dbJDBCClasspath=props.getProperty("dbJDBCClasspath");
        String dbConnectionLocation=props.getProperty("dbConnectionLocation");
        String dbStorageGroup=props.getProperty("dbStorageGroup");
        
        
        
        String BuildFile = WAS_HOME+"\\profileTemplates\\default.wbiserver\\actions\\commonDBUtility.ant";

        //set log level. Project.MSG_DEBUG ==highest level
        configureProject(BuildFile, Project.MSG_INFO,true);

        /**
         * set common properties.
         */
        project.setUserProperty("WS_CMT_LOG_HOME", WAS_HOME+"/logs/manageprofiles");
        project.setUserProperty("TEMP_DIR", WAS_HOME+"/logs/manageprofiles/"+profileName);
        project.setUserProperty("WAS_HOME", WAS_HOME);
        project.setUserProperty("WBI_HOME", WBI_HOME);
        project.setUserProperty("wbi.install.root", wbi_install_root);
        project.setUserProperty("was.install.root", was_install_root);
        project.setUserProperty("cellName", cellName);
        project.setUserProperty("nodeName", nodeName);
        project.setUserProperty("templatePath",templatePath);
        project.setUserProperty("profilePath", profilePath);
        project.setUserProperty("profileName", profileName);

        /**
         * set db configuration.
         */
        project.setUserProperty("common.dbCreateNew", "true");
        project.setUserProperty("common.dbDelayConfig", "false");
        project.setUserProperty("common.dbType", dbType);
        project.setUserProperty("common.dbDriverType", dbDriverType);
        project.setUserProperty("common.dbName", dbName);
        project.setUserProperty("common.dbUserId", dbUserId);
        project.setUserProperty("common.dbPassword", dbPassword);
        project.setUserProperty("common.dbHostName", dbHostName);
        project.setUserProperty("common.dbServerPort", dbServerPort);
        project.setUserProperty("common.dbLocation", dbLocation);
        project.setUserProperty("common.dbServerName", dbServerName);
        project.setUserProperty("common.dbJDBCClasspath", dbJDBCClasspath);        
        project.setUserProperty("common.dbInstance", dbInstance);
        project.setUserProperty("common.dbSchemaName", dbSchemaName);
        project.setUserProperty("common.dbStorageGroup", dbStorageGroup);
        project.setUserProperty("common.dbConnectionLocation", dbConnectionLocation);
    }

    //clear up all resources
    protected void tearDown()
        throws Exception
    {
        super.tearDown();
    }


    public void testCase1()
        throws Exception
    {
        //create all resource
        
        /**
         * run "createDB" target                 --->create a new database
         */
        executeTarget("createDB");
        
        
        
        /**
         * run "checkCriticalConnection" target  --->check database connection
         */
        expectLogContaining("checkCriticalConnection", "Check Database connection: (true / error info): true");
        
        /**
         * run "createTable" target              --->create some tables (execute some sqls)
         */
        //project.setUserProperty("sqlScriptPath.default", "");
        project.setUserProperty("sqlScriptName.default", "createTable_CommonDB.sql");
        expectLogContaining("createTable", "SQL statements executed successfully");
     
        /**
         * run "createVariables.common" target   --->create some WAS Variables
         */
        project.setUserProperty("common.scope.level", "node");//node---standalone,cell-dmgr
        
        executeTarget("createVariables.common");
        
        /**
         * run "createJDBCProvider" target.      --->create a JDBCProvider
         */
        project.setUserProperty("common.scope.level", "node");//node---standalone,cell-dmgr
        executeTarget("createJDBCProvider");

        /**
         * run "createDataSource" target.        --->create a DataSource
         */
        project.setUserProperty("common.scope.level", "node");//node---standalone,cell-dmgr
        project.setUserProperty("jndiName", "jdbc/WPSDB");//value: node  or cell.
        executeTarget("createDataSource");
        
        /**
         * run "removeDataSource" target.        --->remove a DataSource
         */
        executeTarget("removeDataSource");
        
        /**
         * run "removeJDBCProvider" target.     --->remove a JDBCProvider
         */
        executeTarget("removeJDBCProvider");
        
        /**
         * run "removeVariables.common" target. --->remove a WAS Variable
         */
        executeTarget("removeVariables.common");
        
        
        /**
         * run "removeDB" target                 --->remove a new database
         */
//        expectLogContaining("removeDB","remove database sucessfully!");
        
        
        
//        System.out.println(getFullLog());
    }

}
