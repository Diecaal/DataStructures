package algorithms;

public class Algorithms {

	/**
	 * Executes a linear algorithm O(n) given an n workload
	 * 
	 * @param n Long - algorithm workload
	 */
	public static void linear(int n) {
		for (int i = 0; i <= n; i++) {
			// Slow down each iteration some milliseconds
			TestBench.doNothing(i);
		}
	}

	/**
	 * Executes a quadratic algorithm O(n^2) given an n workload
	 * 
	 * @param n Long - algorithm workload
	 */
	public static void quadratic(int n) {
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				// Slow down each iteration some milliseconds
				TestBench.doNothing(i);
			}
		}
	}

	/**
	 * Executes a cubic algorithm O(n^3) given an n workload
	 * 
	 * @param n Long - algorithm workload
	 */
	public static void cubic(int n) {
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				for (int k = 0; k <= n; k++) {
					// Slow down each iteration some milliseconds
					TestBench.doNothing(i);
				}
			}
		}
	}

	/**
	 * Executes a logarithmic algorithm O(log n) given an n workload
	 * 
	 * @param n Long - algorithm workload
	 */
	public static void logarithmic(int n) {
		while (n > 0) {
			// Slow down each iteration some milliseconds
			TestBench.doNothing(n);
			n /= 2;
		}
	}
}
