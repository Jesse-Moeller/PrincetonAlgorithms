import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF Grid;
    int N;
    boolean[] Sites;
    int OpenSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if(n>0){
            Grid = new WeightedQuickUnionUF(n*n+2);
            N = n;
            Sites = new boolean[n*n+2];
            for(int i=0; i<N; i++)
            {
                Grid.union(0, i);
                Grid.union(i+n*n-n+1, n*n+1);
            }
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if((row>=1)&&(col>=1)&&(row<=N)&&(col<=N)){
            if(Sites[(row-1)*N+col]==false)
            {
                int currentidx = (row-1)*N+col;
                Sites[currentidx]=true;
                OpenSites++;

                if( (row-1>=1) && isOpen(row-1, col) ){
                        int leftidx = (row-2)*N+col;
                        Grid.union(currentidx,leftidx);
                }
                
                if( (row+1<=N) && isOpen(row+1, col) ){
                        int rightidx = (row)*N+col;
                        Grid.union(currentidx,rightidx);
                }
                
                if( (col-1>=1) && isOpen(row, col-1) ){
                        int upidx = (row-1)*N+(col-1);
                        Grid.union(currentidx,upidx);
                }

                if( (col+1<=N) && isOpen(row, col+1) ){
                        int downidx = (row-1)*N+(col+1);
                        Grid.union(currentidx,downidx);
                }
            }
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if((row>=1)&&(col>=1)&&(row<=N)&&(col<=N)){
            return Sites[(row-1)*N+col];
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if((row>=1)&&(col>=1)&&(row<=N)&&(col<=N)){
            if(isOpen(row, col)){
                return (Grid.find((row-1)*N+col) == Grid.find(0));
            }
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return OpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return (Grid.find(0)==Grid.find(N*N+1));
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation my_percolation = new Percolation(2);
    }
}
