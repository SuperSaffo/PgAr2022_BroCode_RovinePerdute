package it.unibs.fp.rovinePerdute;

import java.util.*;

public class Path {
    public static double cost = 0;
    public static TreeMap<Integer, City> map = Map.getCityMap();
    public static TreeMap<Integer, Node> nodes = new TreeMap<>();

    public static double getCost() {
        return cost;
    }

    public static void setCost(double cost) {
        Path.cost = cost;
    }

    /**
     * Metodo per creare i nodi
     * <p>Se l'ArrayList di nodi esiste gia' vengono azzerati: parent, f, g, h</p>
     * <p>Se l'ArrayList e' vuoto per ogni citta' viene creato un  nodo</p>
     * <p>Per ogni nodo vengono aggiunti i nodi a cui e' legato</p>
     *
     * @see Node
     * @see City
     * @see it.unibs.fp.rovinePerdute.Node.Edge
     * @see Node#addBranch(double, double, Node)
     * @return Ritorna ArrayList di nodi
     */
    public static TreeMap<Integer, Node> createNodes() {
        if(nodes.size() != 0) {
            Collection<Node> nodesC = nodes.values();
            nodesC.forEach(value -> value.parent = null);
            nodesC.forEach(value -> value.f = 0);
            nodesC.forEach(value -> value.g = 0);
            nodesC.forEach(value -> value.h = 0);
            return nodes;
        }

        Collection<City> cities = map.values();
        cities.forEach(value -> nodes.put(value.getId(), new Node(value)));

        for(int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            for(int j = 0; j < n.city.getLink().size(); j++) {
                Node branch = nodes.get(n.city.getLinkByIndex(j));
                n.addBranch(n.calculateHeuristicEuclidean(branch), n.calculateHeuristicAltitude(branch), branch);
            }
        }

        return nodes;
    }

    /**
     * Metodo per leggere il percorso e inserirlo in un ArrayList
     * <p>Viene salvato il costo totale del percorso</p>
     * <p>Vengono fatti scorrere i nodi dal punto di arrivo al punto di partenza con la variabile parent</p>
     * <p>L'ArrayList degli ID del percorso viene invertito, in modo da andare dal punto di partenza al punto di arrivo</p>
     *
     * @param target Nodo di arrivo
     * @see Squad#setCost(double)
     * @see Node#parent
     * @see Collections#reverse(List)
     * @return Ritorna un ArrayLIst con gli ID delle citta'
     */
    public static ArrayList<Integer> createPath(Node target){
        Node n = target;
        if(n == null)
            return null;

        setCost(n.f);
        ArrayList<Integer> path = new ArrayList<>();

        while(n.parent != null){
            path.add(n.id);
            n = n.parent;
        }
        path.add(n.id);
        Collections.reverse(path);

        return path;
    }

    //SQUADRA TONATIUH
    public static Node aStarEuclidean(Node start, Node target){
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();

        start.f = start.g + start.calculateHeuristicEuclidean(target);
        openList.add(start);

        while(!openList.isEmpty()){
            Node n = openList.peek();
            if(n == target){
                return n;
            }

            for(Node.Edge edge : n.neighbors){
                Node m = edge.node;
                double totalWeight = n.g + edge.weightD;

                if(!openList.contains(m) && !closedList.contains(m)){
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calculateHeuristicEuclidean(target);
                    openList.add(m);
                } else {
                    if(totalWeight < m.g){
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calculateHeuristicEuclidean(target);

                        if(closedList.contains(m)){
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }

    public static ArrayList<Integer> getPathEuclideo() {
        TreeMap<Integer, Node> nodes = createNodes();
        Collection<Node> nodesC = nodes.values();
        nodesC.forEach(value -> value.parent = null);

        Node head = nodes.get(0);
        head.g = 0;

        Node target = nodes.get(nodes.size() - 1);


        Node res = aStarEuclidean(head, target);
        return createPath(res);
    }

    //SQUADRA METZIL
    public static Node aStarAltitude(Node start, Node target){
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();

        start.f = start.g + start.calculateHeuristicAltitude(target);
        openList.add(start);

        while(!openList.isEmpty()){
            Node n = openList.peek();
            if(n == target){
                return n;
            }

            for(Node.Edge edge : n.neighbors){
                Node m = edge.node;
                double totalWeight = n.g + edge.weightH;

                if(!openList.contains(m) && !closedList.contains(m)){
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calculateHeuristicAltitude(target);
                    openList.add(m);
                } else {
                    if(totalWeight < m.g){
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calculateHeuristicAltitude(target);

                        if(closedList.contains(m)){
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }

    public static ArrayList<Integer> getPathAltitude() {
        TreeMap<Integer, Node> nodes = createNodes();

        Node head = nodes.get(0);
        head.g = 0;

        Node target = nodes.get(nodes.size() - 1);


        Node res = aStarAltitude(head, target);
        return createPath(res);
    }
}
