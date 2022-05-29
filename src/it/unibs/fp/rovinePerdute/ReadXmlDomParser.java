package it.unibs.fp.rovinePerdute;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class ReadXmlDomParser {
    private static final String FILENAME = "PgAr_Map_5.xml";
    //private static final String FILENAME = "PgAr_Map_12.xml";
    //private static final String FILENAME = "PgAr_Map_50.xml";
    //private static final String FILENAME = "PgAr_Map_200.xml";
    //private static final String FILENAME = "PgAr_Map_2000.xml";
    //private static final String FILENAME = "PgAr_Map_10000.xml";

    public static TreeMap<Integer, City> creaMappa() {
        TreeMap<Integer, City> map = new TreeMap<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(FILENAME));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("city");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    City newCity = new City();

                    Element element = (Element) node;

                    // get city attributes
                    newCity.setId(Integer.parseInt(element.getAttribute("id")));
                    newCity.setName(element.getAttribute("name"));
                    newCity.setX(Integer.parseInt(element.getAttribute("x")));
                    newCity.setY(Integer.parseInt(element.getAttribute("y")));
                    newCity.setH(Integer.parseInt(element.getAttribute("h")));

                    //LETTURA DEI LINK
                    ArrayList<Integer> links = new ArrayList<>();
                    NodeList linkNodeList = element.getElementsByTagName("link");

                    for(int i = 0; i < linkNodeList.getLength(); i++) {
                        Node linkNode =  linkNodeList.item(i);
                        if(linkNode.getNodeType() == linkNode.ELEMENT_NODE) {
                            Element e = (Element) linkNode;
                            links.add(Integer.parseInt(e.getAttribute("to")));
                        }
                    }
                    newCity.setLink(links);

                    map.put(newCity.getId(), newCity);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return map;
    }

}
