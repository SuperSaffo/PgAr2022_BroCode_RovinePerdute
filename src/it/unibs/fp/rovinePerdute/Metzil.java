package it.unibs.fp.rovinePerdute;

import java.util.TreeMap;

public class Metzil extends Squad {
    private static final String NAME = "Metzil";

    public Metzil(TreeMap<Integer, City> map) {
        super(NAME, map);
    }

    public void findPath() {
        //CITY PATH
        setCityPath(Path.getPathAltitude());
        //LIST OF CITIES
        for(int key : getCityPath()) {
            addCity(getMap().get(key));
        }
        //COST
        setCost(Path.getCost());
        //NUMBER OF CITIES
        setnCities(getCityPath().size());
    }
}
