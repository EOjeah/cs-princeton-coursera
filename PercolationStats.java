/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int numberOftrials;
    private final double[] arrayForSum;
    // private double
    // private ArrayList<Double> arrayForSum;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Illegal Arguments");
        }
        numberOftrials = trials;

        arrayForSum = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int randomX;
            int randomY;
            do {
                do {
                    randomX = StdRandom.uniform(1, n + 1);
                    randomY = StdRandom.uniform(1, n + 1);
                } while (percolation.isOpen(randomX, randomY));
                percolation.open(randomX, randomY);
            } while (!percolation.percolates());
            int numberOfOpenSites = percolation.numberOfOpenSites();
            double percolationThreshold = (double) numberOfOpenSites/(n*n);
            arrayForSum[i] = percolationThreshold;
            // System.out.printf("number of Open sites: %d\n", numberOfOpenSites);
            // System.out.printf("percolation threshold is: %f\n", percolationThreshold);
        }
    }

    public double mean() {
        // double[] a = arrayForSum.toArray();
        return StdStats.mean(arrayForSum);
    }

    public double stddev() {
        return StdStats.stddev(arrayForSum);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(numberOftrials));
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(numberOftrials));
    }

    public static void main(String[] args) {
        int n = 0;
        int t = 0;
        // while (!StdIn.isEmpty()) {
        //     n = StdIn.readInt();
        //     t = StdIn.readInt();
        // }
        n = Integer.parseInt(args[0]);
        t = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, t);
        System.out.printf("mean\t\t\t = %f\n", percolationStats.mean());
        System.out.printf("stddev\t\t\t = %f\n", percolationStats.stddev());
        System.out.printf("95 confidence interval\t = [%f, %f]\n", percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }
}
