package il.ac.tau.cs.sw1.ex7;
import java.util.*;

public class FractionalKnapSack implements Greedy<FractionalKnapSack.Item>{
    int capacity;
    List<Item> lst;


    FractionalKnapSack(int c, List<Item> lst1){
        capacity = c;
        Collections.sort(lst1, Collections.reverseOrder());;
        lst = lst1;
    }

    public static class Item implements Comparable<Item> {
        double weight, value;
        Item(double w, double v) {
            weight = w;
            value = v;
        }

        @Override
        public String toString() {
            return "{" + "weight=" + weight + ", value=" + value + '}';
        }

        public int compareTo (Item other){
            double thisVal = this.value/this.weight;
            double otherVal = other.value/other.weight;
            return Double.compare(thisVal,otherVal);
        }
    }

    @Override
    public Iterator<Item> selection() {
        return lst.iterator();
    }

    @Override
    public boolean feasibility(List<Item> candidates_lst, Item element) {
        if (capacity == 0){
            return false;
        }
        if (sum(candidates_lst) <= capacity){
            return true;
        }
        return false;
    }

    @Override
    public void assign(List<Item> candidates_lst, Item element) {

        if ((sum(candidates_lst) + element.weight) <= capacity) {
            candidates_lst.add(element);
        }
        else {
            double roomToAdd = capacity - sum(candidates_lst);
            double newVal = (roomToAdd/ element.weight) * element.value;
            Item toAdd = new Item(roomToAdd, newVal);
            candidates_lst.add(toAdd);

        }
    }

    @Override
    public boolean solution(List<Item> candidates_lst) {
        if ( this.capacity == 0 || sum(candidates_lst) == capacity ||  candidates_lst.size() == lst.size()){
            return true;
        }
        return false;
    }

    public static double sum(List<Item> lst){
        double sum = 0;

        for (int i =0 ; i< lst.size() ; i++){
            sum += lst.get(i).weight;
        }
        return sum;
    }
}
