import edu.princeton.cs.algs4.*;

public class QuickFind {
    int nodes[];

    public QuickFind(int N){
        nodes = new int [N]; 
        for(int i=0; i<N; i++){
            nodes[i] = i;
        }
    }

    public void Union(int a, int b){
        int a_component = nodes[a];
        int b_component = nodes[b];
        for(int i=0; i<nodes.length; i++){
            if(nodes[i]==a_component){
                nodes[i] = b_component;
            }
        }
    }

    public boolean Connected(int a, int b){
        return nodes[a]==nodes[b];
    }

    public void Print(){
        for(int i=0; i<nodes.length; i++){
            StdOut.print(nodes[i]);
        }
    }

    public static void main(String[] args) {
        QuickFind my_class = new QuickFind(10);
        my_class.Union(4, 3);
        my_class.Union(3, 8);
        my_class.Union(6, 5);
        my_class.Union(9, 4);
        my_class.Union(2, 1);
        StdOut.print(my_class.Connected(8, 9));
        StdOut.print(my_class.Connected(5, 0));
        my_class.Print();

    }
}