package org.appfuse.common.util.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.tools.ant.Project;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlSerializerUtilTest extends TestCase {
	
	public XmlSerializerUtilTest(String name) {
		super(name);
	}
	
	private final String fileName="serializedObject.xml";
	
	public void testMain() throws Exception {
        
		/**
         * serialize a object
         */
        HelloWorld1 hello1 = new HelloWorld1();
        System.out.println("object id (before serialization):\r\n"+hello1);
        Document document1 = XmlSerializerUtil.serializeObject(hello1);
        printDocument(document1);
        
        /**
         * deserialize a object
         */
        HelloWorld1 hello2 = (HelloWorld1)  XmlSerializerUtil.deserializeObject(document1);
        System.out.println("object id (after serialization and deserialization):\r\n"+hello2);
        Document document2 = XmlSerializerUtil.serializeObject(hello2);
        printDocument(document2);

    }
	
	/**
	 * 首先将HelloWorld1对象序列化到一个xml文件中去,他能够序列化数据到primitive类型.
	 * 
	 */
	public void testSerialization2XmlFile() throws Exception {
        
        /**
         * serialize a object
         */
        HelloWorld1 hello1 = new HelloWorld1();
        Document document1 = XmlSerializerUtil.serializeObject(hello1);
        createFile(document1, fileName);
        
        /**
         * put data to a object  from xml file 
         */
        Document doc = loadFile(fileName);
        Object object = XmlSerializerUtil.deserializeObject(doc);
        HelloWorld1 hello2 = (HelloWorld1) object;

        /**
         * serialize a object again.
         */ 
        Document document2 = XmlSerializerUtil.serializeObject(hello2);
        printDocument(document2);
    }
	
	/**
	 * 在运行完成testSerialization2XmlFile后,会得到一个serializedObject.xml文件,修改这个文件中的
	 * org.appfuse.common.util.serialize.HelloWorld1 改成org.appfuse.common.util.serialize.HelloWorld2.
	 * 然后再运行下面这个方法,可以看到HelloWord1中的数据到HelloWorld2中去了.
	 */
	public void testDeserialization2Object() throws Exception {
        
        /**
         * put data to a object  from xml file 
         */
        Document doc = loadFile(fileName);
        Object object = XmlSerializerUtil.deserializeObject(doc);
        HelloWorld2 hello2 = (HelloWorld2) object;

        /**
         * serialize a object again.
         */ 
        Document document2 = XmlSerializerUtil.serializeObject(hello2);
        printDocument(document2);
    }
	
	public void testSerializeAntProject() throws Exception {
        /**
         * serialize a object
         */
        Project project1 = new Project();
        System.out.println("object id (before serialization):"+project1);
        Document document1 = XmlSerializerUtil.serializeObject(project1);
        printDocument(document1);
        
        /**
         * deserialize a object
         */
        Project project2 = (Project)  XmlSerializerUtil.deserializeObject(document1);
        System.out.println("object id (after serialization and deserialization):"+project2);
        Document document2 = XmlSerializerUtil.serializeObject(project2);
        printDocument(document2);
    }

    private void createFile(Document doc, String fileName) {
        XMLOutputter xmlOut = new XMLOutputter(); // 生成xml的输出流
        Format format = xmlOut.getFormat(); // 输出流格式化
        format.setEncoding("UTF-8"); // 设置字符集
        format.setExpandEmptyElements(true); //是否填充
        format.setIndent("\t");
        format.setLineSeparator("\r\n");
        xmlOut.setFormat(format); // 把格式化的流给输出流
        try {
            // 生成xml的文件，文件名为用户输入的文件
            xmlOut.output(doc, new FileOutputStream(fileName));
            System.out.println("create a file called " + fileName);
        } catch (IOException ex) {
            System.out.println("the cause of file creation failure is " + ex.getMessage());
        }
    }

    private Document loadFile(String fileName) {
        Document doc = null;
        FileInputStream fi = null; // 文件输入流
        File file = new File(fileName);
        try {
            if (!file.isFile()) { // 文件打开失败
                System.out.println("fails to open a file called " + fileName);
            } else {
                fi = new FileInputStream(file); // 建立打开流
                SAXBuilder sb = new SAXBuilder();
                doc = sb.build(fi); // 把文件流给build
            }
        } catch (Exception e) {
            System.out.println("fails to open a file called " + fileName + ",the cause is "+ e.getMessage());
        } finally {
            try {
                fi.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doc;
    }
    
    private void printDocument(Document doc){
    	XMLOutputter xmlOut = new XMLOutputter(); // 生成xml的输出流
        Format format = xmlOut.getFormat(); // 输出流格式化
        format.setEncoding("UTF-8"); // 设置字符集
        format.setExpandEmptyElements(true); //是否填充
        format.setIndent("\t");
        format.setLineSeparator("\r\n");
        xmlOut.setFormat(format); // 把格式化的流给输出流
        try {
            //print Document in normal text format.
            xmlOut.output(doc, System.out);
          
        } catch (IOException ex) {
            System.out.println("the cause of file creation failure is " + ex.getMessage());
        }
    }
}
