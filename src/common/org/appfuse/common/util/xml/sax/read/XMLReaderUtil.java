package org.appfuse.common.util.xml.sax.read;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

public class XMLReaderUtil extends DefaultHandler implements LexicalHandler{
	private String xmlPath=null;
	
	public XMLReaderUtil(String xmlPath) {
		this.xmlPath=xmlPath;
		init();
	}
	
	public String getXmlPath() {
		return xmlPath;
	}

	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
		init();
	}

	private void init(){
		try {
			
			InputStream inputStream= new BufferedInputStream
					(new FileInputStream(new File(getXmlPath())));
			InputSource xmlInputSource =new InputSource(inputStream);
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);
			SAXParser saxParser = factory.newSAXParser();
			XMLReader xmlReader=saxParser.getXMLReader();
			xmlReader.setContentHandler(this);
			xmlReader.setEntityResolver(this);
			xmlReader.setErrorHandler(this);
			xmlReader.setDTDHandler(this);
//			xmlReader.setFeature();
			xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler",this);
			xmlReader.parse(xmlInputSource);
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		String text=new String(ch, start, length);
		System.out.println(" "+text.trim());
	}

	public void startDocument() throws SAXException {
		System.out.println("----Start Document----");
	}
	
	public void endDocument() throws SAXException {
		System.out.println("----End Document----");
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.print("uri: "+uri);
		System.out.print("localName: "+localName);
		System.out.print("< "+qName+">");

		for (int i = 0; i < attributes.getLength(); i++) {
			System.out.println(" "+attributes.getQName(i)+" = "+attributes.getValue(i));
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("</"+qName+">");
	}

	/**
	 * 当碰到空格，空行的时候
	 */
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.ignorableWhitespace(ch, start, length);
	}

	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub
		super.processingInstruction(target, data);
	}

	public void setDocumentLocator(Locator locator) {
		
	}
	
	public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
		return super.resolveEntity(publicId, systemId);
	}

	public void skippedEntity(String name) throws SAXException {
	}

	public void startPrefixMapping(String prefix, String uri) throws SAXException {
	
	}
	
	public void endPrefixMapping(String prefix) throws SAXException {
	}


	public void notationDecl(String name, String publicId, String systemId) throws SAXException {
		System.out.println("publicId: "+publicId);
		System.out.println("systemId: "+systemId);
		System.out.println("name: "+name);
	}
	
	public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
		System.out.println("name: "+name);
		System.out.println("publicId: "+publicId);
		System.out.println("systemId: "+systemId);
		System.out.println("notationName: "+notationName);
	}
	
	public void error(SAXParseException e) throws SAXException {
		
	}

	public void fatalError(SAXParseException e) throws SAXException {
	
	}
	
	public void warning(SAXParseException e) throws SAXException {
		
	}

	/**
	 * ------------------------------------
	 * LexicalHandler的方法。
	 * ------------------------------------
	 */
	
	public void comment(char[] ch, int start, int length) throws SAXException {
		String comment=new String(ch,start,length);
		System.out.println("comment: "+comment);
	}

	public void startCDATA() throws SAXException {
		System.out.println("startCDATA()");
	}

	public void endCDATA() throws SAXException {
		System.out.println("endCDATA()");
	}

	public void startDTD(String name, String publicId, String systemId) throws SAXException {
		System.out.println("startDTD()");
		System.out.println("DTDname: "+name);
		System.out.println("DTDpublicId: "+publicId);
		System.out.println("DTDsystemId: "+systemId);
	}
	
	public void endDTD() throws SAXException {
		System.out.println("endDTD()");
	}
	
	public void startEntity(String name) throws SAXException {
		System.out.println("StartEntityName: "+name);
	}

	public void endEntity(String name) throws SAXException {
		System.out.println("EndEntityName: "+name);
	}
}
