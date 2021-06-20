import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double estimates[];
    private double confidence95 = 1.96;
    
    public PercolationStats(int n, int trials) {
        estimates = new double[trials];
        for(int t=0; t<trials; t++){
            Percolation experiment = new Percolation(n);
            StdOut.println("Starting trial...");
            while (!experiment.percolates()) {
                int row = StdRandom.uniform(n)+1;
                int col = StdRandom.uniform(n)+1;
                if (!experiment.isOpen(row, col)) {
                    experiment.open(row, col);
                }
            }
            double sites = experiment.numberOfOpenSites();
            double total = n * n;
            double estimate = sites/total;
            estimates[t] = estimate;
            StdOut.println("Trial Complete.");
        }
    }

    public double mean() {
        return StdStats.mean(estimates);
    }

    public double stddev() {
        return StdStats.stddev(estimates);
    }

    public double confidenceLo() {
        return mean() - confidence95*stddev() / Math.sqrt(estimates.length);
    }

    public double confidenceHi() {
        return mean() + confidence95*stddev() / Math.sqrt(estimates.length);
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats my_experiment = new PercolationStats(n, T);
        StdOut.println(my_experiment.mean());
        StdOut.println(my_experiment.stddev());
        StdOut.println(my_experiment.confidenceLo());
        StdOut.println(my_experiment.confidenceHi());
    }
}
