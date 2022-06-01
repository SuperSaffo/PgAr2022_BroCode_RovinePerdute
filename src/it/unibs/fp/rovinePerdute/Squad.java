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
    private TreeMap<Integer, City> map;
    private ArrayList<Integer> cityPath = new ArrayList<>();
    private ArrayList<City> city = new ArrayList<>();
    private int nCities;

    /**
     * Costruttore della classe squadra
     * @param team rappresenta il nome del team
     * @param map mappa chiave valore
     */
    public Squad(String team, TreeMap<Integer, City> map) {
        this.team = team;
        this.map = map;
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
     * Metodo che ID per una città
     * @param index variabile indice
     * @return ritorna l'ID di una città
     */
    public int getCityId(int index) {
        return cityPath.get(index);
    }

    /**
     * Getter city
     * @return ritorna un'arrayList di città
     */
    public ArrayList<City> getCity() {
        return city;
    }

    /**
     * Getter nCity
     * @return ritorna il numero di città percorse
     */
    public int getnCities() {
        return nCities;
    }

    /**
     * Getter getMap
     * @return ritorna una mappa chiave(Integer) valore(citta)
     */
    public TreeMap<Integer, City> getMap() {
        return map;
    }

    /*
    public void setTeam(String team) {
        this.team = team;
    }
     */

    /**
     * Setter cost
     * <p>Set carburante utilizzato</p>
     * @param cost quantità carburante
     */
    public void setCost(double cost) {
        this.cost = cost;
    }


    public void setCityPath(ArrayList<Integer> cityPath) {
        this.cityPath = cityPath;
    }

    /**
     * Setter city
     * <p>Set city in un arrayLIst</p>
     * @param city
     */
    public void setCity(ArrayList<City> city) {
        this.city = city;
    }

    /**
     * Metodo per aggiungere un nuovo città all'arrayList
     * @param c nuova città
     */
    public void addCity(City c) {
        this.city.add(c);
    }

    /**
     * Set numero città attraversate
     * @param nCities numero città
     */
    public void setnCities(int nCities) {
        this.nCities = nCities;
    }
}
