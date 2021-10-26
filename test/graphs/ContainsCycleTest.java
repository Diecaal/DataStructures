package graphs;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContainsCycleTest {

	@Test
	public void Test_Edit () 
	{
		 Graph<Character> g1 = new Graph<Character>(4);
		    
		 System.out.println ("TEST EDIT BEGINS *");
		 assertEquals(0, g1.getSize());
		 
		 try {
			 g1.addNode('a');
			 g1.addNode('b');
			 g1.addNode('c');
			 g1.addNode('d');
			 
			 g1.addEdge('a', 'b', 1.0);
			 g1.addEdge('b', 'c', 1.0);
			 g1.addEdge('c', 'd', 1.0);
			 g1.addEdge('d', 'a', 1.0);
		 } catch(Exception e) {
			 
		 }
		 
		 assertEquals(true, g1.containsCycles());
		 
		 try {
			 g1.removeEdge('d', 'a');
		 } catch(Exception e) {
			 
		 }
		 
		 assertEquals(false, g1.containsCycles());
	}

}