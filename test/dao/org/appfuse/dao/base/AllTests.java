package org.appfuse.dao.base;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.appfuse.dao.base");
		//$JUnit-BEGIN$
		suite.addTestSuite(BaseDAOTest.class);
		//$JUnit-END$
		return suite;
	}

}
