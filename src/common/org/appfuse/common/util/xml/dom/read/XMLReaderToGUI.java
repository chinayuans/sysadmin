package org.appfuse.common.util.xml.dom.read;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReaderToGUI extends JFrame {

	/**
	 * serialization ID
	 */
	private static final long serialVersionUID = -7820441130880183843L;

	static Document document;

	static DefaultMutableTreeNode GUIRootNode;

	static JTree tree;

	public static void main(String[] args) {

		/**
		 * get xml file.
		 */
		URL url = XMLReaderToGUI.class.getResource("commondb_migration.xml");
		System.out.println("url: " + url);
		String file = url.getFile();

		/**
		 * DOM elements.
		 */
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(file);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		DocumentType doctype = document.getDoctype();
		Node XMLRootNode = document;
		String rootNodeDescription = XMLRootNode.getNodeName() + ": "
				+ XMLRootNode.getNodeValue() + " "
				+ nodeType(XMLRootNode.getNodeType());

		/**
		 * GUI framework.
		 */
		XMLReaderToGUI frame1 = new XMLReaderToGUI();
		Container cp = frame1.getContentPane();

		/**
		 * GUI elements.
		 */
		GUIRootNode = new DefaultMutableTreeNode("root: " + rootNodeDescription);

		/**
		 * construct doctype from xml's DOCTYPE
		 */
		if (doctype != null) {
			NamedNodeMap nnm = doctype.getEntities();

			/**
			 * construct all attributes of doctype.
			 */
			attrNodes(nnm, GUIRootNode);
		}

		/**
		 * construct all nodes from xml.
		 */
		childNodes(GUIRootNode, XMLRootNode);

		/**
		 * construct a GUI.
		 */
		tree = new JTree(GUIRootNode);
		JScrollPane scrollPane = new JScrollPane(tree,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		/**
		 * GUI framework.
		 */
		cp.add(scrollPane);
		frame1.setSize(500, 500);
		frame1.setVisible(true);
		frame1.addWindowListener(new WindowClose());
	}

	/**
	 * nested function or recursive function. construct all children nodes. 
	 * 
	 * @param presentTreeNode
	 * @param presentXMLNode
	 */
	static void childNodes(DefaultMutableTreeNode presentTreeNode,
			Node presentXMLNode) {
		/**
		 * construct all attributes of present node.
		 */
		NamedNodeMap nnm = presentXMLNode.getAttributes();
		attrNodes(nnm, presentTreeNode);

		/**
		 * construct all children nodes of present node.
		 */
		NodeList nodelist = presentXMLNode.getChildNodes();
		for (int i = 0; i < nodelist.getLength(); i++) {

			Node childXMLNode = nodelist.item(i);

			/**
			 * form a description of node.
			 */
			String description = 
				      childXMLNode.getNodeName() + ": " 
				      + childXMLNode.getNodeValue() + " " 
				      + "("+ nodeType(childXMLNode.getNodeType())+ ")";

			/**
			 * add the description to GUI Tree.
			 */
			DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(
					description);
			presentTreeNode.add(childTreeNode);

			/**
			 * recursive invoke.
			 */
			childNodes(childTreeNode, childXMLNode);
		}
	}

	/**
	 * construct all attributes of present node.
	 * @param nnm
	 * @param presentTreeNode
	 */
	static void attrNodes(NamedNodeMap nnm,	DefaultMutableTreeNode presentTreeNode) {
		
		if (nnm != null) {
			for (int i = 0; i < nnm.getLength(); i++) {
				Node node = nnm.item(i);

				/**
				 * form a description of attributes.
				 */
				String description = 
						  node.getNodeName() + ": "
						+ node.getNodeValue() + " "
						+ "("+nodeType(node.getNodeType())+ ")";

				/**
				 * add the description to GUI Tree.
				 */
				DefaultMutableTreeNode attrTreeNode = new DefaultMutableTreeNode(description);
				presentTreeNode.add(attrTreeNode);
			}
		}
	}

	/**
	 * a map
	 * 
	 * @param nodetype
	 * @return
	 */
	static String nodeType(short nodetype) {
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

class WindowClose extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}