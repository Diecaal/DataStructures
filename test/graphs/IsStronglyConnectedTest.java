package graphs;

import static org.junit.Assert.*;

import org.junit.Test;

public class IsStronglyConnectedTest {

	@Test
	public void Test_Edit () 
	{
		 Graph<Character> g1 = new Graph<Character>(6);
		    
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
		 
		 assertEquals(true, g1.isStronglyConnected());
		 
		 try {
			 g1.removeEdge('d', 'a');
		 } catch(Exception e) {
			 
		 }
		 
		 assertEquals(false, g1.isStronglyConnected());
		 
		 try {
			 g1.addNode('e');
			 g1.addNode('f');
			 
			 g1.addEdge('d', 'e', 2.0);
			 g1.addEdge('e', 'f', 2.0);
		 } catch(Exception e) {
			 
		 }
		 
		 assertEquals(false, g1.isStronglyConnected());
		 
		 try {
			 g1.addEdge('f', 'a', 2.0);
		 } catch(Exception e) {
			 
		 }
		 
		 assertEquals(true, g1.isStronglyConnected());
	}

}
