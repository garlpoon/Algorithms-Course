// Programming Assignment 1: Percolation
// Written by Garland Poon

// Write a program to estimate the value of the percolation threshold via Monte Carlo simulation.
// http://coursera.cs.princeton.edu/algs4/assignments/percolation.html

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   
   private int[][] grid;
   
   public Percolation(int n) // create n-by-n grid, with all sites blocked
   {
       int count = 0;
       grid = new int[n][n]; 
       
       for(int i =0; i < n; i++)
       {
           for(int j =0; j < n; j++)
           {
               grid[i][j] = count;
               count++;
           }
       }
       System.out.print(count + "\n");
       
       for(int i =0; i < n; i++)
       {
           for(int j =0; j < n; j++)
               System.out.print(grid[i][j] + "\n");
       }
   }
  
   public void open(int row, int col) // open site (row, col) if it is not open already
   {
   
   }
   
   public boolean isOpen(int row, int col) // is site (row, col) open?
   {
       return false;
   }
   
   public boolean isFull(int row, int col) // is site (row, col) full?
   {
       return false;
   }
   
   public int numberOfOpenSites()// number of open sites
   {
       return 1;
   }
   
   public boolean percolates() // does the system percolate?
   {
       return false;
   }

   public static void main(String[] args) // test client (optional)
   {
       Percolation myPerc = new Percolation(5);
   }            
}
