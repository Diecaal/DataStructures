package priorityQueue.binaryHeap;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryHeapTest {

		 
	@Test
	public void test_homework() {
		
		 Integer[] input = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		 BinaryHeap<Integer> a = new BinaryHeap<Integer>(input);
		 
		 System.out.println("---" + a.toString());
		 assertEquals(a.toString(),("[1, 2, 4, 3, 6, 5, 8, 10, 7, 9]"));
	}
	
	@Test
	public void test_filter_up() {
		
		 BinaryHeap<Integer> a = new BinaryHeap<Integer>();
		 a.add(10);
		 a.add(9);
		 a.add(8);

		 assertEquals(a.toString(),("[8, 10, 9]"));
		 a.add(7);
		 assertEquals(a.toString(),("[7, 8, 9, 10]"));
		 a.add(6);
		 assertEquals(a.toString(),("[6, 7, 9, 10, 8]"));
		 a.add(5);
		 assertEquals(a.toString(),("[5, 7, 6, 10, 8, 9]"));
		 a.add(4);
		 assertEquals(a.toString(),("[4, 7, 5, 10, 8, 9, 6]"));
		 a.add(3);
		 assertEquals(a.toString(),("[3, 4, 5, 7, 8, 9, 6, 10]"));
		 a.add(2);
		 assertEquals(a.toString(),("[2, 3, 5, 4, 8, 9, 6, 10, 7]"));
		 a.add(1);
		 assertEquals(a.toString(),("[1, 2, 5, 4, 3, 9, 6, 10, 7, 8]"));
		 
		 BinaryHeap<Character> b = new BinaryHeap<Character>();
		 
		 b.add('f');
		 b.add('g');
		 b.add('a');
		 b.add('z');
		 b.add('d');
		 
		 System.out.println (b.toString());
		 assertEquals(b.toString(), "[a, d, f, z, g]");
	}
	
	@Test
	public void test_getMin () {
				
		 String[] input = {"Z", "X", "R", "P", "O", "G", "E", "D", "B", "A"};
		 BinaryHeap<String> heap = new BinaryHeap<String>(input);

		 String result = "";

		 while (!heap.isEmpty()) 
		 {
			 String x = heap.getMin();
			 result += x;
		 }
		 		 
		 assertEquals(result, "ABDEGOPRXZ"); 
	}
	
	@Test
	public void test_getMinB() {
				
		 BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
		 heap.add(200);
		 heap.add(105);
		 heap.add(1);
		 
		 String result = "";

		 // print elements in sorted order
		 while (!heap.isEmpty()) 
		 {
			 int x = heap.getMin();
			 result += x +"-";
		 }
		 
		 assertEquals(result, "1-105-200-"); 
	}
	
	@Test
    public void test()
    {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
        heap.add(9);
        heap.add(8);
        heap.add(7);
        heap.add(6);
        heap.add(5);
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
       
        System.out.println (heap.toString());
        assertEquals(heap.toString(), "[1, 3, 2, 4, 7, 8, 5, 9, 6]");
      
        assertEquals (1, (int) heap.getMin());
        System.out.println (heap.toString());
        
        assertEquals(heap.toString(), "[2, 3, 5, 4, 7, 8, 6, 9]");
    }

}
