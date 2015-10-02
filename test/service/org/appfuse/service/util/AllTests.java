package org.appfuse.service.util;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.appfuse.service.util");
		//$JUnit-BEGIN$
		suite.addTestSuite(CharsetConvertTest.class);
		//$JUnit-END$
		return suite;
	}

}
