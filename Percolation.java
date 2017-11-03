// Programming Assignment 1: Percolation
// Written by Garland Poon
// Java version: 1.7.0.67
// https://algs4.cs.princeton.edu/windows/

// Write a program to estimate the value of the percolation threshold via Monte Carlo simulation.
// http://coursera.cs.princeton.edu/algs4/assignments/percolation.html

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

   private Node[][] grid;
   private int val;
   
   public Percolation(int n) // create n-by-n grid, with all sites blocked
   {
       int cnt = 0;
       
       if(n < 1)
           throw new IllegalArgumentException();
       
       grid = new Node[n][n]; 
       val = n;
       
       for(int i=0; i < n; i++)
       {
           for(int j=0; j < n; j++)
           {
               grid[i][j] = new Node(); // Each Node in the array needs initialization               
               grid[i][j].link_val = cnt;
               grid[i][j].stat = false;
               cnt++;
               
               // System.out.println(grid[i][j].link_val);
           }
       }
   }
  
   public void open(int row, int col) // open site (row, col) if it is not open already
   {
       if(rangeCheck(row, col, 1, val))
       {
           row--; col--; // adjust for arrays
           grid[row][col].stat = true;
           
           // connect branches => check north, south, east, west
           
       }
       else throw new IllegalArgumentException();
   }
   
   public boolean isOpen(int row, int col) // is site (row, col) open?
   {
       if(rangeCheck(row, col, 1, val))
       {
           row--; col--; // adjust for arrays
           return grid[row][col].stat;
       }   
       else throw new IllegalArgumentException();
   }
   
   public boolean isFull(int row, int col) // is site (row, col) connected to a node from top row?
   {
       if(rangeCheck(row, col, 1, val))
       {
           row--; col--; // adjust for arrays
           // need to check if the node is connected to a top row node. grid[0][0-n]
           return true;
       }   
       else throw new IllegalArgumentException();
   }
   
   public int numberOfOpenSites()// number of open sites
   {
       int open_cnt = 0;
       
       for(int i = 0; i < val; i++)
       {
           for(int j = 0; j < val; j++)
           {
               if(grid[i][j].stat == true) // check if grid is open
                   open_cnt++;
           }
       }
       return open_cnt;
   }
   
   public boolean percolates() // does the system percolate?
   {
       // scan top row to see if any are connected to bottom row
       
       return true;
   }
   
   private boolean rangeCheck(int row, int col, int min, int max) // does value fit between range?
   {
       if(singleCheck(row, min, max) && singleCheck(col, min, max)) return true;
       else return false;
   }
   
   private boolean singleCheck(int val, int min, int max)
   {
       if(min <= val && val <= max) return true;
       else return false;
   }

   public static void main(String[] args) // test client (optional)
   {
       Percolation myPerc = new Percolation(5);
       myPerc.open(1,3);
       myPerc.isFull(2, 5);
       myPerc.isOpen(1, 1);
       
       System.out.println("Percolation Check Complete");
   }            
}