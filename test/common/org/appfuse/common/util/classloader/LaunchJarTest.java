package org.appfuse.common.util.classloader;

import junit.framework.TestCase;

public class LaunchJarTest extends TestCase {

	/**
	 * execute the specified java class.
	 * 
	 */
	public void testJarClassLoader1() throws Exception {
		String jarsLocation = "D:/backup/编程工具/ant/ant explorer/Stand-alone/antexplorer.jar";
		
//		String jarsLocation = "D:/backup/编程工具/JDBC-Viewer/isql-viewer/iSQL-Viewer-2.1.8.jar";
		// 将jar包转为byte[]
		byte[] resource = LaunchJar.getDataSource(jarsLocation);

		// 通过byte[]获得主函数所在类
		Class clazz = LaunchJar.load(resource);
		// 调用main函数
		LaunchJar.callVoidMethod(clazz, "main", new String[] { "" });

	}
}
