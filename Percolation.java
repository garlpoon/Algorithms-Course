// Programming Assignment 1: Percolation
// Written by Garland Poon

// Write a program to estimate the value of the percolation threshold via Monte Carlo simulation.
// http://coursera.cs.princeton.edu/algs4/assignments/percolation.html

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   
   private boolean[][] grid;
   private int val;
   
   public Percolation(int n) // create n-by-n grid, with all sites blocked
   {
       //int count = 0;
       if(n < 1)
           throw new IllegalArgumentException();
       
       grid = new boolean[n][n]; 
       val = n;
       
       for(int i =0; i < n; i++)
       {
           for(int j =0; j < n; j++)
           {
               //grid[i][j] = count;
               //count++;
               grid[i][j] = false;
           }
       }
       /*
       
       System.out.print(count + "\n");
       
       for(int i =0; i < n; i++)
       {
           for(int j =0; j < n; j++)
               System.out.print(grid[i][j] + "\n");
       }
       */
   }
  
   public void open(int row, int col) // open site (row, col) if it is not open already
   {
       if(rangeCheck(row, col, 0, val))
       {
           grid[row][col] = true;
       }   
       else
       {
           throw new IllegalArgumentException();
       }
   }
   
   public boolean isOpen(int row, int col) // is site (row, col) open?
   {
       if(rangeCheck(row, col, 0, val))
       {
           return false;
       }   
       else
       {
           throw new IllegalArgumentException();
       }
   }
   
   public boolean isFull(int row, int col) // is site (row, col) full?
   {
       if(rangeCheck(row, col, 0, val))
       {
           return false;
       }   
       else
       {
           throw new IllegalArgumentException();
       }
   }
   
   public int numberOfOpenSites()// number of open sites
   {
       return 1;
   }
   
   public boolean percolates() // does the system percolate?
   {
       return true;
   }
   
   private boolean rangeCheck(int row, int col, int min, int max) // does value fit between range?
   {
       if(min < row && row < max && min < col && col < max)
           return true;
       else
           return false;
   }

   public static void main(String[] args) // test client (optional)
   {
       Percolation myPerc = new Percolation(5);
       //myPerc.open(1, 1);
       //myPerc.isFull(10, 10);
       //myPerc.isOpen(10, 10);
   }            
}