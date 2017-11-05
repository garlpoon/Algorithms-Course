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
       
       int max_steps = n*n, cnt = 0, mean_sum;
              
       int x = StdRandom.uniform(1, n+1), 
           y = StdRandom.uniform(1, n+1);
       
       boolean end_flag = false;
              
       for(int i=0;i<trials;i++)
       {
           end_flag = false; cnt = mean_sum = 0; 
           //while(!end_flag && cnt >= max_steps)
           while(!end_flag)
           {
               while(trial_set.isOpen(x, y)) // loop until fresh x & y is found
               {
                   x = StdRandom.uniform(1, n+1);
                   y = StdRandom.uniform(1, n+1);
               }
               
               System.out.println(x + " " + y);
               trial_set.open(x,y);
               cnt++;
               
               if(trial_set.percolates())
               {
                   end_flag = true;
                   trial_set.outputRoots();
                   trial_set = new Percolation(n); // reset
               }
               
           }
           
           mean_sum += cnt/max_steps;
       }
   }
   public double mean()                          // sample mean of percolation threshold
   {
       return mean_sum/trial_num;
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
       PercolationStats myStats = new PercolationStats(15, 100);
       
   }
}