package com.company.toXML;

import java.io.*;
import java.util.ArrayList;

// SAX classes.
import com.company.personClasses.Adress;
import com.company.personClasses.FamilyMember;
import com.company.personClasses.Person;
import com.company.personClasses.Phone;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.sax.*;

public class TextToXML {

    BufferedReader in;
    StreamResult out;

    TransformerHandler th;
    AttributesImpl atts;

    String openAndCloseTag = "people";
    ArrayList<Person> people = new ArrayList<>();
    
    public void start() {
        try {
            in = new BufferedReader(new FileReader("data.txt"));
            out = new StreamResult("data.xml");
            initXML();

            boolean insideP = false;
            boolean insideF = false;
            String str;
            String[] splitList;
            Person lastPerson;
            FamilyMember lastFamilyMember;
            ArrayList<FamilyMember> familyMembers;
            while((str = in.readLine()) != null){
                splitList = str.split("\\|");

                switch (splitList[0]){
                    case "P":
                        insideP = true;
                        insideF = false;
                        Person tmpPerson = new Person(splitList[1], splitList[2]);
                        people.add(tmpPerson);
                        break;
                    case "F":
                        insideF = true;
                        lastPerson = people.get(people.size() - 1);
                        lastPerson.addFamilyMember(new FamilyMember(splitList[1], splitList[2]));
                        break;
                    case "T":
                        if(insideP && !insideF){
                            lastPerson = people.get(people.size() - 1);
                            lastPerson.setPhone(new Phone(splitList[1], splitList[2]));
                        }else if(insideF && insideP){
                            lastPerson = people.get(people.size() - 1);
                            familyMembers = lastPerson.getFamilyMembers();
                            lastFamilyMember = familyMembers.get(familyMembers.size() - 1);
                            lastFamilyMember.setPhone(new Phone(splitList[1], splitList[2]));
                        }
                        break;
                    case "A":
                        if(insideP && !insideF){
                            lastPerson = people.get(people.size() - 1);
                            lastPerson.setAdress(new Adress(splitList[1], splitList[2], splitList[3]));
                        } else if(insideF && insideP){
                            lastPerson = people.get(people.size() - 1);
                            familyMembers = lastPerson.getFamilyMembers();
                            lastFamilyMember = familyMembers.get(familyMembers.size() - 1);
                            lastFamilyMember.setAdress(new Adress(splitList[1], splitList[2], splitList[3]));
                        }
                        break;
                    default:
                        System.out.println("Something is broken");
                        break;
                }
            }

            writeToXML();
            in.close();
            for (Person person : people) {
                System.out.println(person);
            }
            closeXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void writeToXML() throws SAXException {
        atts.clear();
        for(Person person : people){
            th.startElement(null, null, "person", atts);

            xmlHelp("firstname", person.getFirstname());
            xmlHelp("lastname", person.getLastname());

            if(person.getAdress() != null) {
                th.startElement(null, null, "adress", atts);

                xmlHelp("street", person.getAdress().getStreet());
                xmlHelp("city", person.getAdress().getCity());
                xmlHelp("zipcode", person.getAdress().getZipcode());

                th.endElement(null, null, "adress");
            }

            if(person.getPhone() != null) {
                th.startElement(null, null, "phone", atts);

                xmlHelp("mobile", person.getPhone().getPhoneNumber());
                xmlHelp("landline", person.getPhone().getLandline());

                th.endElement(null, null, "phone");
            }

            if(person.getFamilyMembers() != null) {
                for(FamilyMember familyMember : person.getFamilyMembers()){
                    th.startElement(null, null, "family", atts);

                    xmlHelp("name", familyMember.getName());
                    xmlHelp("born", familyMember.getBirth());

                    if(familyMember.getAdress() != null){
                        th.startElement(null, null, "adress", atts);

                        xmlHelp("street", familyMember.getAdress().getStreet());
                        xmlHelp("city", familyMember.getAdress().getCity());
                        xmlHelp("zipcode", familyMember.getAdress().getZipcode());

                        th.endElement(null, null, "adress");
                    }

                    if(familyMember.getPhone() != null){
                        th.startElement(null, null, "phone", atts);

                        xmlHelp("mobile", familyMember.getPhone().getPhoneNumber());
                        xmlHelp("landline", familyMember.getPhone().getLandline());

                        th.endElement(null, null, "phone");
                    }

                    th.endElement(null, null, "family");
                }
            }

            th.endElement(null, null, "person");

        }
    }

    public void xmlHelp(String name, String item) throws SAXException {
        th.startElement(null, null, name, atts);
        th.characters(item.toCharArray(), 0, item.length());
        th.endElement(null, null, name);
    }

    public void initXML() throws ParserConfigurationException,
            TransformerConfigurationException, SAXException {
        // JAXP + SAX
        SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

        th = tf.newTransformerHandler();
        Transformer serializer = th.getTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        // pretty XML output
        serializer.setOutputProperty
                ("{http://xml.apache.org/xslt}indent-amount", "4");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        th.setResult(out);
        th.startDocument();
        atts = new AttributesImpl();
        th.startElement("", "", openAndCloseTag, atts);
    }

    public void closeXML() throws SAXException {
        th.endElement("", "", openAndCloseTag);
        th.endDocument();
    }
}