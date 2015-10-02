
package org.appfuse.common.util.ant;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;

import junit.framework.TestCase;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

/**
 * A BuildFileBaseTestCase is a TestCase which executes targets from an Ant buildfile
 * for testing.
 *
 * This class provides a number of utility methods for particular build file
 * tests which extend this class.
 *
 */
public abstract class BuildFileTestBase extends TestCase {

    /**
     * notes that :we can get it by getProject().
     */
    protected Project project;

    /**
     * only contains log information of logLevel specified by these log level: 
     * Project.MSG_INFO Project.MSG_WARN  Project.MSG_ERR
     * notes that :we can get it by getLog().
     */
    private StringBuffer logBuffer;
    
    /**
     * contains log information of logLevel specified by the method:
     * configureProject(String filename, int logLevel)
     * notes that :we can get it by getFullLog().
     */
    private StringBuffer fullLogBuffer;
    
    /**
     * contains buildException.
     * notes that :we can get it by getBuildException().
     */
    private BuildException buildException;

    
    /**
     * contains buildException.
     * notes that :we can get it by getBuildException().
     */
    private boolean consoleOutputted;

    /**
     *  Constructor for the BuildFileTest object
     *
     *@param  name string to pass up to TestCase constructor
     */
    public BuildFileTestBase(String name) {
        super(name);
    }

    /**
     * Assert that only the given message has been logged with a
     * priority &gt;= INFO when running the given target.
     */
    protected void expectLog(String target, String log) {
        executeTarget(target);
        String logWithPriority = getLog();
        assertEquals(log, logWithPriority);
    }

    /**
     * Assert that the given message has been logged with a priority
     * &gt;= INFO when running the given target.
     */
    protected void expectLogContaining(String target, String log) {
        executeTarget(target);
        String logWithPriority = getLog();
        assertLogContaining(log,logWithPriority);
    }

    /**
     * Assert that the given message has been logged with a priority
     * &gt;= DEBUG when running the given target.
     */
    protected void expectFullLog(String target, String log) {
        executeTarget(target);
        String fullLog = getFullLog();
        assertEquals(log, fullLog);
    }

    /**
     * Assert that the given message has been logged with a priority
     * &gt;= INFO when running the given target.
     */
    protected void expectFullLogContaining(String target, String log) {
        executeTarget(target);
        String fullLog = getFullLog();
        assertLogContaining(log,fullLog);
    }
    
    /**
     * Assert that the given substring is in the log messages
     */
    
    protected void assertLogContaining(String substring, String logType) {
        String realLog = logType;
        assertTrue("expecting log to contain \"" + substring + "\" log was \""
                   + realLog + "\"",
                   realLog.indexOf(substring) >= 0);
    }

   
    /**
     *  run a target, expect for any build exception
     *
     *@param  target target to run
     *@param  cause  information string to reader of report
     */
    protected void expectBuildException(String target, String cause) {
        expectBuildException(target, cause, null);
    }

    /**
     *  run a target, wait for a build exception
     *
     *@param  target target to run
     *@param  cause  information string to reader of report
     *@param  msg    the message value of the build exception we are waiting for
              set to null for any build exception to be valid
     */
    protected void expectBuildException(String target, String cause, String msg) {
        try {
            executeTarget(target);
        } catch (org.apache.tools.ant.BuildException ex) {
            buildException = ex;
            if ((null != msg) && (!ex.getMessage().equals(msg))) {
                fail("Should throw BuildException because '" + cause
                        + "' with message '" + msg
                        + "' (actual message '" + ex.getMessage() + "' instead)");
            }
            return;
        }
        fail("Should throw BuildException because: " + cause);
    }
    
    /**
     *  run a target, expect an exception string
     *  containing the substring we look for (case sensitive match)
     *
     *@param  target target to run
     *@param  cause  information string to reader of report
     *@param  contains  substring of the build exception to look for
     */
    protected void expectBuildExceptionContaining(String target, String cause, String contains) {
        try {
            executeTarget(target);
        } catch (org.apache.tools.ant.BuildException ex) {
            buildException = ex;
            if ((null != contains) && (ex.getMessage().indexOf(contains) == -1)) {
                fail("Should throw BuildException because '" + cause + "' with message containing '" + contains + "' (actual message '" + ex.getMessage() + "' instead)");
            }
            return;
        }
        fail("Should throw BuildException because: " + cause);
    }

   

    /**
     * run a target, expect an exception string containing the substring we look
     * for (case sensitive match)
     * 
     * @param target    target to run
     * @param cause     information string to reader of report
     * @param contains  substring of the build exception to look for
     */
    protected void expectBuildExceptionStackTraceContaining( String target, String cause, String contains ) {
       try {
          executeTarget( target );
       }
       catch ( org.apache.tools.ant.BuildException ex ) {
          //buildException = ex;  // buildException has private access in super
          StringWriter stacktrace = new StringWriter();
          PrintWriter writer = new PrintWriter( stacktrace, true );
          ex.printStackTrace( writer );
          String trace = stacktrace.toString();
          if ( ( null != contains ) && ( trace.indexOf( contains ) == -1 ) ) {
             fail( "Should throw BuildException because '" + cause + "' with message containing '" + contains + "' (actual message '" + trace + "' instead)" );
          }
          return;
       }
       fail( "Should throw BuildException because: " + cause );
    }

    /**
     * call a target, verify named property is "true".
     *
     * @param target build file target
     * @param property property name
     */
    protected void expectPropertySet(String target, String property) {
        expectPropertySet(target, property, "true");
    }

