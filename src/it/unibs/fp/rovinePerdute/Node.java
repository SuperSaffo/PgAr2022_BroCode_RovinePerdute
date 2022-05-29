package it.unibs.fp.rovinePerdute;

import java.util.*;

public class Node implements Comparable<Node>{
    // Id for readability of result purposes
    public int id;
    public City city;

    // Parent in the path
    public Node parent = null;

    public ArrayList<Edge> neighbors;

    // Evaluation functions
    public double f;
    public double g;
    public double h;

    Node(City city){
        this.city = city;
        this.id = city.getId();
        this.neighbors = new ArrayList<>();
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(this.f, n.f);
    }

    public static class Edge {
        public double weight;
        public Node node;

        Edge(double weight, Node node){
            this.weight = weight;
            this.node = node;
        }
    }

    public void addBranch(double weight, Node node){
        Edge newEdge = new Edge(weight, node);
        neighbors.add(newEdge);
    }

    public double calculateHeuristicEuclideo(Node target){
        return Math.sqrt(Math.pow(target.city.getX() - this.city.getX(), 2) + Math.pow(target.city.getY() - this.city.getY(), 2));
    }

    public double calculateHeuristicAltitude(Node target){
        return Math.abs(target.city.getH() - this.city.getH());
    }

}
