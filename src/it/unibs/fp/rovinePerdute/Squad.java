package it.unibs.fp.rovinePerdute;

import java.util.ArrayList;

public class Squad {
    String team;
    double cost;
    Map map;
    ArrayList<City> city;
    int cities = city.size();

    public Squad(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public double getCost() {
        return cost;
    }

    public Map getMap() {
        return map;
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

    public void setMap(Map map) {
        this.map = map;
    }

    public void setCity(ArrayList<City> city) {
        this.city = city;
    }

    public void setCities(int cities) {
        this.cities = cities;
    }
}
