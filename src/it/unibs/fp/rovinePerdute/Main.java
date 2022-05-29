package it.unibs.fp.rovinePerdute;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Collection;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        TreeMap<Integer, City> map = ReadXmlDomParser.creaMappa();

        Tonatiuh tonatiuh = new Tonatiuh(map);
        Metzil metzil = new Metzil(map);
        tonatiuh.findPath();
        metzil.findPath();
        WriteXmlDomParser.printRoutes(tonatiuh, metzil);
    }
}
