package graphs;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrimTest {

	@Test
	public void test() {
		Graph<String> g = new Graph<String>(6);
		
		try {
			g.addNode("V1");
			g.addNode("V2");
			g.addNode("V3");
			g.addNode("V4");
			g.addNode("V5");
			g.addNode("V6");
			
			g.addEdge("V1", "V2", 1.0);
			g.addEdge("V1", "V3", 3.0);
			g.addEdge("V1", "V4", 3.0);
			g.addEdge("V2", "V3", 2.0);
			g.addEdge("V2", "V4", 2.0);
			g.addEdge("V2", "V6", 1.0);
			g.addEdge("V3", "V4", 1.0);
			g.addEdge("V3", "V5", 2.0);
			g.addEdge("V4", "V5", 3.0);
			g.addEdge("V4", "V6", 2.0);
			g.turnEdgesToBidirectional();
		} catch(Exception e) {
			
		}
		
		assertEquals("[V1, V2, V6, V3, V4, V5]", g.prim("V1"));
	}
	
	@Test
	public void test02() {
		Graph<String> g = new Graph<String>(5);
		
		try {
			g.addNode("V1");
			g.addNode("V2");
			g.addNode("V3");
			g.addNode("V4");
			g.addNode("V5");
			
			g.addEdge("V1", "V2", 2.0);
			g.addEdge("V1", "V3", 3.0);
			g.addEdge("V1", "V5", 2.0);
			g.addEdge("V2", "V3", 2.0);
			g.addEdge("V3", "V4", 1.0);
			g.addEdge("V3", "V5", 1.0);
			g.addEdge("V4", "V5", 2.0);
			g.turnEdgesToBidirectional();
		} catch(Exception e) {
			
		}
		
		assertEquals("[V3, V4, V5, V2, V1]", g.prim("V3"));
	}

}
