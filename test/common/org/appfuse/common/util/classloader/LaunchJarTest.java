package org.appfuse.common.util.classloader;

import junit.framework.TestCase;

public class LaunchJarTest extends TestCase {

	/**
	 * execute the specified java class.
	 * 
	 */
	public void testJarClassLoader1() throws Exception {
		String jarsLocation = "D:/backup/��̹���/ant/ant explorer/Stand-alone/antexplorer.jar";
		
//		String jarsLocation = "D:/backup/��̹���/JDBC-Viewer/isql-viewer/iSQL-Viewer-2.1.8.jar";
		// ��jar��תΪbyte[]
		byte[] resource = LaunchJar.getDataSource(jarsLocation);

		// ͨ��byte[]���������������
		Class clazz = LaunchJar.load(resource);
		// ����main����
		LaunchJar.callVoidMethod(clazz, "main", new String[] { "" });

	}
}
