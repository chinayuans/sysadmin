/*
 * project name : equinox-self
 * package name : org.appfuse.web
 * file    name : BaseActionTest.java
 * class   name : BaseActionTest
 * Created on 2005-7-24 10:07:07
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.web.base;

import org.apache.log4j.Logger;

import servletunit.struts.MockStrutsTestCase;

/**
 * 经过数次试验,struts-config.xml,web.xml必须放置在/WEB-INF/目录下。无法通过setContextDirectory(file);
 * setConfigFile("/struts-config.xml"); setServletConfigFile("/web.xml"); 无法更改
 * web.xml,struts-config.xml的位置。其他xml文件位置可以进行更改,但是必须在struts-config.xml做相应的调整。
 *
 */
public class BaseActionTest extends MockStrutsTestCase {
	
	/* By default, the Struts ActionServlet will look for the file WEB-INF/struts-config.xml, 
	 * so you must place the directory that contains WEB-INF in your CLASSPATH. 
	 * If you would like to use an alternate configuration file, please see the setConfigFile()
	 * method for details on how this file is located.
	*/
	private static final Logger logger = Logger
			.getLogger(BaseActionTest.class);
	
	public BaseActionTest(String name) {
		super(name);
		if (logger.isInfoEnabled()) {
			logger.info("BaseActionTest()");
		}
	}

	/**
	 *  servletunit.struts.ExceptionDuringTestError: A NullPointerException was thrown. 
	 *  This may indicate an error in your ActionForm, or it may indicate that the Struts
	 *  ActionServlet was unable to find struts config file.  TestCase is running from 
	 *  D:\work\eclipse3.1-work\work\sysadmin directory.  Context directory has not been set. 
	 *  Try calling setContextDirectory() with a relative or absolute path.  struts config file 
	 *  must be found under the context directory, the directory the test case is running from, 
	 *  or in the classpath.
	 */
	public void setUp() throws Exception {
		super.setUp();
		
		/**
		 * 以下试验代码被证明不可行。
		 */
//		String pkg = ClassUtils.classPackageAsResourcePath(Constants.class);
//		System.out.println(pkg);

//		File file = new File("D:/work/eclipse3.1-work/work/sysadmin/WebRoot/WEB-INF/classes/");
//		File realFile=file.getAbsoluteFile();
//		System.out.println(realFile);
//		
//		/**
//		 *  Sets the context directory to be used with the getRealPath() methods
//		 *  in the ServletContext and HttpServletRequest API.
//		 */
//		setContextDirectory(file);
//		
//		/**
//		 *  Sets the location of the Struts configuration file for the default module. 
//		 *  This method can take either an absolute path, or a relative path.
//		 *  If an absolute path is supplied, the configuration file will be loaded from 
//		 *  the underlying filesystem; otherwise, the ServletContext loader will be used.
//		 */
//		setConfigFile("/struts-config.xml");
//		
//		/**
//		 * Sets the location of the web.xml configuration file to be used to 
//		 * set up the servlet context and configuration for this test. This method supports
//		 * both init-param and context-param tags, setting the ServletConfig and ServletContext 
//		 * appropriately. This method can take either an absolute path, or a relative path. 
//		 * If an absolute path is supplied, the configuration file will be loaded from 
//		 * the underlying filesystem; otherwise, the ServletContext loader will be used.
//		 */
//		setServletConfigFile("/web.xml");
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
