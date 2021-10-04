package graphs;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;


public class L4_Graph_sampleTest {

	@Test
	public void Test_Edit_A () 
	{
		 Graph<Character> g1 = new Graph<Character>(3);
		    
		 System.out.println ("TEST EDIT A BEGINS ***");
		 assertEquals(0, g1.getSize());
		    
		 try
		 {
			 g1.addNode('a');
		 }
		 catch (Exception e)
		 {
			 System.out.println ("No repeated nodes are allowed" + e);
		 }
		    
		 assertEquals(1, g1.getSize());
		 assertEquals(0,  g1.getNode('a'));
		 assertArrayEquals (new boolean[][]{{false,false,false}, {false,false,false}, {false,false,false}}, g1.getEdges());
		 assertArrayEquals (new double[][]{{0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}}, g1.getWeight());
		
		 // Test nodes for nodes not found
		 assertEquals(Graph.INDEX_NOT_FOUND,  g1.getNode('b'));
		 
		 
		 // No repeated nodes allowed
		 try
		 {
			 g1.addNode('a');
		 }
		 catch (Exception e)
		 {
			 System.out.println ("No repeated nodes are allowed" + e);
		 }
		 
		 
		 try
		 {
			 g1.addNode('b');
			 g1.addNode('c');
		 }
		 catch (Exception e)
		 {
			 System.out.println ("No repeated nodes are allowed" + e);
		 }

		 assertEquals(3, g1.getSize());
		 assertEquals(0, g1.getNode('a'));
		 assertEquals(1, g1.getNode('b'));
		 assertEquals(2, g1.getNode('c'));
			
		 assertArrayEquals (new boolean[][]{
			 {false,false,false}, 
			 {false,false,false}, 
			 {false,false,false}}, g1.getEdges());
		 assertArrayEquals (new double[][]{
			 {0.0, 0.0, 0.0}, 
			 {0.0, 0.0, 0.0}, 
			 {0.0, 0.0, 0.0}}, g1.getWeight());
		 
		 // Testing edges
		 try
		 {
			 assertEquals (false, g1.existsEdge('b', 'd'));
			 fail();
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		 
		 try
		 {
			 assertEquals (false, g1.existsEdge('b', 'c'));
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		 
		 assertArrayEquals (new boolean[][]{
			 {false,false,false}, 
			 {false,false,false}, 
			 {false,false,false}}, g1.getEdges());
		 assertArrayEquals (new double[][]{
			 {0.0, 0.0, 0.0}, 
			 {0.0, 0.0, 0.0}, 
			 {0.0, 0.0, 0.0}}, g1.getWeight());
		 
		 try
		 {
			 g1.addEdge ('b', 'c', 5.0);
			 assertEquals (true, g1.existsEdge('b', 'c'));
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		 
		 assertArrayEquals (new boolean[][]{
			 {false,false,false}, 
			 {false,false,true}, 
			 {false,false,false}}, g1.getEdges());
		 assertArrayEquals (new double[][]{
			 {0.0, 0.0, 0.0}, 
			 {0.0, 0.0, 5.0}, 
			 {0.0, 0.0, 0.0}}, g1.getWeight());
		 
		 try
		 {
			 g1.addEdge ('a', 'b', 2.0);
			 assertEquals (true, g1.existsEdge('a', 'b'));
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		 
		 assertArrayEquals (new boolean[][]{
			 {false,true,false}, 
			 {false,false,true}, 
			 {false,false,false}}, g1.getEdges());
		 assertArrayEquals (new double[][]{
			 {0.0, 2.0, 0.0}, 
			 {0.0, 0.0, 5.0}, 
			 {0.0, 0.0, 0.0}}, g1.getWeight());
		 
		 try
		 {
			 g1.addEdge ('a', 'c', 1.0);
			 assertEquals (true, g1.existsEdge('a', 'c'));
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		 
		 assertArrayEquals (new boolean[][]{
			 {false,true,true}, 
			 {false,false,true}, 
			 {false,false,false}}, g1.getEdges());
		 assertArrayEquals (new double[][]{
			 {0.0, 2.0, 1.0}, 
			 {0.0, 0.0, 5.0}, 
			 {0.0, 0.0, 0.0}}, g1.getWeight());
		 
		 try
		 {
			 g1.addEdge ('c', 'a', 3.0);
			 assertEquals (true, g1.existsEdge('a', 'c'));
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		 
		 assertArrayEquals (new boolean[][]{
			 {false,true,true}, 
			 {false,false,true}, 
			 {true,false,false}}, g1.getEdges());
		 assertArrayEquals (new double[][]{
			 {0.0, 2.0, 1.0}, 
			 {0.0, 0.0, 5.0}, 
			 {3.0, 0.0, 0.0}}, g1.getWeight());
		
		 g1.print();
		 
		 try
		 {
			 g1.removeNode ('a');
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Node does not exist" + e);
		 }
		 
		 g1.print();
	 
	 assertArrayEquals (new boolean[][]{
		 {false ,false ,true}, 
		 {true  ,false ,true}, 
		 {true  ,false ,false}}, g1.getEdges());
	 assertArrayEquals (new double[][]{
		 {0.0, 0.0, 1.0}, 
		 {5.0, 0.0, 5.0}, 
		 {3.0, 0.0, 0.0}}, g1.getWeight());
	}
}
