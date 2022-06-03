package it.unibs.fp.rovinePerdute;

import java.util.ArrayList;

/**
 * Classe per creazione di una citta'
 */
public class City {
    /**
     * ID della citta'
     */
    private int id;
    /**
     * Nome della citta'
     */
    private String name;
    /**
     * Coordinata X della citta'
     */
    private int x;
    /**
     * Coordinata Y della citta'
     */
    private int y;
    /**
     * Altitudine della citta'
     */
    private int h;
    /**
     * Lista degli ID delle citta' a cui e' collegata
     */
    private ArrayList<Integer> link;

    /**
     * Costruttore vuoto di city
     */
    public City() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public ArrayList<Integer> getLink() {
        return link;
    }

    public int getLinkByIndex(int index) {
        return link.get(index);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setLink(ArrayList<Integer> link) {
        this.link = link;
    }

    public void printCity() {
        System.out.println(id);
        System.out.println(name);
        System.out.println(x + "\t" + y + "\t" + h);
        for(int i = 0; i < link.size(); i++)
            System.out.println(link.get(i));
    }
}
