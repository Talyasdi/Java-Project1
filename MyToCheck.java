package il.ac.tau.cs.sw1.ex7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyToCheck {
    public static void main(String[] args) {

        ArrayList<Graph.Edge> ed = new ArrayList<Graph.Edge>();
        ed.add(new Graph.Edge(0, 1, 1));
        ed.add(new Graph.Edge(0, 6, 3));
        ed.add(new Graph.Edge(0, 7, 0));
        ed.add(new Graph.Edge(1, 2, 0));
        ed.add(new Graph.Edge(1, 5, 0));
        ed.add(new Graph.Edge(2, 3, 0));
        ed.add(new Graph.Edge(2, 4, 0));
        ed.add(new Graph.Edge(7, 8, 0));
        ed.add(new Graph.Edge(7, 11, 0));
        ed.add(new Graph.Edge(8, 9, 0));
        ed.add(new Graph.Edge(8, 10, 0));
        ed.add(new Graph.Edge(10, 11, 0));
        //System.out.println(ed);
        int n = 13;

        // build a graph from the given edges
        Graph graph = new Graph(n, ed);
        System.out.println(graph.greedyAlgorithm());





    }

}



