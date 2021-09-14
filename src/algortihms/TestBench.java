package algortihms;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TestBench {

	public static final int SLEEP_TIME = 25;
	
	public static void main(String[] args) {
		TestBench.test("linear.txt",0,8);
	}
	
	public static void test(String outputFileName, int startN, int endN) {
		List<Long> executions = new ArrayList<>();
		for(int i=startN; i<=endN; i++) {
			long start = System.currentTimeMillis();
			Algorithms.linear(i);
			long end = System.currentTimeMillis();
			executions.add(end - start);
		}
		TestBench.writeResults(outputFileName, executions);
	}
	
	public static void doNothing(int i) {
		System.out.println(String.format("Doing nothing at iteration: %s", i));
		long endTime = System.currentTimeMillis() + SLEEP_TIME;
		while(System.currentTimeMillis() < endTime) {
			// Do nothing
			// Iteration stopped until 25 milliseconds passes
		}
	}
	
	public static void writeResults(String fileName, List<Long> executions) {
		FileWriter file = null;
		PrintWriter pw = null;
		try {
			file = new FileWriter(fileName);
			pw = new PrintWriter(file);
			for(Long x : executions) pw.println(x);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(file != null) {
					file.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}

