package graphs;

import static org.junit.Assert.*;

import org.junit.Test;

public class FloydTest {

	/**
	 * Exercise 1 from ED lectures PDF
	 */
	@Test
	public void test() {
		Graph<Character> g1 = new Graph<Character>(6);
	    
		 System.out.println ("TEST EDIT A BEGINS ***");
		 assertEquals(0, g1.getSize());
		    
		 try
		 {
			 g1.addNode('1');
			 g1.addNode('2');
			 g1.addNode('3');
			 g1.addNode('4');
			 g1.addNode('5');
			 g1.addNode('6');
		 }
		 catch (Exception e)
		 {
			 System.out.println ("No repeated nodes are allowed" + e);
		 }
		    
		 assertEquals(6, g1.getSize());
		 
		 
		 
		 try
		 {
			 g1.addEdge ('1', '2', 3.0);
			 g1.addEdge ('1', '3', 4.0);
			 g1.addEdge ('1', '5', 8.0);
			 g1.addEdge ('2', '5', 5.0);
			 g1.addEdge ('3', '5', 3.0);
			 g1.addEdge ('5', '4', 7.0);
			 g1.addEdge ('5', '6', 3.0);
			 g1.addEdge ('6', '4', 2.0);
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		 
		 // FLOYD
		 g1.print();
		 g1.floyd(g1.getSize());
		 
		 assertArrayEquals (new int[][]{
				 { -1, -1, -1,  5,	2,	4}, 
				 { -1, -1, -1,  5,	-1,	4},
				 { -1, -1, -1,  5,	-1,	4}, 
				 { -1, -1, -1, -1,	-1,	-1},
				 { -1, -1, -1,  5,  -1, -1},
				 { -1, -1, -1, -1,	-1,	-1}}, g1.getP());
		 assertArrayEquals (new double[][]{
			 { 00.0, 03.0, 04.0,  12.0,	7.0, 10.0}, 
			 { Graph.INFINITE, 00.0, Graph.INFINITE,  10.0,	05.0,	08.0},
			 { Graph.INFINITE, Graph.INFINITE, 00.0, 08.0,	03.0,	06.0}, 
			 { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 00.0,	Graph.INFINITE,	Graph.INFINITE},
			 { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE,  05.0,  00.0, 03.0},
			 { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 02.0,	Graph.INFINITE,	00.0}}, g1.getA());
	}

}
