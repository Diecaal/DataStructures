import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.Algorithms;

public class AlgorithmsTest {

	@Test
	public void test() {
		assertEquals(720, Algorithms.factorial(6));
		assertEquals(720, Algorithms.factorialRec(6));
		assertEquals(1099511627776L, Algorithms.pow(40));
		//Too much time to test (exponential)
		//assertEquals(1099511627776L, Algorithms.powRec1(40));
		assertEquals(1099511627776L, Algorithms.powRec2(40));
		assertEquals(1099511627776L, Algorithms.powRec3(40));
		assertEquals(1099511627776L, Algorithms.powRec4(40));
	}

}
