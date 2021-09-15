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
		long start = System.currentTimeMillis();
		for(int i=0;i<=n;i++) {
                for(int j=0;j<=n;j++) {
			// Slow down each iteration 25 milliseconds
			TestBench.doNothing(i); 
		}
                }
		long end = System.currentTimeMillis();
		System.out.println(String.format("Execution time: %s miliseconds", end-start) );
	}
	
	/**
	 * Executes a cubic algorithm O(n^3) given an n workload
	 * @param n Long - algorithm workload
	 */
	public static void cubic(int n) {
		long start = System.currentTimeMillis();
		for(int i=0;i<=n;i++) {
                for(int j=0;j<=n;j++) {
                for(int k=0;k<=n;k++) {
			// Slow down each iteration 25 milliseconds
			TestBench.doNothing(i); 
		}
                }
                }
		long end = System.currentTimeMillis();
		System.out.println(String.format("Execution time: %s miliseconds", end-start) );
	}
	
	/**
	 * Executes a logarithmic algorithm O(log n) given an n workload
	 * @param n Long - algorithm workload
	 */
	public static void logarithmic(int n) {
		long start = System.currentTimeMillis();
                while(n>0) {
                TestBench.doNothing(n); 
                }
                n /= 2;
                long end = System.currentTimeMillis();
	}
}
