package it.unibs.fp.rovinePerdute;

import java.util.TreeMap;

/**
 * Classe per la creazione della mappa
 */
public class Map {
    /**
     * TreeMap Contenente le citta'
     * @see ReadXmlDomParser#creaMappa()
     */
    private static TreeMap<Integer, City> cityMap = ReadXmlDomParser.creaMappa();

    /**
     * Getter della mappa
     * @return Ritorna la TreeMap di citta'
     */
    public static TreeMap<Integer, City> getCityMap() {
        return cityMap;
    }

    /**
     * Gettere di una citta' data la sua chiave
     * @param key Chiave della citta
     * @return Ritorna la citta identificata dalla chiave
     */
    public static City getCityByKey(int key) {
        return cityMap.get(key);
    }
}
