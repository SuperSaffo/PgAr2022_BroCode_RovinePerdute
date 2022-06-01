package it.unibs.fp.rovinePerdute;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Classe per il controllo della squadra Tonatiuh, eredita da Squad
 */
public class Tonatiuh extends Squad {
    /**
     * Nome della squadra
     */
    private static final String NAME = "Tonatiuh";

    /**
     * Costruttore della classe
     */
    public Tonatiuh() {
        super(NAME);
    }

    /**
     * Metodo per settare i valori della squadra Tonatiuh
     * <p>Viene settato il cityPath, viene creato ArrayList delle citta' del percorso,
     * viene settato il costo del percorso, viene settato il numero di citta' per cui si passa</p>
     * @see Squad#setCityPath(ArrayList)
     * @see Path#getPathEuclideo() ()
     * @see Squad#addCity(City)
     * @see Map#getCityByKey(int)
     * @see Squad#setCost(double)
     * @see Path#getCost()
     * @see Squad#setnCities(int)
     * @see Squad#getCityPath()
     */
    public void findPath() {
        setCityPath(Path.getPathEuclideo());

        for(int key : getCityPath()) {
            addCity(Map.getCityByKey(key));
        }

        setCost(Path.getCost());

        setnCities(getCityPath().size());
    }


}
