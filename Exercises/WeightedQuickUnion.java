import edu.princeton.cs.algs4.*;

public class WeightedQuickUnion {
    int nodes[];
    int weights[];

    public WeightedQuickUnion(int N){
        nodes = new int [N]; 
        weights = new int[N];

        for(int i=0; i<N; i++){
            nodes[i] = i;
            weights[i] = 1;
        }
    }

    public int Root(int a){
        while(nodes[a]!=a){
            nodes[a] = nodes[nodes[a]];
            a = nodes[a];
        }
        return a;
    }

    public void Union(int a, int b){
        int a_root = Root(a);
        int b_root = Root(b);
        if(weights[a_root]<weights[b_root]){
            nodes[a_root] = b_root;
            weights[b_root] += weights[a_root];
        }
        else{
            nodes[b_root] = a_root;
            weights[a_root] += weights[b_root];
        }
        
    }

    public boolean Connected(int a, int b){
        return Root(a)==Root(b);
    }

    public void Print(){
        for(int i=0; i<nodes.length; i++){
            StdOut.print(nodes[i]);
        }
    }

    public static void main(String[] args) {
        WeightedQuickUnion my_class = new WeightedQuickUnion(10);
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
