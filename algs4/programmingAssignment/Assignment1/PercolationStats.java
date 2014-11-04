/**
 * PercolationStats, simulate a series of computational experiments
 */
public class PercolationStats {
    private double[] x; // x[t] be the fraction of open sites in computational experiment t.
    private int T;
    /**
     * Perform T independent computational experiments
     * on an N-by-N grid
     * 
     * @param N a percolatoin system size <tt>N</tt> by <tt>N</tt>
     * @param T experiments count
     * @throws IllegalArgumentException if either N <= 0 or T <= 0
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        
        x = new double[T];
        this.T = T;
        int totalSites = N * N;
        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
            int openCount = 0; // record the total open sites number
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, N+1);
                int column = StdRandom.uniform(1, N+1);
                if (!p.isOpen(row, column)) {
                    p.open(row, column);
                    openCount++;
                }
            }
            x[i] = (double) openCount / totalSites;
        }
    }
    
    /**
     * sample mean of percolation thershold
     * @returns an estimate of the percolaton threshold
     */
    public double mean() {
        return StdStats.mean(x);
    }
    
    /**
     * sample standard deviation of percolation threshold
     * @returns the sharpness of the threshold
     */
    public double stddev() {
        return StdStats.stddev(x);
    }
    
    /**
     * @returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }
    
    /**
     * @returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("please provide two arguments N and T");
            return;
        }
        
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);
        System.out.printf("%-23s = %e\n", "mean", ps.mean());
        System.out.printf("%-23s = %e\n", "stddev", ps.stddev());
        System.out.printf("%-23s = %e, %e\n", "95% confidence interval", ps.confidenceLo(), ps.confidenceHi());
    }
}
