package org.appfuse.web;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/*
 * project name : sysadmin
 * package name : 
 * file    name : MasterTester.java
 * class   name : MasterTester
 * Created on 2005-8-24 11:12:04
 * creator ---Joson Yuan
 * author comments:
 * 
 */

public class AllTests {

	/**
	 * @param args
	 *            Created on 2005-8-24 11:12:04
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public static void main(String[] args) {
		// 文本方式运行JUnit
		TestRunner.run(suite());
		//swing图形方式运行JUnit
//		junit.swingui.TestRunner.run(org.appfuse.web.AllTests.class);
		//awt图形方式运行JUnit
//		junit.awtui.TestRunner.run(org.appfuse.web.AllTests.class);
	}

	public static Test suite() {

		TestSuite suite = new TestSuite("Test for org.appfuse.web.sysadmin");
		//$JUnit-BEGIN$
		suite.addTest(org.appfuse.web.sysadmin.AllTests.suite());
		//$JUnit-END$
		return suite;
	}
}
