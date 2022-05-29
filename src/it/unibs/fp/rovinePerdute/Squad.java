package it.unibs.fp.rovinePerdute;

import java.util.ArrayList;
import java.util.TreeMap;

public class Squad {
    private String team;
    private double cost;
    private ArrayList<Integer> cityPath;
    private ArrayList<City> city;
    private int cities = city.size();

    public Squad(String team) {
        this.team = team;
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

    public ArrayList<City> getCity() {
        return city;
    }

    public int getCities() {
        return cities;
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

    public void setCities(int cities) {
        this.cities = cities;
    }
}
