package it.unibs.fp.rovinePerdute;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Classe squadra generica
 */
public class Squad {
    /**
     * Nome del team
     */
    private String team;
    /**
     * Carburante utilizzato
     */
    private double cost;
    /**
     * ID delle citta' del percorso
     */
    private ArrayList<Integer> cityPath = new ArrayList<>();
    /**
     * Citta' del percorso
     */
    private ArrayList<City> city = new ArrayList<>();
    /**
     * Numero di citta' del percorso
     */
    private int nCities;

    /**
     * Costruttore della classe squadra
     * @param team rappresenta il nome del team
     */
    public Squad(String team) {
        this.team = team;
    }

    /**
     * Getter del nome del team
     * @return Ritorna il nome del team
     */
    public String getTeam() {
        return team;
    }

    /**
     * Getter del costo del percorso (carburante utilizzato)
     * @return Ritorna il costo del percorso
     */
    public double getCost() {
        return cost;
    }

    /**
     * Getter dell'ArrayList degli ID delle citta' del percorso
     * @return Ritorna l'arrayList ci ID delle città del percorso
     */
    public ArrayList<Integer> getCityPath() {
        return cityPath;
    }

    /**
     * Getter dell'ID di una citta nell'ArrayList diID dato un indice
     * @param index Indice dell'ID
     * @return Ritorna l'ID di una città data la sua posizione nell'ArrayList
     */
    public int getCityId(int index) {
        return cityPath.get(index);
    }

    /**
     * Getter dell'ArrayList di Citta' del percorso
     * @return Ritorna l'ArrayList di città del percorso
     */
    public ArrayList<City> getCity() {
        return city;
    }

    /**
     * Getter del numero di citta' del percorso
     * @return Ritorna il numero di città percorse
     */
    public int getnCities() {
        return nCities;
    }

    /**
     * Setter del costo del percorso
     * @param cost Quantità di carburante utilizzata
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Setter dell'ArrayList degli ID delle citta' percorse
     * @param cityPath ArrayList di ID
     */
    public void setCityPath(ArrayList<Integer> cityPath) {
        this.cityPath = cityPath;
    }

    /**
     * Metodo per aggiungere una nuova città Nell'ArrayList di citta'
     * @param c Nuova citta' da aggiungere
     * @see Squad#city
     */
    public void addCity(City c) {
        this.city.add(c);
    }

    /**
     * Setter del numero città attraversate
     * @param nCities Numero di città attraversate
     */
    public void setnCities(int nCities) {
        this.nCities = nCities;
    }
}
