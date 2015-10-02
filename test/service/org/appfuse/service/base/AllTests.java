package org.appfuse.service.base;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.appfuse.service.base");
		//$JUnit-BEGIN$
		suite.addTestSuite(BaseServiceTest.class);
		//$JUnit-END$
		return suite;
	}

}
