package org.appfuse.common.util.jvm;

import java.util.Properties;

import junit.framework.TestCase;

public class SystemUtilTest extends TestCase {
	
	public void testProperties() throws Exception {
		System.out.println(System.getProperties());
		
		System.getProperties().list(System.out);
		Properties props=System.getProperties();
       
	}
}
