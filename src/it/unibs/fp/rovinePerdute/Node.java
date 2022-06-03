package it.unibs.fp.rovinePerdute;

import java.util.*;

/**
 * Classe per creazione dei nodi
 */
public class Node implements Comparable<Node>{
    /**
     * ID del nodo
     */
    public int id;
    /**
     * Citta del nodo
     */
    public City city;
    /**
     * Parent viene istanziato al nodo precedente nel percorso
     */
    public Node parent = null;
    /**
     * Nodi a cui e collegato il nodo
     */
    public ArrayList<Edge> neighbors;
    /**
     * Funzione di A*
     */
    public double f;
    /**
     * Distanza dal nodo di partenza
     */
    public double g;
    /**
     * Distanza euristica del nodo dall'arrivo
     */
    public double h;

    /**
     * Costruttore del nodo
     *
     * @see City
     * @param city Citta' del nodo
     */
    Node(City city){
        this.city = city;
        this.id = city.getId();
        this.neighbors = new ArrayList<>();
    }

    /**
     * Metodo compareTo per le priorityList per l'algoritmo di A*
     * <p>Il confronto viene fatto sulla "f", la f minore ha priorita'</p>
     *
     * @see Node#f
     * @param n Nodo da confrontare
     * @return Ritorna risultato del confronto
     */
    @Override
    public int compareTo(Node n) {
        return Double.compare(this.f, n.f);
    }

    /**
     * Classe per i Nodi figli di ogni nodo
     */
    public static class Edge {
        /**
         * Distanza euclidea da nodo Padre
         */
        public double weightD;
        /**
         * Differenza di altitudine da nodo Padre
         */
        public double weightH;
        /**
         * Nodo figlio
         */
        public Node node;

        /**
         * Costruttore del nodo figlio
         *
         * @param weightD Distanza euclidea da nodo padre
         * @param weightH Differenza di altitudine da nodo padre
         * @param node Nodo figlio
         */
        Edge(double weightD, double weightH, Node node){
            this.weightD = weightD;
            this.weightH = weightH;
            this.node = node;
        }
    }

    /**
     * Metodo per aggiungere un nodo figlio
     * @param weightD Distanza euclidea da nodo padre
     * @param weightH Differenza di altitudine da nodo padre
     * @param node Nodo figlio
     */
    public void addBranch(double weightD, double weightH, Node node){
        Edge newEdge = new Edge(weightD, weightH, node);
        neighbors.add(newEdge);
    }

    /**
     * Calcolo della distanza euclidea tra 2 nodi
     * @param target Nodo di arrivo
     * @return Ritorna la distanza tra i 2 nodi
     */
    public double calculateHeuristicEuclidean(Node target){
        return Math.sqrt(Math.pow(target.city.getX() - this.city.getX(), 2) + Math.pow(target.city.getY() - this.city.getY(), 2));
    }

    /**
     * Calcolo del modulo della differenza di altitudine tra 2 nodi
     * @param target Nodo di arrivo
     * @return Ritorna il modulo della differenza di altitudine tra i 2 nodi
     */
    public double calculateHeuristicAltitude(Node target){
        return Math.abs(target.city.getH() - this.city.getH());
    }

}
