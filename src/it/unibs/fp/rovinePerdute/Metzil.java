package it.unibs.fp.rovinePerdute;

import java.util.ArrayList;
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
    public Metzil() {
        super(NAME);
    }

    /**
     * Metodo per settare i valori della squadra Metzil
     * <p>Viene settato il cityPath, viene creato ArrayList delle citta' del percorso,
     * viene settato il costo del percorso, viene settato il numero di citta' per cui si passa</p>
     * @see Squad#setCityPath(ArrayList)
     * @see Path#getPathAltitude()
     * @see Squad#addCity(City)
     * @see Map#getCityByKey(int)
     * @see Squad#setCost(double)
     * @see Path#getCost()
     * @see Squad#setnCities(int)
     * @see Squad#getCityPath()
     */
    public void findPath() {
        setCityPath(Path.getPathAltitude());

        for(int key : getCityPath()) {
            addCity(Map.getCityByKey(key));
        }

        setCost(Path.getCost());

        setnCities(getCityPath().size());
    }
}
