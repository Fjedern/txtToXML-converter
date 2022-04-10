package com.company;

import java.io.*;

// SAX classes.
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.sax.*;

public class TextToXMLv2  {

    BufferedReader in;
    StreamResult out;

    TransformerHandler th;
    AttributesImpl atts;

    public void start () {
        try{
            in = new BufferedReader(new FileReader("data.txt"));
            out = new StreamResult("data.xml");
            initXML();
            String str;
            while ((str = in.readLine()) != null) {
                process(str);
            }
            in.close();
            closeXML();
        }
        catch (Exception e) { e.printStackTrace(); }
    }


    public void initXML() throws ParserConfigurationException,
            TransformerConfigurationException, SAXException {
        // JAXP + SAX
        SAXTransformerFactory tf =
                (SAXTransformerFactory) SAXTransformerFactory.newInstance();

        th = tf.newTransformerHandler();
        Transformer serializer = th.getTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
        // pretty XML output
        serializer.setOutputProperty
                ("{http://xml.apache.org/xslt}indent-amount", "4");
        serializer.setOutputProperty(OutputKeys.INDENT,"yes");
        th.setResult(out);
        th.startDocument();
        atts = new AttributesImpl();
        th.startElement("","","HOWTOS",atts);
    }

    public void process (String s) throws SAXException {
        String [] elements = s.split("\\|");
        atts.clear();
        th.startElement("","","TOPIC",atts);

        th.startElement("","","TITLE",atts);
        th.characters(elements[0].toCharArray(),0,elements[0].length());
        th.endElement("","","TITLE");

        th.startElement("","","URL",atts);
        th.characters(elements[1].toCharArray(),0,elements[1].length());
        th.endElement("","","URL");

        th.endElement("","","TOPIC");
    }

    public void closeXML() throws SAXException {
        th.endElement("","","HOWTOS");
        th.endDocument();  }
}