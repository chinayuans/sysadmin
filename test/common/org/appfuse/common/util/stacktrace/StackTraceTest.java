package org.appfuse.common.util.stacktrace;

import junit.framework.TestCase;

public class StackTraceTest extends TestCase {

	/*
	 * Test method for 'org.appfuse.common.util.stacktrace.StackTrace.getStackTrace(Exception)'
	 */
	public void testGetStackTrace1() {
		
		//void java.lang.Thread.dumpStack()
		//Prints a stack trace of the current thread. This method is used only for debugging. 

		Thread.dumpStack(); 
		
		try {
			throw new Exception("hello,it is throwing a exception");
		} catch (Exception e) {
			String detailedException=
				StackTrace.getStackTrace(e,this);
			System.out.println(detailedException);
			
//			Thread.dumpStack(); 
		}
		
//		Thread.dumpStack(); 
	}
	
	public void testGetStackTrace2() {
		//void java.lang.Thread.dumpStack()
		//Prints a stack trace of the current thread. This method is used only for debugging. 

		Thread.dumpStack(); 
		
		try {
			throw new Exception("hello,it is throwing a exception");
		} catch (Exception e) {
			String detailedException=
				StackTrace.getStackTrace(e);
			System.out.println(detailedException);
			
//			Thread.dumpStack(); 
		}
		
//		Thread.dumpStack(); 
	}
	
	public void testGetStackTrace3() {
		try {
			throw new Exception("hello,it is throwing a exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testGetStackTrace4() {
		Thread.dumpStack(); 
	}

}
