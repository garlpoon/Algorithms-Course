// Programming Assignment 1: Percolation
// Written by Garland Poon
// Java version: 1.7.0.67
// https://algs4.cs.princeton.edu/windows/

// Write a program to apply Monte Carlo simulations onto Percolation.java.
// http://coursera.cs.princeton.edu/algs4/assignments/percolation.html

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
   
   private double[] mean_arr;
   
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
       Percolation trial_set = new Percolation(n);
       
       mean_arr = new double[trials];
       int cnt = 0, j = 0;
       double max_steps = n*n;
       
       int x = StdRandom.uniform(1, n+1), 
           y = StdRandom.uniform(1, n+1);
       
       boolean end_flag = false;
              
       for(int i=0;i<trials;i++)
       {
           end_flag = false; cnt = 0;
           //while(!end_flag && cnt >= max_steps)
           while(!end_flag)
           {
               while(trial_set.isOpen(x, y)) // loop until fresh x & y is found
               {
                   x = StdRandom.uniform(1, n+1);
                   y = StdRandom.uniform(1, n+1);
               }
               
               //System.out.println(x + " " + y);
               trial_set.open(x,y);
               cnt++;
               
               if(trial_set.percolates())
               {
                   end_flag = true;
               }
               
           }
           
           //trial_set.outputRoots();
           trial_set = new Percolation(n); // reset
           mean_arr[i] = cnt/max_steps;
           
           j = i + 1;
           System.out.println(j);
       }
   }
   public double mean()                          // sample mean of percolation threshold
   {
       //System.out.println(mean_sum + "---------" + trial_num);
       return StdStats.mean(mean_arr);
   }
   
   public double stddev()                        // sample standard deviation of percolation threshold
   {
       return StdStats.stddev(mean_arr);
   }
   
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
       double diff = (1.96*StdStats.stddev(mean_arr))/Math.sqrt(mean_arr.length);
       return StdStats.mean(mean_arr) - diff;
   }
   
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
       double diff = (1.96*StdStats.stddev(mean_arr))/Math.sqrt(mean_arr.length);
       return StdStats.mean(mean_arr) + diff;
   }
   
   public static void main(String[] args)        // test client (described below)
   {
       PercolationStats myStats = new PercolationStats(2, 100000);
       System.out.println("mean                    = " + myStats.mean());
       System.out.println("stddev                  = " + myStats.stddev());
       System.out.println("95% confidence interval = [" + myStats.confidenceLo() + ", " + myStats.confidenceHi()+ "]");
   }
}
