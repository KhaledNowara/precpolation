
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class PercolationStats {
	private Percolation p ;
	private double[] x ;
	private int T;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
    	if (n <=0 || trials <=0 ) throw new IllegalArgumentException ();
    	T = trials; 
    	x = new double [trials];
    	for (int i = 0 ; i < trials; i++) {
    		p = new Percolation (n);
    		while (!p.percolates()) {
    			int a = StdRandom.uniform(n)+1;
    			int b = StdRandom.uniform(n)+1;
    			if (! p.isOpen(a,b)) {
    			p.open(a, b);
    			}
    			}
    		
    		double f = ((double)p.numberOfOpenSites())/( n*n);
    		x[i] = f;
    	}
    }

    // sample mean of percolation threshold
    public double mean() {
    	return StdStats.mean(x);
    	
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
    	return StdStats.stddev(x);
    	
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	double average = mean();
    	double stdDev = stddev();
    	return average - ((1.96*stdDev)/Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	double average = mean();
    	double stdDev = stddev();
    	return average + ((1.96*stdDev)/Math.sqrt(T));
    }

   // test client (see below)
   public static void main(String[] args) {
	   
	   if (args.length == 2) {
	   int n = Integer.parseInt(args[0]);
	   int t =Integer.parseInt(args[1]);
	   PercolationStats p = new PercolationStats (n,t);
	   System.out.println("mean = " + p.mean());
	   System.out.println("stddev = " +p.stddev());
	   System.out.println ("95 % confidence interval = [" +  p.confidenceLo() + ", " + p.confidenceHi() + "]");
	   }
   }

}
