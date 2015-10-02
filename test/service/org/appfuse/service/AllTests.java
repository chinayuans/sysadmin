package org.appfuse.service;

import junit.framework.Test;
import junit.framework.TestSuite;

/*
 * project name : sysadmin
 * package name : 
 * file    name : AllTests.java
 * class   name : AllTests
 * Created on 2005-8-24 11:09:50
 * creator ---Joson Yuan
 * author comments:
 * 
 */

public class AllTests {

	/**
	 * @param args
	 *            Created on 2005-8-24 11:09:50
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public static void main(String[] args) {
		//�ı���ʽ����JUnit
//		junit.textui.TestRunner.run(suite());
		//swingͼ�η�ʽ����JUnit
		junit.swingui.TestRunner.run(org.appfuse.service.AllTests.class);
		//awtͼ�η�ʽ����JUnit
//		junit.awtui.TestRunner.run(org.appfuse.service.AllTests.class);
	}
	
	public static Test suite(){
		TestSuite suite=new TestSuite("Test for org.appfuse.service");
		
		//$JUnit-BEGIN$
		
		//add Test class;    ���ã�����ǿ���Ƽ�ʹ�����ַ���
		suite.addTest(org.appfuse.service.base.AllTests.suite());
		suite.addTest(org.appfuse.service.sysadmin.AllTests.suite());
		suite.addTest(org.appfuse.service.util.AllTests.suite());
		
		//$JUnit-END$
		return suite;
	}
}
