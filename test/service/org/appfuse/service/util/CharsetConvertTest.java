/*
 * project name : sysadmin
 * package name : org.appfuse.service.util
 * file    name : CharsetConvertTest.java
 * class   name : CharsetConvertTest
 * Created on 2005-9-27 9:30:50
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.service.util;

import org.apache.log4j.Logger;

import junit.framework.TestCase;

public class CharsetConvertTest extends TestCase {

	private static final Logger logger = Logger
			.getLogger(CharsetConvertTest.class);

	public CharsetConvertTest() {

	}

	
	protected void setUp() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("CharsetConvertTest() - start");
		}
		super.setUp();
	}


	protected void tearDown() throws Exception {
		
		
		super.tearDown();

		if (logger.isDebugEnabled()) {
			logger.debug("CharsetConvertTest() - end");
		}
	}


	public void testGB2ISO() {
		if (logger.isDebugEnabled()) {
			logger.debug("testGB2ISO()："+CharsetConvert.GB2ISO("中国人"));
		}
		
	}

	public void testISO2GB() {
		if (logger.isDebugEnabled()) {
			logger.debug("testISO2GB()："+CharsetConvert.ISO2GB(CharsetConvert.GB2ISO("中国人")));
		}
		
	}
}
