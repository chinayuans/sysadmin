package org.appfuse.service.sysadmin;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.appfuse.service.sysadmin");
		//$JUnit-BEGIN$
		suite.addTestSuite(MenuItemManagerServiceTest.class);
		suite.addTestSuite(UserManagerServiceTest.class);
		//$JUnit-END$
		return suite;
	}

}
