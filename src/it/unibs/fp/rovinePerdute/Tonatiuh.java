package it.unibs.fp.rovinePerdute;

import java.util.TreeMap;

//DISTANZA EUCLIDEA
public class Tonatiuh extends Squad {
    private static final String NAME = "Tonatiuh";

    public Tonatiuh(TreeMap<Integer, City> map) {
        super(NAME, map);
    }

    public void findPath() {
        //CITY PATH
        setCityPath(Path.getPathEuclideo());
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
