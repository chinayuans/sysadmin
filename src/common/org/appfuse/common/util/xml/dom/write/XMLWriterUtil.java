package org.appfuse.common.util.xml.dom.write;

import java.io.File;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class XMLWriterUtil {
	static Document document;

	static Transformer transformer;

	public static void main(String[] args) throws Exception {
		/**
		 * locate xml file.
		 */
		URL url=XMLWriterUtil.class.getResource("");
		String file=url.getFile();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		/**
		 * create a root element.
		 */
		document = builder.newDocument();
		Element root = document.createElement("book");
		root.setAttribute("category", "softwarer programming");
		root.setAttribute("ISBN", "117");
		document.appendChild(root);
		root.appendChild(newLine());

		
		Element author = document.createElement("author");
		Text authorText = document.createTextNode("Zhang Hongbin");
		root.appendChild(author);
		author.appendChild(authorText);
		root.appendChild(newLine());

		Comment comment = document.createComment("This is a comment");
		root.appendChild(comment);
		root.appendChild(newLine());

		Element resource = document.createElement("resource");
		Element books = document.createElement("books");
		CDATASection booksText = document.createCDATASection("Not Availabe yet");
		root.appendChild(resource);
		resource.appendChild(books);
		books.appendChild(booksText);
		root.appendChild(newLine());

		document.getDocumentElement().normalize();

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		
		System.out.println("---List Whole Document---");
		transforming(source, new File(file+"resource1.xml"));

		NodeList list = document.getElementsByTagName("resource");
		Node node = list.item(0);
		source = new DOMSource(node);
		System.out.println();
		System.out.println("---List Node: resource---");
		transforming(source, new File(file+"resource2.xml"));
	}

	static Text newLine() {
		Text nullline = document.createTextNode("\n");
		return nullline;
	}

	static void transforming(DOMSource source, File file) throws Exception {
		StreamResult console = new StreamResult(System.out);
		StreamResult fileResult = new StreamResult(file);
		transformer.transform(source, console);
		transformer.transform(source, fileResult);
	}
}
