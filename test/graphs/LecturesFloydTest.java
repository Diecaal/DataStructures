package graphs;

import static org.junit.Assert.*;

import org.junit.Test;

public class LecturesFloydTest {

	/**
	 * Exercise 1 from ED lectures PDF
	 */
	@Test
	public void exercise01() {
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
				 { -1, -1, -1,  5,	 2,	 4}, 
				 { -1, -1, -1,  5,  -1,	 4},
				 { -1, -1, -1,  5,	-1,  4}, 
				 { -1, -1, -1, -1,	-1,	-1},
				 { -1, -1, -1,  5,  -1, -1},
				 { -1, -1, -1, -1,	-1,	-1}}, g1.getP());
		 assertArrayEquals (new double[][]{
			 { 00.0,           03.0,           04.0,           12.0, 7.0,            10.0}, 
			 { Graph.INFINITE, 00.0, 		   Graph.INFINITE, 10.0, 05.0,	         08.0},
			 { Graph.INFINITE, Graph.INFINITE, 00.0,           08.0, 03.0,	         06.0}, 
			 { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 00.0, Graph.INFINITE, Graph.INFINITE},
			 { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 05.0, 00.0,           03.0},
			 { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 02.0, Graph.INFINITE, 00.0}}, g1.getA());
	}
	
	/**
	 * Exercise 2 from ED lectures PDF
	 */
	@Test
	public void exercise02() {
		Graph<Character> g1 = new Graph<Character>(5);
	    
		 System.out.println ("TEST EDIT A BEGINS ***");
		 assertEquals(0, g1.getSize());
		    
		 try
		 {
			 g1.addNode('1');
			 g1.addNode('2');
			 g1.addNode('3');
			 g1.addNode('4');
			 g1.addNode('5');
		 }
		 catch (Exception e)
		 {
			 System.out.println ("No repeated nodes are allowed" + e);
		 }
		    
		 assertEquals(5, g1.getSize());
		 
		 
		 
		 try
		 {
			 g1.addEdge ('1', '2', 1.0);
			 g1.addEdge ('1', '4', 3.0);
			 g1.addEdge ('1', '5', 10.0);
			 g1.addEdge ('2', '3', 5.0);
			 g1.addEdge ('3', '5', 1.0);
			 g1.addEdge ('4', '3', 2.0);
			 g1.addEdge ('4', '5', 6.0);
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		 
		 // FLOYD
		 g1.print();
		 g1.floyd(g1.getSize());
		 
		 assertArrayEquals (new int[][]{
				 { -1, -1,  3, -1,  3}, 
				 { -1, -1, -1, -1,  2},
				 { -1, -1, -1, -1, -1}, 
				 { -1, -1, -1, -1,  2},
				 { -1, -1, -1, -1, -1}}, g1.getP());
		 assertArrayEquals (new double[][]{
			 { 00.0,           01.0,           05.0,           03.0,           06.0}, 
			 { Graph.INFINITE, 00.0, 		   05.0,           Graph.INFINITE, 06.0},
			 { Graph.INFINITE, Graph.INFINITE, 00.0,           Graph.INFINITE, 01.0}, 
			 { Graph.INFINITE, Graph.INFINITE, 02.0,           00.0,           03.0},
			 { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 00.0}}, g1.getA());
	}

}
