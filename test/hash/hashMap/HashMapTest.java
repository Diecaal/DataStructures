package hash.hashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	public void removeAndQuadraticTest() throws Exception {
		// Example
		HashMap<Integer, Integer> a = new HashMap<Integer, Integer>(5, HashMap.LINEAR_PROBING, 1.0);
		a.put(0, 4);
		a.put(1, 13);
		a.put(2, 24);
		a.put(3, 3);
		a.remove(24);
		assertEquals(true, a.search(3));
		assertEquals("[0] (2) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());

		a.put(4, 15);
		assertEquals(true, a.search(3));
		assertEquals("[0] (1) = 15 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());

		// Example
		HashMap<Integer, Integer> b = new HashMap<Integer, Integer>(5, HashMap.QUADRATIC_PROBING, 1.0);
		b.put(0, 4);
		b.put(1, 13);
		b.put(2, 24);
		b.put(3, 3);

		b.remove(24);
		assertEquals(true, b.search(3));
		assertEquals("[0] (2) = 24 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());

		b.put(4, 15);
		assertEquals(true, b.search(3));
		assertEquals("[0] (1) = 15 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());

	}

	@Test
	public void double_hashing() throws Exception {
		/// Example
		HashMap<Character,Character> d = new HashMap<Character,Character>(5, HashMap.DOUBLE_HASHING, 0.5);
		assertEquals(0, d.f('A', 0));
		assertEquals(1, d.f('A', 1));
		assertEquals(2, d.f('A', 2));
		assertEquals(3, d.f('A', 3));
		assertEquals(4, d.f('A', 4));

		assertEquals(2, d.f('a', 0));
		assertEquals(4, d.f('a', 1));
		assertEquals(1, d.f('a', 2));
		assertEquals(3, d.f('a', 3));
		assertEquals(0, d.f('a', 4));
		// Example
		HashMap<Integer, Integer> c = new HashMap<Integer, Integer>(5, HashMap.DOUBLE_HASHING, 0.5);
		assertEquals(2, c.f(7, 0));
		assertEquals(4, c.f(7, 1));
		assertEquals(1, c.f(7, 2));
		assertEquals(3, c.f(7, 3));
		assertEquals(0, c.f(7, 4));

		assertEquals(0, c.f(0, 0));
		assertEquals(1, c.f(2, 4));
		assertEquals(2, c.f(3, 3));
		assertEquals(3, c.f(32, 1));
		assertEquals(4, c.f(1045, 2));

		c = new HashMap<Integer, Integer>(5, HashMap.DOUBLE_HASHING, 1.0);
		c.put(0, 4);
		c.put(1, 13);
		c.put(2, 24);
		c.put(3, 3);
		assertEquals("[0] (0) = null - [1] (1) = 3 - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
		c.remove(24);
		assertEquals("[0] (0) = null - [1] (1) = 3 - [2] (2) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
		assertEquals(true, c.search(3));

		c.put(4, 15);
		assertEquals(true, c.search(3));
		assertEquals("[0] (1) = 15 - [1] (1) = 3 - [2] (2) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
		c.remove(15);
		assertEquals(false, c.search(15));

		assertEquals("[0] (2) = 15 - [1] (1) = 3 - [2] (2) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
		assertFalse(c.search(24));
		c.put(5, 24);
		assertEquals("[0] (2) = 15 - [1] (1) = 3 - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());

		assertTrue(c.search(24));
	}

	@Test
	public void dynamic_resizing() throws Exception {
		// Example
		HashMap<Integer, Integer> a = new HashMap<Integer, Integer>(5, HashMap.LINEAR_PROBING, 0.5);
		a.put(0, 4);
		assertEquals(0.2, a.getLF(), 0.1);
		a.put(1, 13);
		assertEquals(0.4, a.getLF(), 0.1);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());

		a.put(2, 24); // DYNAMIC RESIZING!
		assertEquals(0.27, a.getLF(), 0.1);
		assertEquals(
				"[0] (0) = null - [1] (0) = null - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ",
				a.toString());

	}

	@Test
	public void dynamic_resizingV2() throws Exception {
		// Example
		HashMap<Integer, Integer> a = new HashMap<Integer, Integer>(5, HashMap.LINEAR_PROBING, 0.5, 0.2);
		a.put(0, 4);
		assertEquals(0.2, a.getLF(), 0.1);
		a.put(1, 13);
		assertEquals(0.4, a.getLF(), 0.1);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());

		a.put(2, 24); // DYNAMIC RESIZING!
		assertEquals(0.27, a.getLF(), 0.1);
		assertEquals(
				"[0] (0) = null - [1] (0) = null - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ",
				a.toString());
		try {
			a.remove(3);
			//fail();
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
		}
		try {
			a.remove(14);
			//fail();
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(
				"[0] (0) = null - [1] (0) = null - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ",
				a.toString());

		a.remove(4);
		assertEquals(
				"[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 24 - [4] (0) = null - [5] (0) = null - [6] (1) = 13 - ",
				a.toString());

		assertEquals(0.285, a.getLF(), 0.01);
	}
}
