/*
 * project name : sysadmin
 * package name : org.appfuse.dao
 * file    name : AllTests.java
 * class   name : AllTests
 * Created on 2005-8-24 11:00:03
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.dao;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * 测试所有的DAO
 * Created on 2005-8-24 11:00:12
 * @author ---Joson Yuan
 * author comments:
 *
 */
public class AllTests {

	/**
	 * @param args
	 * Created on 2005-8-24 11:00:03
	 * @author ---Joson Yuan
	 * author comments:
	 * 
	 */
	public static void main(String[] args) {
		//文本方式运行JUnit
//		junit.textui.TestRunner.run(suite());
		//swing图形方式运行JUnit
		junit.swingui.TestRunner.run(org.appfuse.dao.AllTests.class);
		//awt图形方式运行JUnit
//		junit.awtui.TestRunner.run(org.appfuse.dao.AllTests.class);

	}

	public static Test suite(){
		TestSuite suite=new TestSuite("Test for org.appfuse.dao");
		
		//$JUnit-BEGIN$
		
		//add Test Object;   不常用－－－推荐不用这种方法。
//		suite.addTest(new BaseDAOTest("BaseDAOTest"));
//		suite.addTest(new MenuItemDAOTest("MenuItemDAOTest"));
//		suite.addTest(new UserDAOTest("UserDAOTest"));
		
		//add TestSuite object;   也可以用这种方法
//		suite.addTest(new TestSuite(BaseDAOTest.class));
//		suite.addTest(new TestSuite(MenuItemDAOTest.class));
//		suite.addTest(new TestSuite(UserDAOTest.class));
		
		
		//add TestSuite class;   也可以用这种方法
//		suite.addTestSuite(BaseDAOTest.class);
//		suite.addTestSuite(MenuItemDAOTest.class);
//		suite.addTestSuite(UserDAOTest.class);
		
		//add Test class;    常用－－－强烈推荐使用这种方法
		suite.addTest(org.appfuse.dao.base.AllTests.suite());
		suite.addTest(org.appfuse.dao.sysadmin.AllTests.suite());
		
		//$JUnit-END$
		return suite;
	}
}
