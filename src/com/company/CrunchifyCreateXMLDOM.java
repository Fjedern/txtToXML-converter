package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * @author Crunchify.com
 * In Java How to Create XML File using DOM parser? Writing Out a DOM as an XML File.
 * Version: 1.1
 */

public class CrunchifyCreateXMLDOM {

    public static void main(String[] args) {

        // Defines a factory API that enables applications to obtain a parser that produces DOM object trees from XML documents.
        DocumentBuilderFactory crunchifyDocBuilderFactory = DocumentBuilderFactory.newInstance();

        // Defines the API to obtain DOM Document instances from an XML document.
        DocumentBuilder crunchifyDocBuilder;
        try {
            crunchifyDocBuilder = crunchifyDocBuilderFactory.newDocumentBuilder();

            // The Document interface represents the entire HTML or XML document.
            Document crunchifyDoc = crunchifyDocBuilder.newDocument();

            // The Element interface represents an element in an HTML or XML document.
            Element mainRootElement = crunchifyDoc.createElementNS("https://crunchify.com/CrunchifyCreateXMLDOM", "Companies");

            // Adds the node newChild to the end of the list of children of this node.
            // If the newChild is already in the tree, it is first removed.
            crunchifyDoc.appendChild(mainRootElement);

            // append child elements to root element
            mainRootElement.appendChild(getCompany(crunchifyDoc, "1", "Paypal", "Payment", "1000"));
            mainRootElement.appendChild(getCompany(crunchifyDoc, "2", "Amazon", "Shopping", "2000"));
            mainRootElement.appendChild(getCompany(crunchifyDoc, "3", "Google", "Search", "3000"));
            mainRootElement.appendChild(getCompany(crunchifyDoc, "4", "Crunchify", "Java Tutorials", "10"));

            // output DOM XML to console

            // An instance of this abstract class can transform a source tree into a result tree.
            Transformer crunchifyTransformer = TransformerFactory.newInstance().newTransformer();
            crunchifyTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Acts as a holder for a transformation Source tree in the form of a Document Object Model (DOM) tree.
            DOMSource source = new DOMSource(crunchifyDoc);

            // Acts as an holder for a transformation result, which may be XML, plain Text, HTML, or some other form of markup.
            StreamResult console = new StreamResult(System.out);
            crunchifyTransformer.transform(source, console);

            System.out.println("\nTutorial by Crunchify. XML DOM Created Successfully..");

        } catch (TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    // The Node interface is the primary datatype for the entire Document Object Model.
    // It represents a single node in the document tree.
    private static Node getCompany(Document doc, String id, String name, String age, String role) {
        Element crunchifyCompany = doc.createElement("Company");
        crunchifyCompany.setAttribute("id", id);
        crunchifyCompany.appendChild(getCrunchifyCompanyElements(doc, crunchifyCompany, "Name", name));
        crunchifyCompany.appendChild(getCrunchifyCompanyElements(doc, crunchifyCompany, "Type", age));
        crunchifyCompany.appendChild(getCrunchifyCompanyElements(doc, crunchifyCompany, "Employees", role));
        return crunchifyCompany;
    }

    // Utility method to create text node
    private static Node getCrunchifyCompanyElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}