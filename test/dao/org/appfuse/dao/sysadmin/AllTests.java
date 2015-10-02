package org.appfuse.dao.sysadmin;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.appfuse.dao.sysadmin");
		//$JUnit-BEGIN$
		suite.addTestSuite(UserDAOTest.class);
		suite.addTestSuite(MenuItemDAOTest.class);
		//$JUnit-END$
		return suite;
	}

}
