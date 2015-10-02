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
	 * ���Ƚ�HelloWorld1�������л���һ��xml�ļ���ȥ,���ܹ����л����ݵ�primitive����.
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
	 * ���������testSerialization2XmlFile��,��õ�һ��serializedObject.xml�ļ�,�޸�����ļ��е�
	 * org.appfuse.common.util.serialize.HelloWorld1 �ĳ�org.appfuse.common.util.serialize.HelloWorld2.
	 * Ȼ�������������������,���Կ���HelloWord1�е����ݵ�HelloWorld2��ȥ��.
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
        XMLOutputter xmlOut = new XMLOutputter(); // ����xml�������
        Format format = xmlOut.getFormat(); // �������ʽ��
        format.setEncoding("UTF-8"); // �����ַ���
        format.setExpandEmptyElements(true); //�Ƿ����
        format.setIndent("\t");
        format.setLineSeparator("\r\n");
        xmlOut.setFormat(format); // �Ѹ�ʽ�������������
        try {
            // ����xml���ļ����ļ���Ϊ�û�������ļ�
            xmlOut.output(doc, new FileOutputStream(fileName));
            System.out.println("create a file called " + fileName);
        } catch (IOException ex) {
            System.out.println("the cause of file creation failure is " + ex.getMessage());
        }
    }

    private Document loadFile(String fileName) {
        Document doc = null;
        FileInputStream fi = null; // �ļ�������
        File file = new File(fileName);
        try {
            if (!file.isFile()) { // �ļ���ʧ��
                System.out.println("fails to open a file called " + fileName);
            } else {
                fi = new FileInputStream(file); // ��������
                SAXBuilder sb = new SAXBuilder();
                doc = sb.build(fi); // ���ļ�����build
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
    	XMLOutputter xmlOut = new XMLOutputter(); // ����xml�������
        Format format = xmlOut.getFormat(); // �������ʽ��
        format.setEncoding("UTF-8"); // �����ַ���
        format.setExpandEmptyElements(true); //�Ƿ����
        format.setIndent("\t");
        format.setLineSeparator("\r\n");
        xmlOut.setFormat(format); // �Ѹ�ʽ�������������
        try {
            //print Document in normal text format.
            xmlOut.output(doc, System.out);
          
        } catch (IOException ex) {
            System.out.println("the cause of file creation failure is " + ex.getMessage());
        }
    }
}