    /**
     * call a target, verify property is null
     * @param target build file target
     * @param property property name
     */
    protected void expectPropertyUnset(String target, String property) {
        expectPropertySet(target, property, null);
    }

    /**
     * call a target, verify property is as expected
     *
     * @param target build file target
     * @param property property name
     * @param value expected value
     */
    protected void expectPropertySet(String target, String property, String value) {
        executeTarget(target);
        assertPropertyEquals(property, value);
    }
    
    /**
     * assert that a property equals &quot;true&quot;
     * @param property property name
     */
    protected void assertPropertySet(String property) {
        assertPropertyEquals(property, "true");
    }

    /**
     * assert that a property is null
     * @param property property name
     */
    protected void assertPropertyUnset(String property) {
        assertPropertyEquals(property, null);
    }
    
    /**
     * assert that a property equals a value; comparison is case sensitive.
     * @param property property name
     * @param value expected value
     */
    protected void assertPropertyEquals(String property, String value) {
        String result = project.getProperty(property);
        assertEquals("property " + property,value,result);
    }


    /**
     *  set up to run the named project
     *
     * @param  filename name of project file to run
     */
    protected void configureProject(String filename,boolean isConsoleOutputted) throws BuildException {
        configureProject(filename, Project.MSG_DEBUG,isConsoleOutputted);
    }

    /**
     *  set up to run the named project
     *
     * @param  filename name of project file to run
     * @param   logLevel   record the log specified by it
     */
    protected void configureProject(String filename, int logLevel,boolean isConsoleOutputted)
        throws BuildException {
        logBuffer = new StringBuffer();
        fullLogBuffer = new StringBuffer();
        consoleOutputted=isConsoleOutputted;
        
        project = new Project();
        project.init();
        project.setUserProperty( "ant.file" , new File(filename).getAbsolutePath() );
        project.addBuildListener(new AntTestListener(logLevel));
        ProjectHelper.configureProject(project, new File(filename));
    }

    /**
     *  execute a target we have set up
     * @pre configureProject has been called
     * @param  targetName  target to run
     */
    protected void executeTarget(String targetName) {
        project.executeTarget(targetName);           
    }

    /**
     * Get the project which has been configured for a test.
     *
     * @return the Project instance for this test.
     */
    protected Project getProject() {
        return project;
    }

    /**
     * get the directory of the project
     * @return the base dir of the project
     */
    protected File getProjectDir() {
        return project.getBaseDir();
    }

    /**
     *  Gets the log the BuildFileTest object.
     *  only valid if configureProject() has
     *  been called.
     * @pre logBuffer!=null
     * @return    The log value
     */
    protected String getLog() {
        return logBuffer.toString();
    }

    /**
     *  Gets the log the BuildFileTest object.
     *  only valid if configureProject() has
     *  been called.
     * @pre fullLogBuffer!=null
     * @return    The log value
     */
    protected String getFullLog() {
        return fullLogBuffer.toString();
    }

    protected BuildException getBuildException() {
        return buildException;
    }

    /**
     * Retrieve a resource from the caller classloader to avoid
     * assuming a vm working directory. The resource path must be
     * relative to the package name or absolute from the root path.
     * @param resource the resource to retrieve its url.
     * @throws AssertionFailureException if resource is not found.
     */
    protected URL getResource(String resource){
        URL url = getClass().getResource(resource);
        assertNotNull("Could not find resource :" + resource, url);
        return url;
    }

 
    /**
     * our own personal build listener
     */
    private class AntTestListener implements BuildListener {
        private int logLevel;

        /**
         * Constructs a test listener which will ignore log events
         * above the given level
         */
        public AntTestListener(int logLevel) {
            this.logLevel = logLevel;
        }

        /**
         *  Fired before any targets are started.
         */
        public void buildStarted(BuildEvent event) {
        }

        /**
         *  Fired after the last target has finished. This event
         *  will still be thrown if an error occured during the build.
         *
         *  @see BuildEvent#getException()
         */
        public void buildFinished(BuildEvent event) {
        }

        /**
         *  Fired when a target is started.
         *
         *  @see BuildEvent#getTarget()
         */
        public void targetStarted(BuildEvent event) {
            if (consoleOutputted){
                System.out.println(event.getTarget().getName()+":");                
            }

        }

        /**
         *  Fired when a target has finished. This event will
         *  still be thrown if an error occured during the build.
         *
         *  @see BuildEvent#getException()
         */
        public void targetFinished(BuildEvent event) {
            //System.out.println("targetFinished " + event.getTarget().getName());
        }

        /**
         *  Fired when a task is started.
         *
         *  @see BuildEvent#getTask()
         */
        public void taskStarted(BuildEvent event) {
            //System.out.println("taskStarted " + event.getTask().getTaskName());
        }

        /**
         *  Fired when a task has finished. This event will still
         *  be throw if an error occured during the build.
         *
         *  @see BuildEvent#getException()
         */
        public void taskFinished(BuildEvent event) {
            //System.out.println("taskFinished " + event.getTask().getTaskName());
        }

        /**
         *  Fired whenever a message is logged.
         *
         *  @see BuildEvent#getMessage()
         *  @see BuildEvent#getPriority()
         */
        public void messageLogged(BuildEvent event) {
            if (event.getPriority() > logLevel) {
                // ignore event
                return;
            }
            
            if (event.getPriority() == Project.MSG_INFO ||
                event.getPriority() == Project.MSG_WARN ||
                event.getPriority() == Project.MSG_ERR) {
                logBuffer.append(event.getMessage());
            }
            fullLogBuffer.append(event.getMessage());         
            if (consoleOutputted){
                System.out.println(event.getMessage());
            }
        }
    }


}
