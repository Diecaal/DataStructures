package algortihms;

public class Algorithms {

	/**
	 * Executes a linear algorithm O(n) given an n workload
	 * @param n Long - algorithm workload
	 */
	public static void linear(int n) {
		long start = System.currentTimeMillis();
		for(int i=0;i<=n;i++) {
			// Slow down each iteration 25 milliseconds
			TestBench.doNothing(i); 
		}
		long end = System.currentTimeMillis();
		System.out.println(String.format("Execution time: %s miliseconds", end-start) );
	}
	
	public static void quadratic(int n) {
		
	}
	
	public static void cubic(int n) {
		
	}
	
	public static void logarithmic(int n) {
		
	}
}
