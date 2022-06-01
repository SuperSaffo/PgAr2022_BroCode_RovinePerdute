package it.unibs.fp.rovinePerdute;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Classe squadra Tonatiuh
 * <p>Eredità da classe Squad</p>
 */

//DISTANZA EUCLIDEA
public class Tonatiuh extends Squad {
    private static final String NAME = "Tonatiuh";

    /**
     * Costruttrore classe Tonatiuh
     * @param map mappa
     */
    public Tonatiuh(TreeMap<Integer, City> map) {
        super(NAME, map);
    }

    /**
     * Metodo che per la squadra Tonatiuh
     * <p>Sett l'arrayList di ID</p>
     * <p>Agiiunge le città alla map</p>
     * <p>Set costo carburante</p>
     * <p>Set numero città visitate</p>
     * @see Squad#setCityPath(ArrayList)
     * @see Squad#getMap() 
     * @see Squad#setCost(double)#getCost()
     * @see Squad#setnCities(int)#getCityPath()  
     */
    public void findPath() {
        setCityPath(Path.getPathEuclideo());

        for(int key : getCityPath()) {
            addCity(getMap().get(key));
        }

        setCost(Path.getCost());

        setnCities(getCityPath().size());
    }


}
