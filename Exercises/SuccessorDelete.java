import edu.princeton.cs.algs4.StdOut;

public class SuccessorDelete {
    int predecessors[];
    int successors[];

    public SuccessorDelete(int N){
        predecessors = new int[N];
        successors = new int[N];
        for(int i=0;i<N; i++){
            predecessors[i] = Math.max(0, i-1);
            successors[i] = Math.min(i+1, N-1);
        }
    }

    public int Successor(int i){
        return successors[i]; 
    }

    public void Delete(int i){
        if(successors[i]!=i){
            successors[predecessors[i]] = successors[i];
        }
        else{
            successors[predecessors[i]] = predecessors[i];
        }
        predecessors[successors[i]] = predecessors[i];
    }
    
    public static void main(String[] args) {
        SuccessorDelete my_SuccessorDelete = new SuccessorDelete(10);
        my_SuccessorDelete.Delete(8);
        StdOut.println(my_SuccessorDelete.Successor(7));
        my_SuccessorDelete.Delete(4);
        StdOut.println(my_SuccessorDelete.Successor(3));        
    }
}
