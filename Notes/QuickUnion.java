import edu.princeton.cs.algs4.*;

public class QuickUnion {
    int nodes[];

    public QuickUnion(int N){
        nodes = new int [N]; 
        for(int i=0; i<N; i++){
            nodes[i] = i;
        }
    }

    public int Root(int a){
        while(nodes[a]!=a){
            a = nodes[a];
        }
        return a;
    }

    public void Union(int a, int b){
        int a_root = Root(a);
        int b_root = Root(b);
        nodes[a_root] = b_root;
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
        QuickUnion my_class = new QuickUnion(10);
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

