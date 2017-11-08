// Programming Assignment 1: Percolation
// Written by Garland Poon
// Java version: 1.7.0.67
// https://algs4.cs.princeton.edu/windows/

// Write a program to apply Monte Carlo simulations onto Percolation.java.
// http://coursera.cs.princeton.edu/algs4/assignments/percolation.html

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
   
   private final double[] meanArr;
   
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
       Percolation trialSet = new Percolation(n);
       
       meanArr = new double[trials];
       int cnt = 0; 
       // int j = 0;
       double maxSteps = n*n;
       
       int x = StdRandom.uniform(1, n+1), 
           y = StdRandom.uniform(1, n+1);
       
       boolean endFlag = false;
              
       for (int i = 0; i < trials; i++)
       {
           endFlag = false; 
           cnt = 0;
           
           while (!endFlag)
           {
               while (trialSet.isOpen(x, y)) // loop until fresh x & y is found
               {
                   x = StdRandom.uniform(1, n+1);
                   y = StdRandom.uniform(1, n+1);
               }
               
               trialSet.open(x, y);
               cnt++;
               
               if (trialSet.percolates())
               {
                   endFlag = true;
               }
           }
           
           trialSet = new Percolation(n); // reset
           meanArr[i] = cnt/maxSteps;
           
           // j = i + 1;
           // System.out.println(j);
       }
   }
   public double mean()                          // sample mean of percolation threshold
   {
       return StdStats.mean(meanArr);
   }
   
   public double stddev()                        // sample standard deviation of percolation threshold
   {
       return StdStats.stddev(meanArr);
   }
   
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
       double diff = (1.96 * StdStats.stddev(meanArr)) / Math.sqrt(meanArr.length);
       return StdStats.mean(meanArr) - diff;
   }
   
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
       double diff = (1.96 * StdStats.stddev(meanArr)) / Math.sqrt(meanArr.length);
       return StdStats.mean(meanArr) + diff;
   }
   
   public static void main(String[] args)        // test client (described below)
   {
       PercolationStats myStats = new PercolationStats(2, 100000);
       System.out.println("mean                    = " + myStats.mean());
       System.out.println("stddev                  = " + myStats.stddev());
       System.out.println("95% confidence interval = [" + myStats.confidenceLo() + ", " + myStats.confidenceHi()+ "]");
   }
}