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
	
	/**
	 * Iterative method to calculate the factorial of a number - O(n)
	 * @param n workload - number to calculate its factorial
	 * @return long factorial of workload
	 */
	public static long factorial(long n) {
		long result = 1;
		while(n > 0) {
			result *= n;
			n--;
		}
		return result;
	}

	/**
	 * Recursive method to calculate the factorial of a number - O(n)
	 * @param n workload - number to calculate its factorial
	 * @return long factorial of workload
	 */
	public static long factorialRec(long n) {
		if(n==0||n==1) 
			return 1;
		else
			return n * factorialRec(n-1);
	}
	
	/**
	 * Iterative method to get the power of a number n - O(n)
	 * @param n workload - number to calculate its power 
	 * @return long power of workload
	 */
	public static long pow(long n) {
		TestBench.doNothing(n);
		if(n==0) 
			return 1;
		long result = 2;
		while(n>1) {
			result *= 2;
			n--;
		}
		return result;
	}
	
	/**
	 * Power function recursive version of O(2^n)
	 * @param n workload - number to calculate its power 
	 * @return long power of workload
	 */
	public static long powRec1(long n) {
		TestBench.doNothing(n);
		if(n==0)
			return 1;
		else
			return powRec1(n-1) + powRec1(n-1);
	}
	
	/**
	 * Power function recursive version of O(n)
	 * @param n workload - number to calculate its power 
	 * @return long power of workload
	 */
	public static long powRec2(long n) {
		TestBench.doNothing(n);
		if(n==0)
			return 1;
		else
			return 2 * powRec2(n-1);
	}
	
	/**
	 * Power function recursive version of O(n)
	 * @param n workload - number to calculate its power 
	 * @return long power of workload
	 */
	public static long powRec3(long n) {
		TestBench.doNothing(n);
		if(n==0)
			return 1;
		else {
			if(n%2 != 0) { // odd numbers - rounding issue
				return (powRec3(n/2) * powRec3(n/2)) * 2;
			}
			return powRec3(n/2) * powRec3(n/2);
		}
	}
	
	/**
	 * Power function recursive version of O(log n)
	 * @param n workload - number to calculate its power 
	 * @return long power of workload
	 */
	public static long powRec4(long n) {
		TestBench.doNothing(n);
		if(n==0)
			return 1;
		else {
			long val = powRec4(n/2);
			if(n%2 != 0) { // odd numbers - rounding issue
				return (val * val) * 2;
			}
			return val * val;
		}
	}
	
}
