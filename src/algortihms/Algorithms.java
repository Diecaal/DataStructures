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
	
	/**
	 * Executes a quadratic algorithm O(n^2) given an n workload
	 * @param n Long - algorithm workload
	 */
	public static void quadratic(int n) {
		
	}
	
	/**
	 * Executes a cubic algorithm O(n^3) given an n workload
	 * @param n Long - algorithm workload
	 */
	public static void cubic(int n) {
		
	}
	
	/**
	 * Executes a logarithmic algorithm O(log n) given an n workload
	 * @param n Long - algorithm workload
	 */
	public static void logarithmic(int n) {
		
	}
}
