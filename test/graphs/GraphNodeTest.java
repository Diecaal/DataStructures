package graphs;

import static org.junit.Assert.*;

import org.junit.Test;


public class GraphNodeTest {

	@Test
	public void test() {
		
		GraphNode<Integer> numbers = new GraphNode<Integer> (4); 
		System.out.println (numbers.getElement());
		assertEquals (4, (int) numbers.getElement());
		assertEquals (false,  numbers.isVisited());
		numbers.setVisited(true);
		assertEquals (true,  numbers.isVisited());
		assertEquals ("GN(N:4/V:true)", numbers.toString());
			
		GraphNode<String> myString = new GraphNode<String> ("hello");
		assertEquals ("hello", myString.getElement());
		assertEquals (false,  myString.isVisited());
		assertEquals ("GN(N:hello/V:false)", myString.toString());
		
		GraphNode<Character> myChar = new GraphNode<Character> ('a');
		assertEquals ('a', (char) myChar.getElement());
		assertEquals ("GN(N:a/V:false)", myChar.toString());
		myChar.setElement('b');
		myChar.print();
		assertEquals ("GN(N:b/V:false)", myChar.toString());
		myChar.setVisited(true);
		assertEquals ("GN(N:b/V:true)", myChar.toString());
	}

}