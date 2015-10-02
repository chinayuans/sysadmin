package org.appfuse.web.sysadmin;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.appfuse.web.sysadmin");
		//$JUnit-BEGIN$
		suite.addTestSuite(UserActionTest.class);
		suite.addTestSuite(MenuItemActionTest.class);
		//$JUnit-END$
		return suite;
	}

}
