// Programming Assignment 1: Percolation
// Written by Garland Poon
// Java version: 1.7.0.67
// https://algs4.cs.princeton.edu/windows/

// Write a program to apply Monte Carlo simulations onto Percolation.java.
// http://coursera.cs.princeton.edu/algs4/assignments/percolation.html

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
   
   private int trial_num, mean_sum;
   
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
       Percolation trial_set = new Percolation(n);
       int max_steps = n*n, cnt = 0;
       boolean end_flag = false;
       
       for(int i=0;i<trials;i++)
       {
           end_flag = false; cnt = 0;
           while(!end_flag && cnt >= max_steps)
           {
               
               cnt++;
           }
       }
   }
   public double mean()                          // sample mean of percolation threshold
   {
       return trial_num/mean_sum;
   }
   
   /*
   public double stddev()                        // sample standard deviation of percolation threshold
   {
   }
   
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
   }
   
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
   }
*/
   public static void main(String[] args)        // test client (described below)
   {
   }
}