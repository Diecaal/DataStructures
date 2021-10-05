package graphs;
import static org.junit.Assert.*;

import org.junit.Test;


public class L5_Floyd_EvalTest {

	@Test
	public void Test_Edit () throws Exception 
	{
		 Graph<Character> g1 = new Graph<Character>(3);
		    
		 System.out.println ("TEST EDIT BEGINS ***");
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
			
		 assertArrayEquals (new boolean[][]{{false,false,false}, {false,false,false}, {false,false,false}}, g1.getEdges());
		 assertArrayEquals (new double[][]{{0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}}, g1.getWeight());
		 
		 // Testing edges
		 try
		 {
			 g1.existsEdge('b', 'd');
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
		 
		 assertArrayEquals (new boolean[][]{{false,false,false}, {false,false,false}, {false,false,false}}, g1.getEdges());
		 assertArrayEquals (new double[][]{{0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}}, g1.getWeight());
		 
		 try
		 {
			 g1.addEdge ('b', 'c', 5.0);
			 assertEquals (true, g1.existsEdge('b', 'c'));
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		 
		 assertArrayEquals (new boolean[][]{{false,false,false}, {false,false,true}, {false,false,false}}, g1.getEdges());
		 assertArrayEquals (new double[][]{{0.0, 0.0, 0.0}, {0.0, 0.0, 5.0}, {0.0, 0.0, 0.0}}, g1.getWeight());
	}
	
	@Test
	public void Test_Floyd_A() throws Exception {
		 Graph<String> g = new Graph<String>(6);
		    
		 System.out.println ("TEST FLOYD A BEGINS ***");
		 assertEquals(0, g.getSize());
		    
		 try
		 {
			 g.addNode("V1");
			 g.addNode("V2");
			 g.addNode("V3");
			 g.addNode("V4");
			 g.addNode("V5");
			 g.addNode("V6");
		 }
		 catch (Exception e)
		 {
			 System.out.println ("No repeated nodes are allowed" + e);
		 }
		    
		 assertEquals(6, g.getSize());
		 assertEquals(0,  g.getNode("V1"));
		 assertEquals(1,  g.getNode("V2"));
		 assertEquals(2,  g.getNode("V3"));
		 assertEquals(3,  g.getNode("V4"));
		 assertEquals(4,  g.getNode("V5"));
		 assertEquals(5,  g.getNode("V6"));
		 assertArrayEquals (new boolean[][]{{false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, g.getEdges());
		 assertArrayEquals (new double[][]{{0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}}, g.getWeight());
	
		 try
		 {
			 g.addEdge ("V1", "V2", 3.0);
			 g.addEdge ("V1", "V3", 4.0);
			 g.addEdge ("V1", "V5", 8.0);
			 
			 g.addEdge ("V2", "V5", 5.0);
						 
			 g.addEdge ("V3", "V5", 3.0);
			 
			 g.addEdge ("V5", "V6", 3.0);
			 g.addEdge ("V5", "V4", 7.0);
			 
			 g.addEdge ("V6", "V4", 2.0);
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Starting or arrival node does not exists" + e);
		 }
		
		 assertArrayEquals (new boolean[][]{{false, true, true, false, true, false}, {false, false, false, false, true, false}, {false, false, false, false, true, false}, {false, false, false, false, false, false}, {false, false, false, true, false, true}, {false, false, false, true, false, false}}, g.getEdges());
		 assertArrayEquals (new double[][]{{0.0, 3.0, 4.0, 0.0, 8.0, 0.0}, {0.0, 0.0, 0.0, 0.0, 5.0, 0.0}, {0.0, 0.0, 0.0, 0.0, 3.0, 0.0}, {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 7.0, 0.0, 3.0}, {0.0, 0.0, 0.0, 2.0, 0.0, 0.0}}, g.getWeight());
		 assertEquals ("V1-V2-V5-V4-V6-V3-", g.traverseGraphDF("V1"));
		 //g.print();
		 
		 g.floyd(g.getSize());

		 assertArrayEquals (new int[][]{
				 {-1, -1, -1,  5,  2,  4}, 
				 {-1, -1, -1,  5, -1,  4},
				 {-1, -1, -1,  5, -1,  4}, 
				 {-1, -1, -1, -1, -1, -1}, 
				 {-1, -1, -1,  5, -1, -1}, 
				 {-1, -1, -1, -1, -1, -1}}, g.getP());
		 assertArrayEquals (new double[][]{
				 {00.0, 03.0, 04.0, 12.0, 07.0, 10.0}, 
				 {Graph.INFINITE, 00.0, 			Graph.INFINITE, 10.0, 05.0, 			08.0}, 
				 {Graph.INFINITE, Graph.INFINITE, 	00.0, 			08.0, 03.0, 			06.0}, 
				 {Graph.INFINITE, Graph.INFINITE, 	Graph.INFINITE, 00.0, Graph.INFINITE, 	Graph.INFINITE}, 
				 {Graph.INFINITE, Graph.INFINITE, 	Graph.INFINITE, 05.0, 00.0, 			03.0}, 
				 {Graph.INFINITE, Graph.INFINITE, 	Graph.INFINITE, 02.0, Graph.INFINITE, 	00.0}}, g.getA());
				 
		 
		 try
		 {
			 assertEquals ("V1V3V5V6", "V1" + g.printFloydPath ("V1", "V6") + "V6");
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Starting or arrival node does not exists" + e);
		 } 
	}
	
	
	@Test
	public void Test_Floyd_B () throws Exception 
	{
		 Graph<String> g = new Graph<String>(6);
		    
		 System.out.println ("TEST FLOYD B BEGINS ***");
		 assertEquals(0, g.getSize());
		    
		 try
		 {
			 g.addNode("Spain");
			 g.addNode("Venezuela");
			 g.addNode("UK");
			 g.addNode("Poland");
			 g.addNode("Greece");
			 g.addNode("Japan");
		 }
		 catch (Exception e)
		 {
			 System.out.println ("No repeated nodes are allowed" + e);
		 }
		    
		 assertEquals(6, g.getSize());
		 assertEquals(0, g.getNode("Spain"));
		 assertEquals(1, g.getNode("Venezuela"));
		 assertEquals(2, g.getNode("UK"));
		 assertEquals(3, g.getNode("Poland"));
		 assertEquals(4, g.getNode("Greece"));
		 assertEquals(5, g.getNode("Japan"));
		 
		 assertArrayEquals (new boolean[][]{
			 {false, false, false, false, false, false}, 
			 {false, false, false, false, false, false},
			 {false, false, false, false, false, false},
			 {false, false, false, false, false, false},
			 {false, false, false, false, false, false},
			 {false, false, false, false, false, false }}, g.getEdges());
		 
		 assertArrayEquals (new double[][]{
			 {00.0, 00.0, 00.0, 00.0, 00.0, 00.0}, 
			 {00.0, 00.0, 00.0, 00.0, 00.0, 00.0},  
			 {00.0, 00.0, 00.0, 00.0, 00.0, 00.0}, 
			 {00.0, 00.0, 00.0, 00.0, 00.0, 00.0}, 
			 {00.0, 00.0, 00.0, 00.0, 00.0, 00.0},
			 {00.0, 00.0, 00.0, 00.0, 00.0, 00.0}}, g.getWeight());
		 
		
		 // Test nodes for nodes not found
		 assertEquals(Graph.INDEX_NOT_FOUND,  g.getNode("Ecuador"));
		 
		 
		 // No repeated nodes allowed
		 try
		 {
			 g.addNode("Venezuela");
		 }
		 catch (Exception e)
		 {
			 System.out.println ("No repeated nodes are allowed" + e);
		 }
		 
		 		
		 // Testing edges
		 try
		 {
			 g.existsEdge("Venezuela", "Ecuador");
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		 
		 try
		 {
			 assertEquals (false, g.existsEdge("Greece", "Spain"));
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		
		 try
		 {
			 g.addEdge ("Spain",     "Venezuela", 3.0);
			 g.addEdge ("Spain",     "Greece"   , 2.0);
			 g.addEdge ("Venezuela", "Poland"   , 2.0);
			 g.addEdge ("Greece",    "UK"       , 1.0);
			 g.addEdge ("UK",        "Poland"   , 4.0);
			 g.addEdge ("Poland",    "Spain"    , 1.0);
			 g.addEdge ("Poland",    "Greece"   , 3.0);
			 g.addEdge ("Poland",    "Japan"    , 1.0);
			 g.addEdge ("Japan",     "Spain"    , 1.0);
			 g.addEdge ("Japan",     "Poland"   , 2.0);
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Departure or arrival node does not exist" + e);
		 }
		 
		 assertArrayEquals (new boolean[][]{
			 {false, true,  false, false, true,  false}, 
			 {false, false, false, true,  false, false},
			 {false, false, false, true,  false, false},
			 {true,  false, false, false, true,  true},
			 {false, false, true,  false, false,  false},
			 {true , false, false, true,  false, false}}, g.getEdges());
		 
		 assertArrayEquals (new double[][]{
			 {00.0, 03.0, 00.0, 00.0, 02.0, 00.0}, 
			 {00.0, 00.0, 00.0, 02.0, 00.0, 00.0},  
			 {00.0, 00.0, 00.0, 04.0, 00.0, 00.0}, 
			 {01.0, 00.0, 00.0, 00.0, 03.0, 01.0}, 
			 {00.0, 00.0, 01.0, 00.0, 00.0, 00.0},
			 {01.0, 00.0, 00.0, 02.0, 00.0, 00.0}}, g.getWeight());
 
		 
		 g.floyd(g.getSize());
		 		 
		 assertArrayEquals (new double[][]{
			 {00.0, 03.0, 03.0, 05.0, 02.0, 06.0}, 
			 {03.0, 00.0, 06.0, 02.0, 05.0, 03.0},  
			 {05.0, 08.0, 00.0, 04.0, 07.0, 05.0}, 
			 {01.0, 04.0, 04.0, 00.0, 03.0, 01.0}, 
			 {06.0, 09.0, 01.0, 05.0, 00.0, 06.0},
			 {01.0, 04.0, 04.0, 02.0, 03.0, 00.0}}, g.getA());
		 
		 assertArrayEquals (new int[][]{
			 {-1, -1,  4,  1, -1,  3}, 
			 { 3, -1,  4, -1,  3,  3},
			 { 3,  3, -1, -1,  3,  3}, 
			 {-1,  0,  4, -1, -1, -1}, 
			 { 3,  3, -1,  2, -1,  3}, 
			 {-1,  0,  4, -1,  0, -1}}, g.getP());
		 
		
		 try
		 {
			 assertEquals ("SpainVenezuelaPolandJapan", "Spain" + g.printFloydPath ("Spain", "Japan") + "Japan");
			 assertEquals ("SpainGreeceUK", "Spain" + g.printFloydPath ("Spain", "UK") + "UK");
			 assertEquals ("SpainVenezuelaPoland", "Spain" + g.printFloydPath ("Spain", "Poland") + "Poland");
			 assertEquals ("PolandSpainVenezuela", "Poland" + g.printFloydPath ("Poland", "Venezuela") + "Venezuela");

		 }
		 catch (Exception e)
		 {
			 System.out.println ("Starting or arrival node does not exists" + e);
		 } 
		 
		 try
		 {
			 g.removeNode("Greece");
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Node does not exists" + e);
		 } 
		 
		 
		 assertArrayEquals (new boolean[][]{
			 {false, true,  false, false, false,  false}, 
			 {false, false, false, true,  false, false},
			 {false, false, false, true,  false, false},
			 {true,  false, false, false, true,  true},
			 {true , false, false, true,  false, false},
			 {true , false, false, true,  false, false}}, g.getEdges());
		 
		 assertArrayEquals (new double[][]{
			 {00.0, 03.0, 00.0, 00.0, 00.0, 00.0}, 
			 {00.0, 00.0, 00.0, 02.0, 00.0, 00.0},  
			 {00.0, 00.0, 00.0, 04.0, 00.0, 00.0}, 
			 {01.0, 00.0, 00.0, 00.0, 01.0, 01.0}, 
			 {01.0, 00.0, 00.0, 02.0, 00.0, 00.0},
			 {01.0, 00.0, 00.0, 02.0, 00.0, 00.0}}, g.getWeight());
		 		 
		 g.floyd(g.getSize());

		 
		 assertArrayEquals (new double[][]{
			 {00.0, 03.0, Graph.INFINITE, 	05.0, 06.0, 06.0}, 
			 {03.0, 00.0, Graph.INFINITE, 	02.0, 03.0, 03.0},  
			 {05.0, 08.0, 00.0, 			04.0, 05.0, 05.0}, 
			 {01.0, 04.0, Graph.INFINITE, 	00.0, 01.0, 01.0}, 
			 {01.0, 04.0, Graph.INFINITE, 	02.0, 00.0, 06.0},
			 {01.0, 04.0, 04.0, 			02.0, 03.0, 00.0}}, g.getA());
		 
		 assertArrayEquals (new int[][]{
			 {-1, -1, -1,  1,  3,  3}, 
			 { 3, -1, -1, -1,  3,  3},
			 { 3,  3, -1, -1,  3,  3}, 
			 {-1,  0, -1, -1, -1, -1}, 
			 {-1,  0, -1, -1, -1,  3}, 
			 {-1,  0,  4, -1,  0, -1}}, g.getP());
		 
		 try
		 {
			 assertEquals ("SpainVenezuelaPolandJapan", "Spain" + g.printFloydPath ("Spain", "Japan") + "Japan");
			 assertEquals ("SpainUK", "Spain" + g.printFloydPath ("Spain", "UK") + "UK");
			 assertEquals ("SpainVenezuelaPoland", "Spain" + g.printFloydPath ("Spain", "Poland") + "Poland");
			 assertEquals ("PolandSpainVenezuela", "Poland" + g.printFloydPath ("Poland", "Venezuela") + "Venezuela");
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Starting or arrival node does not exists" + e);
		 } 
		 
		 
		 
		 try
		 {
			 g.removeEdge("Poland", "Japan");
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Starting or arrival node does not exists" + e);
		 } 
		 
		 
		 assertArrayEquals (new boolean[][]{
			 {false, true,  false, false, false,  false}, 
			 {false, false, false, true,  false, false},
			 {false, false, false, true,  false, false},
			 {true,  false, false, false, false,  true},
			 {true , false, false, true,  false, false},
			 {true , false, false, true,  false, false}}, g.getEdges());
		 
		 assertArrayEquals (new double[][]{
			 {00.0, 03.0, 00.0, 00.0, 00.0, 00.0}, 
			 {00.0, 00.0, 00.0, 02.0, 00.0, 00.0},  
			 {00.0, 00.0, 00.0, 04.0, 00.0, 00.0}, 
			 {01.0, 00.0, 00.0, 00.0, 00.0, 01.0}, 
			 {01.0, 00.0, 00.0, 02.0, 00.0, 00.0},
			 {01.0, 00.0, 00.0, 02.0, 00.0, 00.0}}, g.getWeight());
		 		 
		 g.floyd(g.getSize());
	
		 assertArrayEquals (new double[][]{
			 {00.0, 03.0, Graph.INFINITE, 	05.0, Graph.INFINITE, 	06.0}, 
			 {03.0, 00.0, Graph.INFINITE, 	02.0, Graph.INFINITE, 	03.0},  
			 {05.0, 08.0, 00.0, 			04.0, Graph.INFINITE, 	05.0}, 
			 {01.0, 04.0, Graph.INFINITE, 	00.0, Graph.INFINITE, 	01.0}, 
			 {01.0, 04.0, Graph.INFINITE,	02.0, 00.0, 			06.0},
			 {01.0, 04.0, 04.0, 			02.0, 03.0, 			00.0}}, g.getA());
		 
		 assertArrayEquals (new int[][]{
			 {-1, -1, -1,  1, -1,  3}, 
			 { 3, -1, -1, -1, -1,  3},
			 { 3,  3, -1, -1, -1,  3}, 
			 {-1,  0, -1, -1, -1, -1}, 
			 {-1,  0, -1, -1, -1,  3}, 
			 {-1,  0,  4, -1,  0, -1}}, g.getP());
		 
		 try
		 {
			 assertEquals ("SpainJapan", "Spain" + g.printFloydPath ("Spain", "Japan") + "Japan");
			 assertEquals ("SpainUK", "Spain" + g.printFloydPath ("Spain", "UK") + "UK");
			 assertEquals ("SpainVenezuelaPoland", "Spain" + g.printFloydPath ("Spain", "Poland") + "Poland");
			 assertEquals ("PolandSpainVenezuela", "Poland" + g.printFloydPath ("Poland", "Venezuela") + "Venezuela");
		 }
		 catch (Exception e)
		 {
			 System.out.println ("Starting or arrival node does not exists" + e);
		 } 
	}	
}
