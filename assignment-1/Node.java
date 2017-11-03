// Programming Assignment 1: Percolation
// Written by Garland Poon
// Java version: 1.7.0.67
// https://algs4.cs.princeton.edu/windows/

// Write a program to estimate the value of the percolation threshold via Monte Carlo simulation.
// http://coursera.cs.princeton.edu/algs4/assignments/percolation.html

public class Node {
	
   boolean stat = false;
   int row;
   int col;
   
   Node(int r, int c)
   {
       row = r;
       col = c;
   }
}