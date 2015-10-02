package org.appfuse.common.util.xml.dom.read;

import java.net.URL;



import junit.framework.TestCase;

public class XMLReaderUtilTest extends TestCase {

	public void testXMLReader() throws Exception {
		
		URL url_temp=getClass().getResource("");
		System.out.println("url: "+url_temp);
		
		URL url=getClass().getResource("commondb_migration.xml");
		System.out.println("url: "+url);
		
		
		
		String file=url.getFile();
		XMLReaderUtil util=new XMLReaderUtil(file);
	}
}
