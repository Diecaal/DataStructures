package graphs;

import static org.junit.Assert.*;

import org.junit.Test;

public class IsSourceNodeTest {

	@Test
	public void test() {
		Graph<Character> g1 = new Graph<Character>(5);

		System.out.println("TEST DRAIN B BEGINS ***");
		assertEquals(0, g1.getSize());

		try {
			g1.addNode('a');
			g1.addNode('b');
			g1.addNode('c');
			g1.addNode('d');
			g1.addNode('e');
		} catch (Exception e) {
			System.out.println("No repeated nodes are allowed" + e);
		}

		try {
			// A B C will be source nodes
			g1.addEdge('a', 'd', 5.0);
			g1.addEdge('b', 'd', 5.0);
			g1.addEdge('c', 'e', 5.0);
			g1.addEdge('c', 'e', 5.0);
		} catch (Exception e) {
			System.out.println("Departure or arrival node does not exist" + e);
		}

		g1.print();

		assertEquals(3, g1.countSourceNodes());

		try {
			// B C wont be source nodes anymore
			g1.addEdge('d', 'a', 5.0);
			g1.addEdge('c', 'b', 5.0);
		} catch (Exception e) {
			System.out.println("Departure or arrival node does not exist" + e);
		}

		g1.print();

		assertEquals(1, g1.countSourceNodes());
	}

}
