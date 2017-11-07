// Programming Assignment 1: Percolation
// Written by Garland Poon
// Java version: 1.7.0.67
// https://algs4.cs.princeton.edu/windows/

// Write a program to estimate the value of the percolation threshold via Monte Carlo simulation.
// http://coursera.cs.princeton.edu/algs4/assignments/percolation.html

//import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   
   // Node class is the type used to represent the entire grid used for percolation
   private class Node 
   {
       boolean stat = false;
       int row;
       int col;
       
       Node(int r, int c)
       {
           row = r;
           col = c;
       }
   }
   
   private Node[][] grid;
   private int[][] sz;
   private final int val;
   
   // Status enum represents basic ranges
   private enum Status {
       LOW, MEDIUM, HIGH
   }
   
   // Side enum represents sides of the grid in the following order:
   // Upper-left, upper-right, lower-left, lower-right, middle-left, middle-right,
   // upper-middle, lower-middle, and not applicable for non-side coordinates
   private enum Side {
       UL, UR, LL, LR, ML, MR, UM, LM, NA
   }
   
   public Percolation(int n) // create n-by-n grid, with all sites blocked
   {
       if (n < 1) throw new IllegalArgumentException();
       grid = new Node[n][n]; 
       sz = new int[n][n]; 
       val = n;
       
       for (int i = 0; i < n; i++)
       {
           for (int j = 0; j < n; j++) 
           {
               grid[i][j] = new Node(i, j);
               sz[i][j] = 1;
           }
       }
   }
  
   public void open(int row, int col) // open site (row, col) if it is not open already
   {
       Side status = edgeDetect(row, col);
       if (grid[row-1][col-1].stat == false)
       {
           if (rangeCheck(row, col, 1, val))
           {
               row--; col--; // adjust for arrays
               grid[row][col].stat = true;
               
               if (status != Side.UR && status != Side.LR && status != Side.MR)
                   if (grid[row][col + 1].stat) union(grid[row][col], grid[row][col + 1]);

               if (status != Side.UL && status != Side.LL && status != Side.ML)
                   if (grid[row][col - 1].stat) union(grid[row][col], grid[row][col - 1]);

               if (status != Side.LL && status != Side.LR && status != Side.LM)
                   if (grid[row + 1][col].stat) union(grid[row][col], grid[row + 1][col]);         
               if (status != Side.UL && status != Side.UR && status != Side.UM)
                   if (grid[row - 1][col].stat) union(grid[row][col], grid[row - 1][col]);
           }
           else throw new IllegalArgumentException();
       }
   }
   
   // Determines if the coordinate is on an edge based on grid size
   private Side edgeDetect(int row, int col)
   {
       Status x = Status.MEDIUM, y = Status.MEDIUM;
       
       if (row >= val) x = Status.HIGH; 
       else if (row <= 1) x = Status.LOW;
       
       if (col >= val) y = Status.HIGH;
       else if (col <= 1) y = Status.LOW;
       
       if (x == Status.HIGH && y == Status.HIGH)
           return Side.LR;
       else if (x == Status.LOW && y == Status.HIGH)
           return Side.UR;       
       else if (x == Status.HIGH && y == Status.LOW)
           return Side.LL;    
       else if (x == Status.LOW && y == Status.LOW)
           return Side.UL;            
       else if (x == Status.HIGH && y == Status.MEDIUM)
           return Side.LM;    
       else if (x == Status.LOW && y == Status.MEDIUM)
           return Side.UM;            
       else if (x == Status.MEDIUM && y == Status.HIGH)
           return Side.MR;            
       else if (x == Status.MEDIUM && y == Status.LOW)
           return Side.ML;            
       else
           return Side.NA;
   }
   
   private Node root(Node p) // return Node value for type consistency
   {       
       while (p.row != grid[p.row][p.col].row || p.col != grid[p.row][p.col].col)
       {
           Node n = grid[p.row][p.col];
           grid[p.row][p.col] = grid[n.row][n.col];
           p = grid[p.row][p.col];
       }
       return p;                  
   }
   
   private boolean connected(Node p, Node q)
   {
       return root(p) == root(q);
   }
   
   private void union(Node p, Node q)
   {
       // to-do: implement a union system where the bigger sized tree hooks onto the smaller sized tree
       Node i = root(p);
       Node j = root(q);
       
       if (i != j)
       {
           if (sz[i.row][i.col] < sz[j.row][j.col])
           {
               grid[i.row][i.col] = j;
               sz[i.row][i.col] += sz[j.row][j.col];
           }
           else
           {
               grid[j.row][j.col] = i;
               sz[j.row][j.col] += sz[i.row][i.col];
           }
       }
   }
   
   public boolean isOpen(int row, int col) // is site (row, col) open?
   {
       if (rangeCheck(row, col, 1, val))
       {
           row--; col--; // adjust for arrays
           return grid[row][col].stat;
       }   
       else throw new IllegalArgumentException();
   }
   
   public boolean isFull(int row, int col) // is site (row, col) connected to a node from top row?
   {
       if (rangeCheck(row, col, 1, val))
       {
           row--; col--; // adjust for arrays
           for (int j = 0; j < val; j++)
               if (connected(grid[row][col], grid[0][j])) return true;

           return false;
       }   
       else throw new IllegalArgumentException();
   }
   
   public int numberOfOpenSites()// number of open sites
   {
       int open_cnt = 0;
       
       for (int i = 0; i < val; i++)
       {
           for (int j = 0; j < val; j++)
           {
               if (grid[i][j].stat == true) // check if grid is open
                   open_cnt++;
           }
       }
       return open_cnt;
   }
   
   // Checks top row to see if any are connected to bottom row to determine if the system percolates?
   public boolean percolates()
   {
       for (int i=0; i < val; i++) for (int j=0; j < val; j++) 
           if (connected(grid[val-1][i], grid[0][j])) return true;
       return false;
   }
   
   // Checks to see if the coordinate fits between min and max
   private boolean rangeCheck(int row, int col, int min, int max)
   {
       return singleCheck(row, min, max) && singleCheck(col, min, max);
   }
   
   private boolean singleCheck(int val, int min, int max)
   {
       return min <= val && val <= max;
   }
   
   private void outputRoots()
   {
       for (int i = 0; i < val; i++)
       {
           for (int j = 0; j < val; j++)
           {
               System.out.print("[" + root(grid[i][j]).row + " " + root(grid[i][j]).col + "] ");
           }
           System.out.println();
       }
       
       System.out.println("-----------------------------------");
       
       for (int i = 0; i < val; i++)
       {
           for (int j = 0; j < val; j++)
           {
               System.out.print("[");
               if (grid[i][j].stat)
                   System.out.print("-ON");
               else
                   System.out.print("OFF");
               System.out.print("] ");
           }
           System.out.println();
       }
   }
      
   public static void main(String[] args) // test client (optional)
   {
       Percolation myPerc = new Percolation(9);

       for (int i = 1; i <= 9; i++) myPerc.open(i,3);
       for (int i = 1; i <= 9; i++) myPerc.open(i,2);
       myPerc.open(8,2);
       myPerc.open(9,2);
       
       myPerc.outputRoots();
       
       if (myPerc.percolates())
           System.out.println("Percolation Confirmed");
       else
           System.out.println("No Percolation Spotted");
   }            
}