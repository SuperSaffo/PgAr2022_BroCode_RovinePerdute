package it.unibs.fp.rovinePerdute;

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
import java.util.ArrayList;

public class WriteXmlDomParser {

    public static void printRoutes(Tonatiuh t, Metzil m) throws ParserConfigurationException, TransformerException {
        ArrayList<Squad> squads = new ArrayList<>();
        squads.add(t);
        squads.add(m);

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("routes");
        doc.appendChild(rootElement);

        // add xml elements
        for(Squad s : squads) {
            Element route = doc.createElement("route");
            rootElement.appendChild(route);

            route.setAttribute("team", s.getTeam());
            route.setAttribute("cost", String.format("%.2f", s.getCost()));
            route.setAttribute("cities", Integer.toString(s.getnCities()));

            for(int i = 0; i < s.getnCities(); i++){
                int key = s.getCityId(i);
                Element city = doc.createElement("city");
                route.appendChild(city);
                city.setAttribute("id", Integer.toString(key));
                city.setAttribute("name", Map.getCityByKey(key).getName());
            }
        }

        writeXml(doc, System.out);

        try (FileOutputStream output = new FileOutputStream("Routes.xml")) {
            writeXml(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeXml(Document doc, OutputStream output) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }
}
