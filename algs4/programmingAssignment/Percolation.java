/**
 * Percolation Data Type
 */
public class Percolation {
    private boolean[][] open; // open or blocked
    private WeightedQuickUnionUF uf; // UnionFind instance
    private int N;
    /**
     * Create N-by_N grid, with all sites blocked
     * @throws java.lang.IllegalArgumentException if N <= 0
     * @param N the number of row and column
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        
        this.N = N;
        open = new boolean[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                open[i][j] = false;
            }    
        }
        uf = new WeightedQuickUnionUF(N*N + 2);
    }
    
    /**
     * Test if parameters are legal
     * @throws java.lang.IndexOutOfBoundsException unless 1 <= i <= N, 1 <= j <=N
     * @param i row number
     * @param j column number
     */
    private void checkArgument(int i, int j) {
        if (!(i <= N && i >= 1 && j >= 1 && j<= N)) {
            throw new IndexOutOfBoundsException();
        }
    }
    
    /**
     * Find the corresponding num in the unionfind, there are N*N + 2 nodes, 
     * (i, j) = (1, 1) correspond to the num 0, (1, 2) correspond to num 1...
     * there are two special nodes, the start node and the end node.
     * the start node connect to the open sites in the first line, and the
     * end node connect to the open sites in the last line.
     * so, the first node's num is N*N, and the last node's num is N*N + 1.
     * 
     * @param i row number
     * @param j column number
     * @returns the corresponding num in the UnionFind
     */
    private int num(int i, int j) {
        return (i - 1) * N + (j - 1);
    }
    
    /**
     * @returns the num of the virtual strat site in the UnionFind
     */
    private int startNum() {
        return N*N;
    }
    
    /**
     * @returns the num of the virtual end site in the UnionFind
     */
    private int endNum() {
        return N*N + 1;
    }
    
    /**
     * open site (row i, column j) if it is not already
     * @throws java.lang.IndexOutOfBoundsException unless 1 <= i <= N, 1 <= j <=N
     * @param i row number
     * @param j column number
     */
    public void open(int i, int j) {
        checkArgument(i, j);
        
        if (open[i][j]) { // if it is already open, return
            return;
        }
        
        open[i][j] = true; // open it
        
        /*
         * if it's neighbor is open, union it
         */
        int numIJ = num(i, j);
        if (i != 1 && open[i-1][j]) { // up site
            uf.union(numIJ, num(i-1, j));
        }
        if (i != N && open[i+1][j]) { // down site
            uf.union(numIJ, num(i+1, j));
        }
        if (j != 1 && open[i][j-1]) { // left site
            uf.union(numIJ, num(i, j-1));
        }
        if (j != N && open[i][j+1]) { // right site
            uf.union(numIJ, num(i, j+1));
        }
        
        if (i == 1) { // if the first line site, connect to the virtual start site
            uf.union(numIJ, startNum());
        }
        if (i == N) { // if the last line site, connect ot the virtual end site
            uf.union(numIJ, endNum());
        }
    }
    
    /**
     * Is site (row i, column j) open?
     * @param i
     * @param j
     * @returns <tt>true</tt> if the site (<tt>i</tt>, <tt>j</tt>) is open,
     *    and <tt>false</tt> otherwise
     * @throws java.lang.IndexOutOfBoundsException unless 1 <= i <= N, 1 <= j <=N
     */
    public boolean isOpen(int i, int j) {
        return open[i][j];
    }
    
    /**
     * Is site (row i, column j) full?
     * @param i
     * @param j
     * @returns <tt>true</tt> if the site (<tt>i</tt>, <tt>j</tt>) is full,
     *    and <tt>false</tt> otherwise
     * @throws java.lang.IndexOutOfBoundsException unless 1 <= i <= N, 1 <= j <=N
     */
    public boolean isFull(int i, int j) {
        checkArgument(i, j);
        return uf.connected(num(i, j), startNum());
    }
    
    /**
     * Does the system percolate?
     */ 
    public boolean percolates() {
        return uf.connected(startNum(), endNum());
    }
    
    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        p.open(1, 1);
        p.open(3, 1);
        p.open(2, 1);
        p.open(4, 1);
 
        System.out.println(p.isOpen(3, 1));
        System.out.println(p.isFull(3, 1));
        System.out.println(p.percolates());
    }
}