package it.unibs.fp.rovinePerdute;

import it.unibs.fp.mylib.MyMenu;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Collection;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        TreeMap<Integer, City> map = Map.getCityMap();

        Tonatiuh tonatiuh = new Tonatiuh();
        Metzil metzil = new Metzil();
        tonatiuh.findPath();
        metzil.findPath();
        WriteXmlDomParser.printRoutes(tonatiuh, metzil);
    }
}
