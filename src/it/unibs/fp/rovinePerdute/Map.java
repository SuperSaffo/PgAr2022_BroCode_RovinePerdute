package it.unibs.fp.rovinePerdute;

import java.util.TreeMap;
//DISLIVELLO
public class Map {
    private static TreeMap<Integer, City> cityMap = ReadXmlDomParser.creaMappa();

    public static TreeMap<Integer, City> getCityMap() {
        return cityMap;
    }

    public static City getCityByKey(int key) {
        return cityMap.get(key);
    }
}
