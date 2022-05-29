package it.unibs.fp.rovinePerdute;

import java.util.ArrayList;
import java.util.TreeMap;

public class Squad {
    private String team;
    private double cost;
    private TreeMap<Integer, City> map;
    private ArrayList<Integer> cityPath = new ArrayList<>();
    private ArrayList<City> city = new ArrayList<>();
    private int nCities;

    public Squad(String team, TreeMap<Integer, City> map) {
        this.team = team;
        this.map = map;
    }

    public String getTeam() {
        return team;
    }

    public double getCost() {
        return cost;
    }

    public ArrayList<Integer> getCityPath() {
        return cityPath;
    }

    public int getCityId(int index) {
        return cityPath.get(index);
    }

    public ArrayList<City> getCity() {
        return city;
    }

    public int getnCities() {
        return nCities;
    }

    public TreeMap<Integer, City> getMap() {
        return map;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCityPath(ArrayList<Integer> cityPath) {
        this.cityPath = cityPath;
    }

    public void setCity(ArrayList<City> city) {
        this.city = city;
    }

    public void addCity(City c) {
        this.city.add(c);
    }

    public void setnCities(int nCities) {
        this.nCities = nCities;
    }
}
