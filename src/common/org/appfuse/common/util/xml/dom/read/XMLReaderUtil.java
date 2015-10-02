package org.appfuse.common.util.xml.dom.read;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLReaderUtil implements ErrorHandler{
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
		Document document=null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			DocumentBuilder xmlReader = factory.newDocumentBuilder();
			xmlReader.isNamespaceAware();
			xmlReader.isValidating();
			xmlReader.setErrorHandler(this);
			document=xmlReader.parse(new File(getXmlPath()));
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/**
		 * 将整个文档按照自己想要的方式进行输出。
		 */
		DocumentType doctype = document.getDoctype();
		Node XMLRootNode = document;
		String rootNodeDescription = XMLRootNode.getNodeName() + ": "
				+ XMLRootNode.getNodeValue() + " "
				+ nodeType(XMLRootNode.getNodeType());
		
		System.out.println(rootNodeDescription);
		/**
		 * construct doctype from xml's DOCTYPE
		 */
		if (doctype != null) {
			NamedNodeMap nnm = doctype.getEntities();

			/**
			 * construct all attributes of doctype.
			 */
			attrNodes(nnm);
		}

		/**
		 * construct all nodes from xml.
		 */
		childNodes(XMLRootNode);
		
		
		/**
		 * 将整个xml文档按照原来的格式进行输出。
		 */
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			String systemValue = (new File(document.getDoctype().getSystemId())).getName();
			System.out.println("systemValue:"+systemValue);
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"book4.dtd");
			
			System.out.println("========list entire document==============");
			StreamResult console = new StreamResult(System.out);			
			transformer.transform(source, console);
			
			//将xml文档进行输出到文件。
//			StreamResult fileResult = new StreamResult(new File(getXmlPath()+".bak"));
//			transformer.transform(source, fileResult);
			
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

	
	
	public void error(SAXParseException e) throws SAXException {
//		throw e;
	}

	public void fatalError(SAXParseException e) throws SAXException {
//		throw e;
	}
	
	public void warning(SAXParseException e) throws SAXException {
//		throw e;
	}

	/**
	 * nested function or recursive function. construct all children nodes. 
	 * 
	 * @param presentXMLNode
	 */
	private void childNodes(Node presentXMLNode) {
		/**
		 * construct all attributes of present node.
		 */
		NamedNodeMap nnm = presentXMLNode.getAttributes();
		attrNodes(nnm);

		/**
		 * construct all children nodes of present node.
		 */
		NodeList nodelist = presentXMLNode.getChildNodes();
		for (int i = 0; i < nodelist.getLength(); i++) {

			Node childXMLNode = nodelist.item(i);

			/**
			 * form a description of node.
			 */
			String xmlNodeDescription = 
				      childXMLNode.getNodeName() + ": " 
				      + childXMLNode.getNodeValue() + " " 
				      + "("+ nodeType(childXMLNode.getNodeType())+ ")";
			
			System.out.println(xmlNodeDescription);
			
			/**
			 * recursive invoke.
			 */
			childNodes(childXMLNode);
		}
	}

	/**
	 * construct all attributes of present node.
	 * @param nnm
	 */
	private void attrNodes(NamedNodeMap nnm) {
		
		if (nnm != null) {
			for (int i = 0; i < nnm.getLength(); i++) {
				Node node = nnm.item(i);

				/**
				 * form a description of attributes.
				 */
				String attributeDescription = 
						  node.getNodeName() + ": "
						+ node.getNodeValue() + " "
						+ "("+nodeType(node.getNodeType())+ ")";
				System.out.println("  "+attributeDescription);
			}
		}
	}

	/**
	 * a map
	 * 
	 * @param nodetype
	 * @return
	 */
	private String nodeType(short nodetype) {
		String nodeTypeDescription = "unexpected type";
		switch (nodetype) {
		case Node.ATTRIBUTE_NODE:
			nodeTypeDescription = "ATTRIBUTE_NODE";
			break;
		case Node.CDATA_SECTION_NODE:
			nodeTypeDescription = "CDATA_SECTION_NODE";
			break;
		case Node.COMMENT_NODE:
			nodeTypeDescription = "COMMENT_NODE";
			break;
		case Node.DOCUMENT_FRAGMENT_NODE:
			nodeTypeDescription = "DOCUMENT_FRAGMENT_NODE";
			break;
		case Node.DOCUMENT_NODE:
			nodeTypeDescription = "DOCUMENT_NODE";
			break;
		case Node.DOCUMENT_TYPE_NODE:
			nodeTypeDescription = "DOCUMENT_TYPE_NODE";
			break;
		case Node.ELEMENT_NODE:
			nodeTypeDescription = "ELEMENT_NODE";
			break;
		case Node.ENTITY_NODE:
			nodeTypeDescription = "ENTITY_NODE";
			break;
		case Node.ENTITY_REFERENCE_NODE:
			nodeTypeDescription = "ENTITY_REFERENCE_NODE";
			break;
		case Node.NOTATION_NODE:
			nodeTypeDescription = "NOTATION_NODE";
			break;
		case Node.PROCESSING_INSTRUCTION_NODE:
			nodeTypeDescription = "PROCESSING_INSTRUCTION_NODE";
			break;
		case Node.TEXT_NODE:
			nodeTypeDescription = "TEXT_NODE";
			break;
		}
		return nodeTypeDescription;
	}
}
