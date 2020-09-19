package com.kuan;

public class PercolationStats {
    double[] thresholds;
    final double Z = 1.96;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        int randomRow;
        int randomCol;
        thresholds = new double[trials];
        int totalSites = n * n;
        Percolation system = new Percolation(n);
        for (int i = 0; i < trials; i++){
            while(!system.percolates()) {
                randomRow = (int) (Math.random() * n);
                randomCol = (int) (Math.random() * n);
                system.open(randomRow, randomCol);
            }
            int openSites = system.numberOfOpenSites();
            thresholds[i] = (double) openSites / (double) totalSites;
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        double sum = 0.0;
        for (double threshold : thresholds) sum += threshold;
        return (sum / (float) thresholds.length);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        double sum = 0.0, standardDeviation = 0.0;
        int length = thresholds.length;

        for(double num : thresholds) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: thresholds) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - Z * stddev() / Math.sqrt(thresholds.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + Z * stddev() / Math.sqrt(thresholds.length);
    }

    // test client (see below)
    public static void main(String[] args){
        long startTime = System.nanoTime();
        PercolationStats test = new PercolationStats(1600, 100);
        System.out.println("Mean: " + test.mean());
        System.out.println("Standard Deviation: " + test.stddev());
        System.out.println("95% Confidence Interval: " + test.confidenceLo() + " to " + test.confidenceHi());
        long endTime = System.nanoTime();
        long programTime = (endTime - startTime) / 1_000_000;
        System.out.println("Program running time (microseconds): " + programTime);
    }

}
