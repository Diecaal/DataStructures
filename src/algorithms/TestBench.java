package algorithms;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestBench {

	public static final int SLEEP_TIME = 2;

	public static void main(String[] args) {
		TestBench.testAlgorithm("algorithms.Algorithms", "files/linear", "linear.txt", 3, 1, 50);
		TestBench.testAlgorithm("algorithms.Algorithms", "files/quadratic", "quadratic.txt", 3, 1, 50);
		TestBench.testAlgorithm("algorithms.Algorithms", "files/cubic", "cubic.txt", 3, 1, 20);
		TestBench.testAlgorithm("algorithms.Algorithms", "files/logarithmic", "logarithmic.txt", 3, 1, 50);
	}

	/**
	 * Performs an already decided algorithm for a given workload and examples
	 * 
	 * @param outputFileName file to output the results
	 * @param samples        number of times to perform the experiment
	 * @param startN         maximum n workload
	 * @param endN           minimum n workload
	 */
	public static void test(String outputFileName, int samples, int startN, int endN) {
		List<Long> executions = new ArrayList<>();
		for (int i = startN; i <= endN; i++) {
			long start = System.currentTimeMillis();
			for (int currentSample = 0; currentSample < samples; currentSample++) {
				// TestBench.testAlgorithm("Algorithms", "linear", i);
			}
			long end = System.currentTimeMillis();
			executions.add(end - start);
		}
		TestBench.writeResults(outputFileName, executions);
	}

	/**
	 * Creates a new object of class "className" during execution time and invokes
	 * it "methodName" method
	 * 
	 * @param className  to be created
	 * @param methodName to be executed
	 * @param n          workload to the method
	 */
	public static void testAlgorithm(String className, String methodName, String outputFileName, int samples,
			int startN, int endN) {
		try {
			Class<?> classDetected = Class.forName(className);
			Object classObject = classDetected.newInstance();
			Method classMethod = classObject.getClass().getMethod(methodName, int.class);
			List<Long> executions = new ArrayList<>();
			for (int i = startN; i <= endN; i++) {
				long start = System.currentTimeMillis();
				for (int currentSample = 0; currentSample < samples; currentSample++) {
					classMethod.invoke(classObject, i);
					System.out.println(methodName);
				}
				long end = System.currentTimeMillis();
				executions.add(end - start);
			}
			TestBench.writeResults(outputFileName, executions);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Either class name or method name given is not valid");
		}
	}

	/**
	 * Prevents more code from being executed during SLEEP_TIME constant value
	 * 
	 * @param i time to be added to current time to stop execution
	 */
	public static void doNothing(int i) {
		// System.out.println(String.format("Doing nothing at iteration: %s", i));
		long endTime = System.currentTimeMillis() + SLEEP_TIME;
		while (System.currentTimeMillis() < endTime) {
			// Do nothing
			// Iteration stopped until 25 milliseconds passes
		}
	}

	/**
	 * Write a list of given execution times to an output file
	 * 
	 * @param fileName   output file name
	 * @param executions list containing the execution times
	 */
	public static void writeResults(String fileName, List<Long> executions) {
		FileWriter file = null;
		PrintWriter pw = null;
		try {
			file = new FileWriter(fileName);
			pw = new PrintWriter(file);
			for (Long x : executions)
				pw.println(x);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null) {
					file.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
