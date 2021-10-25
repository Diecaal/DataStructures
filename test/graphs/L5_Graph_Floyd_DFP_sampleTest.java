package graphs;

import static org.junit.Assert.*;

import org.junit.Test;

public class L5_Graph_Floyd_DFP_sampleTest {

	@Test
	public void Test_Floyd_DFP () throws Exception 
	{
		 Graph<Character> g1 = new Graph<Character>(4);
		    
		 System.out.println ("TEST EDIT A BEGINS ***");
		 assertEquals(0, g1.getSize());
		    
		 try
		 {
			 g1.addNode('a');
			 g1.addNode('b');
			 g1.addNode('c');
			 g1.addNode('d');
		 }
		 catch (Exception e)
		 {
			 System.out.println ("No repeated nodes are allowed" + e);
		 }
		    
		 assertEquals(4, g1.getSize());
		 
		 
		 
		 try
		 {
			 g1.addEdge ('a', 'b', 3.0);
			 g1.addEdge ('b', 'c', 1.0);
			 g1.addEdge ('b', 'd', 3.0);
			 g1.addEdge ('c', 'd', 1.0);
			 g1.addEdge ('d', 'a', 3.0);
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		 
		 assertArrayEquals (new boolean[][]{
			 {false,	true,	false,	false}, 
			 {false, 	false,	true,	true}, 
			 {false, 	false,	false,	true},
			 {true,		false,	false,	false}}, g1.getEdges());
		 
		 assertArrayEquals (new double[][]{
			 {0.0, 3.0, 0.0, 0.0}, 
			 {0.0, 0.0, 1.0, 3.0}, 
			 {0.0, 0.0, 0.0, 1.0},
			 {3.0, 0.0, 0.0, 0.0}}, g1.getWeight());
		 
		 
		 // TRAVERSE
		 assertEquals("a-b-c-d-", g1.traverseGraphDF('a'));
		 assertEquals("b-c-d-a-", g1.traverseGraphDF('b'));
		 assertEquals("c-d-a-b-", g1.traverseGraphDF('c'));
		 assertEquals("d-a-b-c-", g1.traverseGraphDF('d'));
		 
//		 
//		 // FLOYD
		 
		 g1.floyd(g1.getSize());

		 assertArrayEquals (new int[][]{
				 {-1, -1,  1,  2}, 
				 { 3, -1, -1,  2},
				 { 3,  3, -1, -1}, 
				 {-1,  0,  1, -1}}, g1.getP());
		 assertArrayEquals (new double[][]{
				 {00.0, 			03.0, 			04.0, 			05.0}, 
				 {05.0, 			00.0, 			01.0, 			02.0}, 
				 {04.0, 			07.0, 			00.0, 			01.0}, 
				 {03.0,			 	06.0, 			07.0, 			00.0}}, g1.getA());
				 
		 
		 // add edge a->c (2)
		 
		 try
		 {
			 g1.addEdge ('c', 'a', 2.0);
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		 
		 assertArrayEquals (new boolean[][]{
			 {false,	true,	false,	false}, 
			 {false, 	false,	true,	true}, 
			 {true, 	false,	false,	true},
			 {true,		false,	false,	false}}, g1.getEdges());
		 
		 assertArrayEquals (new double[][]{
			 {0.0, 3.0, 0.0, 0.0}, 
			 {0.0, 0.0, 1.0, 3.0}, 
			 {2.0, 0.0, 0.0, 1.0},
			 {3.0, 0.0, 0.0, 0.0}}, g1.getWeight());
		 
		 
		 // TRAVERSE
		 assertEquals("a-b-c-d-", g1.traverseGraphDF('a'));
		 assertEquals("b-c-a-d-", g1.traverseGraphDF('b'));
		 assertEquals("c-a-b-d-", g1.traverseGraphDF('c'));
		 assertEquals("d-a-b-c-", g1.traverseGraphDF('d'));
		 
		 
		 // FLOYD
		 
		 g1.floyd(g1.getSize());

		 assertArrayEquals (new int[][]{
				 { -1, -1,  1,  2}, 
				 {  2, -1, -1,  2},
				 { -1,  0, -1, -1}, 
				 { -1,  0,  1, -1}}, g1.getP());
		 assertArrayEquals (new double[][]{
				 {00.0, 			03.0, 			04.0, 			05.0}, 
				 {03.0, 			00.0, 			01.0, 			02.0}, 
				 {02.0, 			05.0, 			00.0, 			01.0}, 
				 {03.0,			 	06.0, 			07.0, 			00.0}}, g1.getA());
		 
		 
		 
		 // remove b
		 try
		 {
			 g1.removeNode ('b');
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		 
		 assertArrayEquals (new boolean[][]{
			 {false,	false,	false,	false}, 
			 {true, 	false,	false,	true}, 
			 {true, 	true,	false,	true},
			 {true,		false,	false,	false}}, g1.getEdges());
		 
		 assertArrayEquals (new double[][]{
			 {0.0, 0.0, 0.0, 0.0}, 
			 {3.0, 0.0, 0.0, 3.0}, 
			 {2.0, 1.0, 0.0, 1.0},
			 {3.0, 0.0, 0.0, 0.0}}, g1.getWeight());
		 
		 
		 // TRAVERSE
		 assertEquals("a-", g1.traverseGraphDF('a'));
		 System.out.println();
		 assertEquals("c-a-d-", g1.traverseGraphDF('c'));
		 assertEquals("d-a-", g1.traverseGraphDF('d'));
		 
		 
		 // FLOYD
		 g1.print();
		 g1.floyd(g1.getSize());

		 assertArrayEquals (new int[][]{
				 { -1, -1, -1,  2}, 
				 { -1, -1, -1,  2},
				 { -1, -1, -1, -1}, 
				 { -1,  0,  1, -1}}, g1.getP());
		 assertArrayEquals (new double[][]{
				 {00.0, 			Graph.INFINITE,	Graph.INFINITE, 05.0}, 
				 {03.0, 			00.0, 			Graph.INFINITE, 02.0}, 
				 {02.0, 			01.0, 			00.0, 			01.0}, 
				 {03.0,			 	06.0, 			07.0, 			00.0}}, g1.getA());
		 

		 
	}

}