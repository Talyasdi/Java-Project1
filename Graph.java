package il.ac.tau.cs.sw1.ex7;
import java.util.*;


public class Graph implements Greedy<Graph.Edge>{
    List<Edge> lst; //Graph is represented in Edge-List. It is undirected. Assumed to be connected.
    int n; //nodes are in [0,...,n-1]

    Graph(int n1, List<Edge> lst1){
        Collections.sort(lst1);
        lst = lst1;
        n = n1;
    }

    public static class Edge implements Comparable<Edge>{
        int node1, node2;
        double weight;

        Edge(int n1, int n2, double w) {
            node1 = n1;
            node2 = n2;
            weight = w;
        }

        public int compareTo (Graph.Edge other){
            if (this.weight == other.weight){
                if (this.node1 == other.node1){
                    return Integer.compare(this.node2 , other.node2);
                }
                else {
                    return Integer.compare(this.node1, other.node1);
                }
            }
            else {
                return Double.compare(this.weight,other.weight);
            }
        }

        @Override
        public String toString() {
            return "{" + "(" + node1 + "," + node2 + "), weight=" + weight + '}';
        }
    }

    @Override
    public Iterator<Edge> selection() {
        return lst.iterator();
    }

    @Override
    public boolean feasibility(List<Edge> candidates_lst, Edge element) {
        if (this.n == 0){
            return false;
        }
        int[][] mat = toMatrix(candidates_lst , this.n+1);
        boolean[] beenthere = new boolean[this.n+1];
        int[] vals = new int[this.n+1];
        int res = findPath(mat, element.node1 ,element.node2, mat.length + 1, beenthere, vals);
        if (res == -1){
            return true;
        }
        return false;
    }

    private static int[][] toMatrix (List<Edge> lst , int n){
        int[][] mat = new int[n][n];
        for (Edge e : lst){
            mat[e.node1][e.node2] = 1;
            mat[e.node2][e.node1] = 1;
        }
        return mat;
    }

    private static int findPath(int[][] m, int i, int j, int count, boolean[] beenthere, int[] values) {
        //recursion conditions
        if (i == j) {
            return 0;
        }
        if (m[i][j] == 1) {
            return 1;
        }
        //goes through all the possible paths
        int min;
        for (int index = 0; index < m.length; index++) {
            if (index == i) {
                continue;
            }
            if (m[i][index] == 1) {
                if (beenthere[index] == false) {
                    beenthere[index] = true;
                    int curr_check = findPath(m, index, j, count, beenthere, values);
                    if (curr_check >= 0) {
                        values[index] = curr_check;
                        min = 1 + curr_check;
                        if (min < count) {
                            count = min;
                        }
                    }
                } else {
                    if (values[index] != 0) {
                        min = 1 + values[index];
                        if (min < count) {
                            count = min;
                        }
                    }
                }
            }
        }
        //suppose to happen if there is any path
        if (count < m.length + 1) {
            return count;
        }
        return -1;
    }



    @Override
    public void assign(List<Edge> candidates_lst, Edge element) {
        candidates_lst.add(element);
    }

    @Override
    public boolean solution(List<Edge> candidates_lst) {
        return (candidates_lst.size() == n);
    }


}





