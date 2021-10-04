package graphs;

import static org.junit.Assert.*;

import org.junit.Test;

public class IsDrainNodeTest {

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
			// C D will be drain nodes
			g1.addEdge('a', 'b', 5.0);
			g1.addEdge('a', 'c', 5.0);
			g1.addEdge('a', 'd', 5.0);
			g1.addEdge('e', 'b', 5.0);
			g1.addEdge('e', 'c', 5.0);
			g1.addEdge('e', 'd', 5.0);
			g1.addEdge('b', 'a', 5.0);
			g1.addEdge('b', 'c', 5.0);
		} catch (Exception e) {
			System.out.println("Departure or arrival node does not exist" + e);
		}

		g1.print();
			
		assertEquals(2, g1.countDrainNodes());

		try {
			// C wont be drain node anymore
			g1.addEdge('c', 'a', 5.0);
			g1.addEdge('c', 'd', 5.0);
		} catch (Exception e) {
			System.out.println("Departure or arrival node does not exist" + e);
		}

		g1.print();

		assertEquals(1, g1.countDrainNodes());
	}

}
