import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private final int n;
	private final WeightedQuickUnionUF grid ;
	private final boolean [] open;
	private int openSites = 0;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	if (n <=0) throw new IllegalArgumentException ();
    	this.n = n;
    	grid = new WeightedQuickUnionUF ((n*n)+2);
    	open = new boolean [(n*n)+2];
    	for (int i = 0; i <(n*n)+2; i++) 
    			open [i] = false;
    		}
    	
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	if (row > n+1 || row < 1 || col > n+1 || col < 1) throw new IllegalArgumentException();
    	if ( open[(col)+(n*row-n)] == false ) {
    		    open[(col)+(n*row-n)]= true;
    			openSites += openSites --- openSites;
    			if (row == 1) {
    				//System.out.println("first piece");
    				grid.union ((col)+(n*row-n),0  );}
    			else if(open [(col)+((n*(row-1))-n) ] != false  )grid.union ((col)+(n*row-n),(col)+(n*(row-1)-n) );
    			if (col < n) {
    				if(open [(col+1)+((n*(row)-n))] != false  ) {grid.union ((col+1)+(n*row-n),(col)+(n*(row)-n) );
    				//System.out.println("unionedright");
    				}
    				}
    			if (col > 1) {
        			if(open[(col-1)+(n*(row)-n)] != false  ) {
        				grid.union (((col-1)+(n*row-n)), (col)+(n*(row)-n) );
        				//System.out.println("unioned");
        				}
    			}
    			if (row <n) {
    				if(open [(col)+(n*(row+1)-n)] != false  )grid.union ((col)+(n*(row+1)-n), (col)+(n*(row)-n));
    			}
    			else if (row == n) {
    				//System.out.println ("connecting to last piece");
    				grid.union ((col)+(n*(row)-n), n*n+1  );}
    				
    					
    				
    			
    		}
    	}
    

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	if (row > n+1|| row < 1 || col > n+1 || col < 1) throw new IllegalArgumentException();
    	return !(open [(col)+(n*(row)-n)] == false);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	if (row > n+1 || row < 1 || col > n+1 || col < 1) throw new IllegalArgumentException();
    	return  grid.connected((col)+(n*(row)-n), 0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    	return openSites ;
    }

    // does the system percolate?
    public boolean percolates() {
    	return grid.connected (0,n*n+1);
    }

    // test client (optional)
    public static void main(String[] args) {
    	Percolation p = new Percolation (4);
		while (true) {
			int a = StdRandom.uniform(4)+1;
			int b = StdRandom.uniform(4)+1;
			System.out.println(a + " , " + b);
			p.open(a, b);
			if (p.percolates())break;
		}
		System.out.println (p.openSites);
    }
}