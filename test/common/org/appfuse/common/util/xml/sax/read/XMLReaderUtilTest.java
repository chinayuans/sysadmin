package org.appfuse.common.util.xml.sax.read;

import java.net.URL;

import junit.framework.TestCase;

public class XMLReaderUtilTest extends TestCase {

	/*
	 * Test method for 'org.appfuse.common.util.xml.sax.read.XMLReaderUtil.init()'
	 */
	public void testXmlReader() {
		
		//===================================================
		//getClass().getResource(name)�Ĳ��ҷ���:
		//===================================================
		//1.���name����/��ͷ�ģ���ʾ���Ե�ַ����/ȥ����ʣ�µ��ַ�����Ϊ������ͨ��classloader.getResource(name)����
		//  ClassLoader.getSystemResource(name)ȥȡ��resource��URL,
		// for example: /org/appfuse/common/util/xml/sax/read/commondb_migration.xml ->
		//               org/appfuse/common/util/xml/sax/read/commondb_migration.xml 
		//2.���name������/��ͷ�ģ���ʾ����������Ե�ַ����ô�Ὣ�����İ�����name��Ϊ����(���Ὣ.ȫ�����/)��ͨ��
		//  classloader.getResource(name)
		//  ����ClassLoader.getSystemResource(name)ȥȡ��resource��URL.
		//  for example: commondb_migration.xml  ->
		//               org/appfuse/common/util/xml/sax/read/commondb_migration.xml
		
		
		//=======================================================================
		//classloader.getResource(name)��ClassLoader.getSystemResource(name)
		//=======================================================================
		//classloader.getResource(name) classloader��ClassLoader��һ��ʵ����
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
		//1.����Ĵ�������ClassLoader.getResource(name).��ʾ���ȵ���ǰ��classloader��parent ClassLoader�ң�
		//���parent ClassLoader is null,�ٵ�BootStrap ClassLoader��ȥ�ҡ�
		//����Ĵ���ľ�����˼����:���ȵ�BootStrap ClassLoader���ң�����Ҳ������ٵ�parent ClassLoder����
		//2.�������Ҳ�Ҳ�����ֻ�õ��õ�ǰ��classloader��findResource��������.
		//�ܽ᣺ ���ȵ�BootStrap ClassLoader�ң�Ȼ��Ext ClassLoader���ң��ٵ� System(App) ClassLoader���ң�
		//      ����Լ���classLoader��findResource�ķ������ҡ�
		
		//ClassLoader.getSystemResource(name) ClassLoader��һ���࣬����һ����̬������
		//��App classLoader�Ƕ������resource
		
		//=======================================================================
		//ͳһ��Դ��ʶ�� (Uniform Resource Identifier, URI) :
		//=======================================================================
		//һ��������ʶ�����������Դ�ļ���ַ���������Ψһ�ر�ʶԪ�ػ����Ե����ֻ����ơ�
		//URI ����ͳһ��Դ���� (URN) ��ͳһ��Դ��λ�� (URL)�� 
		//URI ���� Internet �϶�λ��Դ�ĳ��淽�����������ص�������Դ��������λ�á������ϣ�URI ���Բ��Ҿ����ĵ�������ĸ���
		//���߶�λ��һ��վ���ƶ�����һ��վ����ĵ��� 
		//����Ԥ�� URN �ڽ������ø�ͨ�ã�������̸�� XML ʱ��URI ������ָ URL�� 
		
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
