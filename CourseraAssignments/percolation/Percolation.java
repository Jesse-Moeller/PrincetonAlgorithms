import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF connections;
    private int size;
    private boolean[][] siteStatus;
    private int numberOpenSites;
    private boolean percolates;

    // creates n-by-n connections, with all sites initially blocked
    public Percolation(int n) {
        if (n > 0) {
            this.connections = new WeightedQuickUnionUF(n * n);
            this.size = n;
            // (i,j) , (isopen, connectedToTop, connectedTobBttom)
            this.siteStatus = new boolean[n * n][3];
            this.numberOpenSites = 0;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private int getIdx(int row, int col) {
        return (row - 1) * this.size + (col-1);
    }

    private boolean isValidRowCol(int row, int col) {
        return (row >= 1) && (col >= 1) && (row <= this.size) && (col <= this.size);
    }

    private boolean isConnectedToTop(int idx) {
        int rootidx = this.connections.find(idx);
        return this.siteStatus[rootidx][1];
    }

    private boolean isConnectedToBot(int idx) {
        int rootidx = this.connections.find(idx);
        return this.siteStatus[rootidx][2];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isValidRowCol(row, col)) {
            if (!isOpen(row, col)) {
                int currentidx = getIdx(row, col);
                this.siteStatus[currentidx][0] = true;
                this.numberOpenSites++;

                if (row == 1) {
                    this.siteStatus[currentidx][1] = true;
                }

                if (row == this.size) {
                    this.siteStatus[currentidx][2] = true;
                }

                if (isValidRowCol(row - 1, col) && isOpen(row - 1, col)) {
                    int leftidx = getIdx(row-1, col);
                    this.siteStatus[currentidx][1] = this.siteStatus[currentidx][1] || isConnectedToTop(leftidx);
                    this.siteStatus[currentidx][2] = this.siteStatus[currentidx][2] || isConnectedToBot(leftidx);
                    this.connections.union(currentidx, leftidx);
                }
                
                if (isValidRowCol(row + 1, col) && isOpen(row + 1, col)) {
                    int rightidx = getIdx(row+1, col);
                    this.siteStatus[currentidx][1] = this.siteStatus[currentidx][1] || isConnectedToTop(rightidx);
                    this.siteStatus[currentidx][2] = this.siteStatus[currentidx][2] || isConnectedToBot(rightidx);
                    this.connections.union(currentidx, rightidx);
                }
                
                if (isValidRowCol(row, col - 1) && isOpen(row, col - 1)) {
                    int upidx = getIdx(row, col-1);
                    this.siteStatus[currentidx][1] = this.siteStatus[currentidx][1] || isConnectedToTop(upidx);
                    this.siteStatus[currentidx][2] = this.siteStatus[currentidx][2] || isConnectedToBot(upidx);
                    this.connections.union(currentidx, upidx);
                }

                if (isValidRowCol(row, col + 1) && isOpen(row, col + 1)) {
                    int downidx = getIdx(row, col+1);
                    this.siteStatus[currentidx][1] = this.siteStatus[currentidx][1] || isConnectedToTop(downidx);
                    this.siteStatus[currentidx][2] = this.siteStatus[currentidx][2] || isConnectedToBot(downidx);
                    this.connections.union(currentidx, downidx);
                }

                this.percolates = this.percolates || (this.siteStatus[currentidx][1] && this.siteStatus[currentidx][2]);
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (isValidRowCol(row, col)) {
            return this.siteStatus[getIdx(row, col)][0];
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (isValidRowCol(row, col)) {
            return (isOpen(row, col) && isConnectedToTop(getIdx(row, col)));
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numberOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.percolates;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation myPercolation = new Percolation(3);
        myPercolation.open(1, 3);
        StdOut.println(myPercolation.isFull(1, 3));
        myPercolation.open(2, 3);
        StdOut.println(myPercolation.isFull(2, 3));
        myPercolation.open(3, 3);
        StdOut.println(myPercolation.isFull(3, 3));
        myPercolation.open(3, 1);
        StdOut.println(myPercolation.isFull(3, 1));
        myPercolation.open(2, 1);
        StdOut.println(myPercolation.isFull(2, 2));
        myPercolation.open(1, 1);
        StdOut.println(myPercolation.isFull(1, 1));
    }
}
