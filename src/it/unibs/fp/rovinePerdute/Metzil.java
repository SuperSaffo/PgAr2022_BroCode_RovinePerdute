package it.unibs.fp.rovinePerdute;

import java.util.TreeMap;

/**
 * Classe per il controllo della squadra Metzil, eredita da Squad
 */
public class Metzil extends Squad {
    /**
     * Nome della squadra
     */
    private static final String NAME = "Metzil";

    /**
     * COstruttore della classe
     * @param map TreeMap contenente le citta
     */
    public Metzil(TreeMap<Integer, City> map) {
        super(NAME, map);
    }

    public void findPath() {
        setCityPath(Path.getPathAltitude());

        for(int key : getCityPath()) {
            addCity(Map.getCityByKey(key));
        }

        setCost(Path.getCost());

        setnCities(getCityPath().size());
    }
}
