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
            // (i,j) , (isopen, connectedToTop, connectedToBot)
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

    private void connectToTop(int idx){
        int rootidx = this.connections.find(idx);
        this.siteStatus[rootidx][1] = true;
    }
    private void connectToBot(int idx){
        int rootidx = this.connections.find(idx);
        this.siteStatus[rootidx][2] = true;
    }

    private boolean connectedToTop(int idx) {
        int rootidx = this.connections.find(idx);
        return this.siteStatus[rootidx][1];
    }

    private boolean connectedToBot(int idx) {
        int rootidx = this.connections.find(idx);
        return this.siteStatus[rootidx][2];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            int currentidx = this.getIdx(row, col);

            this.siteStatus[currentidx][0] = true;

            int neighbors[][] = { {row-1, col}, {row+1, col}, {row, col-1}, {row, col+1} };

            boolean nbsConnectToTop = false;
            boolean nbsConnectToBot = false;

            for (int i = 0; i<4; i++) {
                int nRow = neighbors[i][0];
                int nCol = neighbors[i][1];
                if (this.isValidRowCol(nRow, nCol)) {
                    int neighboridx = this.getIdx(nRow, nCol);
                    if (this.connectedToTop(neighboridx)) {
                        nbsConnectToTop = true;
                    }

                    if (this.connectedToBot(neighboridx)) {
                        nbsConnectToBot = true;
                    }
                }
            }

            if (nbsConnectToTop || row == 1) {
                this.connectToTop(currentidx);
                this.siteStatus[currentidx][1] = true;
            }

            if (nbsConnectToBot || row == this.size) {
                this.connectToBot(currentidx);
                this.siteStatus[currentidx][2] = true;
            }

            for (int i = 0; i<4; i++) {
                int nRow = neighbors[i][0];
                int nCol = neighbors[i][1];
                if (this.isValidRowCol(nRow, nCol)) {
                    int neighboridx = this.getIdx(nRow, nCol);
                    if (this.siteStatus[neighboridx][0]) {
                        this.connections.union(neighboridx, currentidx);
                        if (this.siteStatus[currentidx][1]) {
                            this.connectToTop(neighboridx);
                        }
                        if (this.siteStatus[currentidx][2]) {
                            this.connectToBot(neighboridx);
                        }
                    }
                 }
            }

            if (this.connectedToTop(currentidx) && this.connectedToBot(currentidx)) {
                this.percolates = true;
            }

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
        if (isValidRowCol(row,col)) {
            return this.connectedToTop(getIdx(row, col));
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
