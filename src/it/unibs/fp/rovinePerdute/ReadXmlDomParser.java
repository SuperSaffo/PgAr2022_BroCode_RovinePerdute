package it.unibs.fp.rovinePerdute;

import it.unibs.fp.mylib.MyMenu;
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

/**
 * Classe per lettura file tramite DOMParser
 */

public class ReadXmlDomParser {

    // FILE
    private static final String FILENAME_MAP_5 = "PgAr_Map_5.xml";
    private static final String FILENAME_MAP_12= "PgAr_Map_12.xml";
    private static final String FILENAME_MAP_50 = "PgAr_Map_50.xml";
    private static final String FILENAME_MAP_200 = "PgAr_Map_200.xml";
    private static final String FILENAME_MAP_2000 = "PgAr_Map_2000.xml";
    private static final String FILENAME_MAP_10000= "PgAr_Map_10000.xml";

    /**
     * Metodo per scelta file da utilizzare all'interno del programma
     * @see MyMenu#MyMenu(String, String[])
     * @see MyMenu#scegli()
     * @return ritorna la scelta
     */

    public static int sceltaMappa() {
        MyMenu menuMappe = new MyMenu("Scegli dimensione mappa:", new String[]{"5 citta", "12 citta", "50 citta", "200 citta", "2000 citta", "10000 citta"});
        int scelta;
        do{
            scelta = menuMappe.scegli();
        }while(scelta == 0);
        return scelta;
    }

    /**
     * Metodo per selezionare il file scelto
     * @see #sceltaMappa()
     * @return ritorna il file
     */
    public static String nomeFile() {
        int scelta = sceltaMappa();

        switch(scelta) {
            case 1:
                return FILENAME_MAP_5;
            case 2:
                return FILENAME_MAP_12;
            case 3:
                return FILENAME_MAP_50;
            case 4:
                return FILENAME_MAP_200;
            case 5:
                return FILENAME_MAP_2000;
            case 6:
                return FILENAME_MAP_10000;
            default:
                break;
        }
        return FILENAME_MAP_5;
    }

    /**
     * Metodo per la lettura del file
     * <p> Dopo la lettura salva i dati all'interno di una mappa</p>
     * @return ritorna la mappa
     */
    public static TreeMap<Integer, City> creaMappa() {
        TreeMap<Integer, City> map = new TreeMap<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(nomeFile()));

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
