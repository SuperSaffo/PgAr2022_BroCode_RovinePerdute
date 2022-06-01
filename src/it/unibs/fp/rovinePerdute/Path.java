package it.unibs.fp.rovinePerdute;

import java.util.*;

public class Path {
    /**
     * Costo del percorso
     */
    public static double cost = 0;
    /**
     * TreeMap contenente le citta'
     * @see Map#getCityMap()
     */
    public static TreeMap<Integer, City> map = Map.getCityMap();
    /**
     * TreeMap per i nodi
     */
    public static TreeMap<Integer, Node> nodes = new TreeMap<>();

    /**
     * Getter per il costo del percorso
     * @return ritorna il costo del percorso
     */
    public static double getCost() {
        return cost;
    }
    /**
     * Setter del costo del percorso
     * @param cost Nuovo costo del percorso
     */
    public static void setCost(double cost) {
        Path.cost = cost;
    }

    /**
     * Metodo per creare TreeMap di nodi nodi
     * <p>Se la TreeMap di nodi esiste gia' vengono azzerati i valori di: parent, f, g, h</p>
     * <p>Se la TreeMap e' vuota per ogni citta' viene creato un nodo</p>
     * <p>Per ogni nodo vengono aggiunti i nodi a cui e' legato,
     * viene calcolata la distanza e differenza di altitudine del nodo figlio dal nodo padre</p>
     *
     * @see Node
     * @see City
     * @see it.unibs.fp.rovinePerdute.Node.Edge
     * @see Node#addBranch(double, double, Node)
     * @see Node#calculateHeuristicEuclidean(Node)
     * @see Node#calculateHeuristicAltitude(Node)
     * @return Ritorna TreeMap di nodi
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

        /*
         * Classe Collection con i valori di "map"
         * forEach di ogni valore per creare i nodi
         */
        Collection<City> cities = map.values();
        cities.forEach(value -> nodes.put(value.getId(), new Node(value)));

        /*
         * Per ogni nodo vengono aggiunti i nodi figli
         * per ogni nodo figlio si calcolano distanza e differenza di altitudine
         */
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
        //Costo ugale a "f"
        setCost(n.f);
        ArrayList<Integer> path = new ArrayList<>();

        /*
         * Vengono fatti scorrere i nodi fino a quando parent e' null (nodo di partenza)
         * Al path viene aggiunto l'ID del nodo
         * n viene settato al parent dell'n appena considerato
         */
        while(n.parent != null){
            path.add(n.id);
            n = n.parent;
        }
        //Aggiunta dell'id del nodo di partenza
        path.add(n.id);
        //Viene invertito l'ordine degli ID nell'ArrayList del percorso
        Collections.reverse(path);

        return path;
    }

    /**
     * Metodo per la squadra Tonatiuh per calcolare il percorso meno dispendioso per raggiungere il Nodo di arrivo dal Nodo di partenza
     * <p>Si utilizzano 2 PriorityQueue, i confronti vengono fatti sulla "f",
     * closedList contenente i nodi di cui sono gia stati controllati tutti i nodi figli,
     * openList contenente i Nodi incontrati ma di cui non si sono controllati i nodi figli</p>
     *
     * @see Node#calculateHeuristicEuclidean(Node)
     * @see Node#compareTo(Node) 
     * @param start Nodo di partenza
     * @param target Nodo di arrivo
     * @return Ritorna il nodo di arrivo
     */
    public static Node aStarEuclidean(Node start, Node target){
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();

        //Calcolo del valore di f del nodo di partenza
        start.f = start.g + start.calculateHeuristicEuclidean(target);
        //Aggiunta del nodo di partenza alla openList
        openList.add(start);

        /*
         * Il ciclo viene ripetuto fino a che la openList non e' vuota
         * Inizialmente la openList conteine solo il nodo di partenza
         */
        while(!openList.isEmpty()){
            //Primo nodo nella openList, quello con "f" minore
            Node n = openList.peek();
            //Se il nodo che si sta considerando e' il nodo di arrivo si ritorna il nodo di arrivo
            if(n == target){
                return n;
            }

            /*
             * Per ogni nodo si controllano i nodi figli
             */
            for(Node.Edge edge : n.neighbors){
                //Nodo figlio del nodo che si sta considerando
                Node m = edge.node;
                //Costo del percorso dal nodo di partenza (head) al nodo considerato
                double totalWeight = n.g + edge.weightD;

                /*
                 * Se il nodo non e' contenuto nelle PriorityList
                 *      Viene settato il parent al Nodo padre
                 *      Viene settata la g alla distanza totale dall'head e calcolata la f con la funzione euristica
                 *      Viene aggiunto il nodo alla openList
                 */
                if(!openList.contains(m) && !closedList.contains(m)){
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calculateHeuristicEuclidean(target);
                    openList.add(m);
                }
                /*
                 * Se il nodo e' contenuto in una della liste
                 *      Si controlla se la distanza totale per raggiungere il nodo tramite il nodo "n" considerato
                 *      sia minore della distanza calcolata in precedenza
                 */
                else {
                    /*
                     * Se la nuova distanza per raggiungere il nodo e' minore
                     *      Viene modificato il valore del parent al nodo "n" considerato
                     *      Viene modificato il valore di "g"
                     *      Viene ricalcolata la "f"
                     */
                    if(totalWeight < m.g){
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calculateHeuristicEuclidean(target);

                        /*
                         * Se il nodo considerato si trovava nella closedList il nodo viene rimosso e viene aggiunto nella openList
                         * Viene reinserito nella openList in modo da poter ricalcolare le distanze dai sui nodi figli
                         */
                        if(closedList.contains(m)){
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
            }

            /*
             * Al termine del controllo il nodo "n" viene rimosso dalla openList e inserito nella closedList
             */
            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }

    /**
     * Metodo per calcolare il percorso della squadra Tonatiuh
     * <p>Vengono inizializzati i nodi head e target (primo e ultimo nodo della TreeMap di nodi)</p>
     *
     * @see Path#aStarEuclidean(Node, Node)
     * @see Path#createPath(Node)
     * @return Ritorna ArrayList con ID delle citta'
     */
    public static ArrayList<Integer> getPathEuclideo() {
        TreeMap<Integer, Node> nodes = createNodes();

        Node head = nodes.get(0);
        head.g = 0;

        Node target = nodes.get(nodes.size() - 1);

        Node res = aStarEuclidean(head, target);
        return createPath(res);
    }

    /**
     * Metodo per la squadra Metzil per calcolare il percorso meno dispendioso per raggiungere il Nodo di arrivo dal Nodo di partenza
     * Stesso funzionamento del metodo aStarEucledean
     * @see Path#aStarEuclidean(Node, Node)
     * @see Node#calculateHeuristicAltitude(Node)
     * @see Path#aStarAltitude(Node, Node)
     * @param start Nodo di partenza
     * @param target Nodo di arrivo
     * @return Ritorna nodo di arrivo
     */
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

    /**
     * Metodo per calcolare il percorso della squadra Tonatiuh
     * <p>Stesso funzionamento del metodo getPathEuclidean</p>
     * @see Path#getPathAltitude()
     * @return Ritorna ArrayList con ID delle citta'
     */
    public static ArrayList<Integer> getPathAltitude() {
        TreeMap<Integer, Node> nodes = createNodes();

        Node head = nodes.get(0);
        head.g = 0;

        Node target = nodes.get(nodes.size() - 1);


        Node res = aStarAltitude(head, target);
        return createPath(res);
    }
}
