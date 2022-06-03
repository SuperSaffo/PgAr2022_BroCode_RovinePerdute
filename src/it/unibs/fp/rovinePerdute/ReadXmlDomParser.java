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
 * Classe per lettura di un file tramite DOMParser
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
     * @return Ritorna la scelta
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
     * @return Ritorna il file
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
     * Metodo per la lettura del file contenente le citta'
     * <p>Le citta' vengono inserite in una TreeMap, ogni citta' ha un codice univoco (ID)</p>
     * @return Ritorna la TreeMap di citta'
     */
    public static TreeMap<Integer, City> creaMappa() {
        TreeMap<Integer, City> map = new TreeMap<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(nomeFile()));

            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("city");

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    City newCity = new City();

                    Element element = (Element) node;

                    //Attributi di city
                    newCity.setId(Integer.parseInt(element.getAttribute("id")));
                    newCity.setName(element.getAttribute("name"));
                    newCity.setX(Integer.parseInt(element.getAttribute("x")));
                    newCity.setY(Integer.parseInt(element.getAttribute("y")));
                    newCity.setH(Integer.parseInt(element.getAttribute("h")));

                    //Lettura di link
                    ArrayList<Integer> links = new ArrayList<>();
                    NodeList linkNodeList = element.getElementsByTagName("link");

                    for(int j = 0; j < linkNodeList.getLength(); j++) {
                        Node linkNode =  linkNodeList.item(j);
                        //Attributi di link
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
