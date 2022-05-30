package it.unibs.fp.rovinePerdute;

import java.util.*;

public class Node implements Comparable<Node>{
    // Id for readability of result purposes
    public int id;
    public City city;
    public Node parent = null;
    public ArrayList<Edge> neighbors;
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
        public double weightD;
        public double weightH;
        public Node node;

        Edge(double weightD, double weightH, Node node){
            this.weightD = weightD;
            this.weightH = weightH;
            this.node = node;
        }
    }

    public void addBranch(double weightD, double weightH, Node node){
        Edge newEdge = new Edge(weightD, weightH, node);
        neighbors.add(newEdge);
    }

    public double calculateHeuristicEuclidean(Node target){
        return Math.sqrt(Math.pow(target.city.getX() - this.city.getX(), 2) + Math.pow(target.city.getY() - this.city.getY(), 2));
    }

    public double calculateHeuristicAltitude(Node target){
        return Math.abs(target.city.getH() - this.city.getH());
    }

}
