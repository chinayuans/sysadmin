package org.appfuse.common.util.system;

public class ThreadUtil {
	public static void printCurrentThreadCallStack() {
		Thread.dumpStack(); 
	}
}
