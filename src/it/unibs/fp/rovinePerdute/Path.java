package it.unibs.fp.rovinePerdute;

import java.util.*;

public class Path {
    public static double cost = 0;

    public static double getCost() {
        return cost;
    }

    public static void setCost(double cost) {
        Path.cost = cost;
    }

    public static TreeMap<Integer, Node> createNodes() {
        TreeMap<Integer, Node> nodes = new TreeMap<>();
        TreeMap<Integer, City> map = Map.getCityMap();

        Collection<City> cities = map.values();
        cities.forEach(value -> nodes.put(value.getId(), new Node(value)));

        for(int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            for(int j = 0; j < n.city.getLink().size(); j++) {
                Node branch = nodes.get(n.city.getLinkByIndex(j));
                n.addBranch(n.calculateHeuristicEuclideo(branch), branch);
            }
        }

        return nodes;
    }

    public static ArrayList<Integer> createPath(Node target){
        Node n = target;

        if(n == null)
            return null;

        setCost(n.f);

        ArrayList<Integer> ids = new ArrayList<>();

        while(n.parent != null){
            ids.add(n.id);
            n = n.parent;
        }
        ids.add(n.id);
        Collections.reverse(ids);

        return ids;
    }

    //SQUADRA TONATIUH
    public static Node aStarEuclideo(Node start, Node target){
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();

        start.f = start.g + start.calculateHeuristicEuclideo(target);
        openList.add(start);

        while(!openList.isEmpty()){
            Node n = openList.peek();
            if(n == target){
                return n;
            }

            for(Node.Edge edge : n.neighbors){
                Node m = edge.node;
                double totalWeight = n.g + edge.weight;

                if(!openList.contains(m) && !closedList.contains(m)){
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calculateHeuristicEuclideo(target);
                    openList.add(m);
                } else {
                    if(totalWeight < m.g){
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calculateHeuristicEuclideo(target);

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

        Node head = nodes.get(0);
        head.g = 0;

        Node target = nodes.get(nodes.size() - 1);


        Node res = aStarEuclideo(head, target);
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
                double totalWeight = n.g + edge.weight;

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
        head.g = head.city.getH();

        Node target = nodes.get(nodes.size() - 1);


        Node res = aStarAltitude(head, target);
        return createPath(res);
    }
}
