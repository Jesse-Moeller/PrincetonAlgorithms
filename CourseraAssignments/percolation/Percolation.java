import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF grid;
    private int size;
    private boolean[] sites;
    private int numberOpenSites;

    // creates n-by-n grid, with allthis.sites initially blocked
    public Percolation(int n) {
        if (n > 0) {
            this.grid = new WeightedQuickUnionUF(n * n + 2);
            this.size = n;
            this.sites = new boolean[n * n + 2];
            this.numberOpenSites = 0;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private int getIdx(int row, int col) {
        return (row-1)*this.size+col;
    }

    private boolean isValidRowCol(int row, int col) {
        return (row >= 1) && (col >= 1) && (row <= this.size) && (col <= this.size);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isValidRowCol(row, col)) {
            if (!isOpen(row, col)) {
                int currentidx = getIdx(row, col);
               this.sites[currentidx]=true;
                this.numberOpenSites++;
                if (row == 1) {
                   this.grid.union(currentidx, 0);
                }
                if (row == this.size) {
                   this.grid.union(currentidx, this.size * this.size + 1);
                }
                if (isValidRowCol(row - 1, col) && isOpen(row - 1, col)) {
                        int leftidx = getIdx(row-1, col);
                       this.grid.union(currentidx, leftidx);
                }
                
                if (isValidRowCol(row + 1, col) && isOpen(row + 1, col)) {
                        int rightidx = getIdx(row+1, col);
                       this.grid.union(currentidx, rightidx);
                }
                
                if (isValidRowCol(row, col - 1) && isOpen(row, col - 1)) {
                        int upidx = getIdx(row, col-1);
                       this.grid.union(currentidx, upidx);
                }

                if (isValidRowCol(row, col + 1) && isOpen(row, col + 1)) {
                        int downidx = getIdx(row, col+1);
                       this.grid.union(currentidx, downidx);
                }
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (isValidRowCol(row, col)) {
            return this.sites[getIdx(row, col)];
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (isValidRowCol(row, col)) {
            return (isOpen(row, col) && (grid.find(getIdx(row, col)) == this.grid.find(0)));
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    // returns the number of openthis.sites
    public int numberOfOpenSites() {
        return this.numberOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return (grid.find(0) == grid.find(this.size * this.size + 1));
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation my_percolation = new Percolation(2);
        StdOut.println(my_percolation.percolates());
        my_percolation.open(1, 1);
        StdOut.println(my_percolation.percolates());
        my_percolation.open(1, 2);
        StdOut.println(my_percolation.percolates());
        my_percolation.open(2, 1);
        StdOut.println(my_percolation.percolates());
    }
}
