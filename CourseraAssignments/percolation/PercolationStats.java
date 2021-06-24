import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] estimates;
    
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        estimates = new double[trials];
        for (int t = 0; t < trials; t++) {
            Percolation experiment = new Percolation(n);
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
        }
    }

    public double mean() {
        return StdStats.mean(estimates);
    }

    public double stddev() {
        return StdStats.stddev(estimates);
    }

    public double confidenceLo() {
        return mean() - CONFIDENCE_95*stddev() / Math.sqrt(estimates.length);
    }

    public double confidenceHi() {
        return mean() + CONFIDENCE_95*stddev() / Math.sqrt(estimates.length);
    }
    
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException();
        }
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats myExperiment = new PercolationStats(n, trials);
        String mean = String.format("mean                    = %f", myExperiment.mean());
        String std = String.format("stddev                  = %f", myExperiment.stddev());
        String conf = String.format("95%% confidence interval = [%f,%f]", myExperiment.confidenceLo(), myExperiment.confidenceHi());
        StdOut.println(mean);
        StdOut.println(std);
        StdOut.println(conf);
    }
}
