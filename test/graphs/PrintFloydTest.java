package graphs;
import static org.junit.Assert.*;

import org.junit.Test;


public class PrintFloydTest {


	@Test
	public void testFloydRefined()  
	{
		 Graph<String> g1 = new Graph<String>(4);
		 
		 try {
			 g1.addNode("A");
			 g1.addNode("B");
			 g1.addNode("C");
			 g1.addNode("D");
			 
			 
			 g1.addEdge("B", "A", 0.2);
			 g1.addEdge("A", "C", 0.2);
			
			 g1.addEdge("B", "C", 1);
			 g1.addEdge("D", "B", 3);
			 g1.addEdge("D", "C", 5);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 g1.floyd(g1.getSize());
	
		 
		assertEquals("D-B-A-C",g1.printFloydPath("D", "C"));
		assertEquals("B-A-C",g1.printFloydPath("B", "C"));
		
	}
	
}
