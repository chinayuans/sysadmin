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
 * �������е�DAO
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
		//�ı���ʽ����JUnit
//		junit.textui.TestRunner.run(suite());
		//swingͼ�η�ʽ����JUnit
		junit.swingui.TestRunner.run(org.appfuse.dao.AllTests.class);
		//awtͼ�η�ʽ����JUnit
//		junit.awtui.TestRunner.run(org.appfuse.dao.AllTests.class);

	}

	public static Test suite(){
		TestSuite suite=new TestSuite("Test for org.appfuse.dao");
		
		//$JUnit-BEGIN$
		
		//add Test Object;   �����ã������Ƽ��������ַ�����
//		suite.addTest(new BaseDAOTest("BaseDAOTest"));
//		suite.addTest(new MenuItemDAOTest("MenuItemDAOTest"));
//		suite.addTest(new UserDAOTest("UserDAOTest"));
		
		//add TestSuite object;   Ҳ���������ַ���
//		suite.addTest(new TestSuite(BaseDAOTest.class));
//		suite.addTest(new TestSuite(MenuItemDAOTest.class));
//		suite.addTest(new TestSuite(UserDAOTest.class));
		
		
		//add TestSuite class;   Ҳ���������ַ���
//		suite.addTestSuite(BaseDAOTest.class);
//		suite.addTestSuite(MenuItemDAOTest.class);
//		suite.addTestSuite(UserDAOTest.class);
		
		//add Test class;    ���ã�����ǿ���Ƽ�ʹ�����ַ���
		suite.addTest(org.appfuse.dao.base.AllTests.suite());
		suite.addTest(org.appfuse.dao.sysadmin.AllTests.suite());
		
		//$JUnit-END$
		return suite;
	}
}
