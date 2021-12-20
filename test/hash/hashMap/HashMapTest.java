package hash.hashMap;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class HashMapTest {

	@Test
	public void fTest() throws Exception {
		// Example
		HashMap<Integer, Integer> a = new HashMap<Integer, Integer>(5, HashMap.LINEAR_PROBING, 0.5);
		assertEquals(2, a.f(7, 0));
		assertEquals(3, a.f(7, 1));
		assertEquals(4, a.f(7, 2));
		assertEquals(0, a.f(7, 3));

		// Example
		HashMap<Integer, Integer> b = new HashMap<Integer, Integer>(5, HashMap.QUADRATIC_PROBING, 0.5);
		assertEquals(2, b.f(7, 0));
		assertEquals(3, b.f(7, 1));
		assertEquals(1, b.f(7, 2));
		assertEquals(1, b.f(7, 3));
		// Example
		HashMap<Character,Character> a2 = new HashMap<Character,Character>(5, HashMap.LINEAR_PROBING, 0.5);
		assertEquals(0, a2.f('A', 0));
		assertEquals(1, a2.f('A', 1));
		assertEquals(2, a2.f('A', 2));
		assertEquals(3, a2.f('A', 3));

		// Example
		HashMap<Character,Character> b2 = new HashMap<Character,Character>(5, HashMap.QUADRATIC_PROBING, 0.5);
		assertEquals(0, b2.f('A', 0));
		assertEquals(1, b2.f('A', 1));
		assertEquals(4, b2.f('A', 2));
		assertEquals(4, b2.f('A', 3));
	}

	@Test
	public void putAndSearchTest() throws Exception {

		// Example
		HashMap<Integer, Integer> a = new HashMap<Integer, Integer>(5, HashMap.LINEAR_PROBING, 1.0);
		a.put(0, 4);
		a.put(1, 13);
		a.put(2, 24);
		a.put(3, 3);

		assertEquals("[0] (1) = 0 : 4 - [1] (1) = 1 : 13 - [2] (1) = 2 : 24 - [3] (1) = 3 : 3 - [4] (0) = null : null - ", a.toString());
		assertEquals(true, a.search(3));
		assertEquals(false, a.search(12));

		// Example
		HashMap<Integer, Integer> b = new HashMap<Integer, Integer>(5, HashMap.QUADRATIC_PROBING, 1.0);
		b.put(0, 4);
		b.put(1, 13);
		b.put(2, 24);
		b.put(3, 3);

		assertEquals("[0] (1) = 0 : 4 - [1] (1) = 1 : 13 - [2] (1) = 2 : 24 - [3] (1) = 3 : 3 - [4] (0) = null : null - ", a.toString());
		assertEquals(true, b.search(3));
		assertEquals(false, b.search(12));
	}

	@Test
	public void dynamic_resizing() throws Exception {
		// Example
		HashMap<Integer, Integer> a = new HashMap<Integer, Integer>(5, HashMap.LINEAR_PROBING, 0.5);
		a.put(0, 4);
		assertEquals(0.2, a.getLF(), 0.1);
		a.put(1, 13);
		assertEquals(0.4, a.getLF(), 0.1);
		assertEquals("[0] (1) = 0 : 4 - [1] (1) = 1 : 13 - [2] (0) = null : null - [3] (0) = null : null - [4] (0) = null : null - ", a.toString());

		a.put(2, 24); // DYNAMIC RESIZING!
		assertEquals(0.27, a.getLF(), 0.1);
		assertEquals(
				"[0] (1) = 0 : 4 - [1] (1) = 1 : 13 - [2] (1) = 2 : 24 - [3] (0) = null : null - [4] (0) = null : null - [5] (0) = null : null - [6] (0) = null : null - [7] (0) = null : null - [8] (0) = null : null - [9] (0) = null : null - [10] (0) = null : null - ",
				a.toString());

	}

}
