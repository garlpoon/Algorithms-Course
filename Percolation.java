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
       int count = 0;
       
       if(n < 1)
           throw new IllegalArgumentException();
       
       grid = new Node[n][n]; 
       val = n;
       
       for(int i=0; i < n; i++)
       {
           for(int j=0; j < n; j++)
           {
               grid[i][j] = new Node(); // Each Node in the array needs initialization               
               grid[i][j].link_val = count;
               grid[i][j].stat = false;
               count++;
               
               System.out.println(grid[i][j].link_val);
           }
       }
   }
  
   public void open(int row, int col) // open site (row, col) if it is not open already
   {
       if(rangeCheck(row, col, 0, val))
       {
           grid[row][col].stat = true;
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
       int open_cnt = 0;
       
       for(int i = 0; i < val; i++)
       {
           for(int j = 0; j < val; j++)
           {
               if(grid[i][j].stat == true) // check if grid is open
               {
                   open_cnt++;
               }
           }
       }
       return open_cnt;
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