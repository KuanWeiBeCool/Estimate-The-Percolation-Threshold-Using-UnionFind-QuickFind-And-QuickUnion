package com.kuan;

import java.util.Arrays;
import java.util.Scanner;

public class Percolation {
    private static int[][] grid;
    private boolean[][] open;
//    private static UnionFind set;
    private static QuickFind set;
    private int numOpenSites;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int N){
        grid = new int[N][N];
        open = new boolean[N][N];
//        set = new UnionFind(N*N + 2); // Initialize UnionFind object
        set = new QuickFind((N*N + 2));
        numOpenSites = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++) {
                grid[i][j] = i * N + (j + 1); // Visualize grid
                open[i][j] = false; // Initialize all sites closed
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if(!open[row][col]){
            open[row][col] = true;
            numOpenSites ++;
            // Automatically connect to the top virtual helper if any sites in the first row opens
            // Top virtual helper is represented by the integer 0
            if (row == 0) {
                set.union(0, grid[row][col]);
            }
            // Automatically connect to the bottom virtual helper if any sites in the last row opens
            // Bottom virtual helper is represented by the integer N*N + 1
            if (row == grid.length - 1) {
                set.union(grid.length * grid.length + 1, grid[row][col]);
            }
            // Check if any site around is open. If so, connect.
            if (row >= 1) {
                if (open[row - 1][col]) {
                    set.union(grid[row - 1][col], grid[row][col]);
                }
            }
            if (row <= grid.length - 2) {
                if (open[row + 1][col]) {
                    set.union(grid[row + 1][col], grid[row][col]);
                }
            }
            if (col >= 1) {
                if (open[row][col - 1]) {
                    set.union(grid[row][col - 1], grid[row][col]);
                }
            }
            if (col <= grid.length - 2) {
                if (open[row][col + 1]) {
                    set.union(grid[row][col + 1], grid[row][col]);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        return (open[row][col]);
    }

    // is the site (row, col) full (i.e. site connected to top)?
    public boolean isFull(int row, int col){
        return (set.connected(grid[row][col], 0));
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        // System percolates when the top and bottom virtual helpers are connected
        return (set.connected(0, grid.length * grid.length + 1));
    }

//    // test client (optional)
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int N;
        while(true) {
            System.out.print("Enter the size of the system: ");
            N = scanner.nextInt();
            if (N > 0)
                break;
            else
                System.out.println("Invalid Input. Please enter an integer larger than 0.");
        }
        Percolation system = new Percolation(N);
        while(true){
            System.out.print("Enter command: ");
            String command = scanner.next();
            if (command.equals("open")) {
                System.out.print("Enter row and column: ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                system.open(row, col);
                int[][] visualGrid = new int[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        int index = i * N + j + 1;
                        visualGrid[i][j] = set.id[index]; // Visualize grid
                    }
                }
                System.out.println(Arrays.deepToString(visualGrid));
            }
            if (command.equals("isOpen")){
                System.out.print("Enter row and column: ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                System.out.println(system.isOpen(row, col));
            }
            if (command.equals("isFull")){
                System.out.print("Enter row and column: ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                System.out.println(system.isFull(row, col));
            }
            if (command.equals("numberOfOpenSites")){
                System.out.println(system.numberOfOpenSites());
            }
            if (command.equals("percolates")){
                System.out.println(system.percolates());
            }
            if (command.toLowerCase().equals("quit"))
                break;
        }
    }
}
