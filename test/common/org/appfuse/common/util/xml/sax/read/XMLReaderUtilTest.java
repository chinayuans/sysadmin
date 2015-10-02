package org.appfuse.common.util.xml.sax.read;

import java.net.URL;

import junit.framework.TestCase;

public class XMLReaderUtilTest extends TestCase {

	/*
	 * Test method for 'org.appfuse.common.util.xml.sax.read.XMLReaderUtil.init()'
	 */
	public void testXmlReader() {
		
		//===================================================
		//getClass().getResource(name)的查找方法:
		//===================================================
		//1.如果name是以/开头的，表示绝对地址，把/去掉，剩下的字符串做为参数，通过classloader.getResource(name)或者
		//  ClassLoader.getSystemResource(name)去取得resource的URL,
		// for example: /org/appfuse/common/util/xml/sax/read/commondb_migration.xml ->
		//               org/appfuse/common/util/xml/sax/read/commondb_migration.xml 
		//2.如果name不是以/开头的，表示是这个类的相对地址，那么会将这个类的包名＋name做为参数(他会将.全部变成/)，通过
		//  classloader.getResource(name)
		//  或者ClassLoader.getSystemResource(name)去取得resource的URL.
		//  for example: commondb_migration.xml  ->
		//               org/appfuse/common/util/xml/sax/read/commondb_migration.xml
		
		
		//=======================================================================
		//classloader.getResource(name)与ClassLoader.getSystemResource(name)
		//=======================================================================
		//classloader.getResource(name) classloader是ClassLoader的一个实例：
		//URL url;
		//if (parent != null) {
		//    url = parent.getResource(name);
		//} else {
		//    url = getBootstrapResource(name);
		//}
		//if (url == null) {
		//    url = findResource(name);
		//}
		//return url;
		//1.上面的代码来自ClassLoader.getResource(name).表示首先到当前的classloader的parent ClassLoader找，
		//如果parent ClassLoader is null,再到BootStrap ClassLoader中去找。
		//上面的代码的具体意思就是:首先到BootStrap ClassLoader中找，如果找不到，再到parent ClassLoder中找
		//2.如果上面也找不到，只好调用当前的classloader的findResource方法查找.
		//总结： 首先到BootStrap ClassLoader找，然后到Ext ClassLoader中找，再到 System(App) ClassLoader中找，
		//      最后到自己的classLoader的findResource的方法中找。
		
		//ClassLoader.getSystemResource(name) ClassLoader是一个类，这是一个静态方法。
		//从App classLoader角度来获得resource
		
		//=======================================================================
		//统一资源标识符 (Uniform Resource Identifier, URI) :
		//=======================================================================
		//一个用来标识抽象或物理资源的简洁字符串，用于唯一地标识元素或属性的数字或名称。
		//URI 包括统一资源名称 (URN) 和统一资源定位器 (URL)。 
		//URI 是在 Internet 上定位资源的常规方案，它的着重点在于资源，而不是位置。理论上，URI 可以查找镜像文档的最近的副本
		//或者定位从一个站点移动到另一个站点的文档。 
		//尽管预计 URN 在将来会变得更通用，但今天谈到 XML 时，URI 往往是指 URL。 
		
//		URL url=Thread.currentThread().getContextClassLoader().getResource("/org/appfuse/common/util/xml/sax/read/commondb_migration.xml");
//		URL url=getClass().getResource("/org/appfuse/common/util/xml/sax/read/commondb_migration.xml");
		
		URL url=getClass().getResource("commondb_migration.xml");
		System.out.println("url: "+url);
		
//		try {
//			url = new URL("file:D:/work/eclipse3.1-work/work/sysadmin/src/common/org/appfuse/common/util/xml/commondb_migration.xml");
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
		
		String file=url.getFile();
		XMLReaderUtil util=new XMLReaderUtil(file);
	}

}
