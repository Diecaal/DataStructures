package hash.hashTable;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class HashTableTest {

	@Test
    public void testTable() throws Exception
    {
        // EJEMPLOS DE CLASE
        HashTable<Integer> h = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 0.5);
        
//        h.print();
        assertEquals (h.toString(),"[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (0) = null - ");
        System.out.println();
    }
	
	@Test
	public void testFInteger() throws Exception
	{
		// Example
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals(2, a.f(7, 0));
		assertEquals(3, a.f(7, 1));
		assertEquals(4, a.f(7, 2));
		assertEquals(0, a.f(7, 3));
		
		// Example
		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 0.5);
		assertEquals(2, b.f(7, 0));
		assertEquals(3, b.f(7, 1));
		assertEquals(1, b.f(7, 2));
		assertEquals(1, b.f(7, 3));
		
		// Example
		HashTable<Integer> c = new HashTable<Integer>(5, HashTable.DOUBLE_HASHING, 0.5);
		assertEquals(2, c.f(7, 0));
		assertEquals(4, c.f(7, 1));
		assertEquals(1, c.f(7, 2));
		assertEquals(3, c.f(7, 3));
	}
	
	
	@Test
	public void testFCharacter() throws Exception
	{
		// Example
		HashTable<Character> a = new HashTable<Character>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals(0, a.f('A', 0));
		assertEquals(1, a.f('A', 1));
		assertEquals(2, a.f('A', 2));
		assertEquals(3, a.f('A', 3));
		
		// Example
		HashTable<Character> b = new HashTable<Character>(5, HashTable.QUADRATIC_PROBING, 0.5);
		assertEquals(0, b.f('A', 0));
		assertEquals(1, b.f('A', 1));
		assertEquals(4, b.f('A', 2));
		assertEquals(4, b.f('A', 3));
		
		// Example
		HashTable<Character> c = new HashTable<Character>(5, HashTable.DOUBLE_HASHING, 0.5);
		assertEquals(0, c.f('A', 0));
		assertEquals(1, c.f('A', 1));
		assertEquals(2, c.f('A', 2));
		assertEquals(3, c.f('A', 3));
	}
	
	@Test
	public void testPrimeNumber() throws Exception
	{
		// Example
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);
		assertEquals(true, HashTable.isPrime(1));
		assertEquals(true, HashTable.isPrime(2));
		assertEquals(true, HashTable.isPrime(3));
		assertEquals(false, HashTable.isPrime(4));
		assertEquals(true, HashTable.isPrime(5));
		assertEquals(false, HashTable.isPrime(6));
		assertEquals(true, HashTable.isPrime(7));
		assertEquals(false, HashTable.isPrime(8));
		assertEquals(false, HashTable.isPrime(9));
		assertEquals(false, HashTable.isPrime(10));
		assertEquals(true, HashTable.isPrime(11));
		assertEquals(false, HashTable.isPrime(12));
		assertEquals(true, HashTable.isPrime(13));
		assertEquals(false, HashTable.isPrime(14));
		assertEquals(false, HashTable.isPrime(15));
		assertEquals(false, HashTable.isPrime(16));
		assertEquals(true, HashTable.isPrime(17));
		
		assertEquals(2, HashTable.getNextPrimeNumber(1));
		assertEquals(3, HashTable.getNextPrimeNumber(2));
		assertEquals(5, HashTable.getNextPrimeNumber(3));
		assertEquals(5, HashTable.getNextPrimeNumber(4));
		assertEquals(7, HashTable.getNextPrimeNumber(5));
		assertEquals(7, HashTable.getNextPrimeNumber(6));
		assertEquals(11, HashTable.getNextPrimeNumber(7));
		assertEquals(11, HashTable.getNextPrimeNumber(8));
		assertEquals(11, HashTable.getNextPrimeNumber(9));
		assertEquals(11, HashTable.getNextPrimeNumber(10));
		assertEquals(13, HashTable.getNextPrimeNumber(11));
		
		assertEquals(13, HashTable.getPrevPrimeNumber(15));
		assertEquals(13, HashTable.getPrevPrimeNumber(14));
		assertEquals(11, HashTable.getPrevPrimeNumber(13));
		assertEquals(11, HashTable.getPrevPrimeNumber(12));
		assertEquals(7, HashTable.getPrevPrimeNumber(11));
		assertEquals(7, HashTable.getPrevPrimeNumber(10));
		assertEquals(7, HashTable.getPrevPrimeNumber(9));
		assertEquals(7, HashTable.getPrevPrimeNumber(8));
		assertEquals(5, HashTable.getPrevPrimeNumber(7));
		assertEquals(5, HashTable.getPrevPrimeNumber(6));
		assertEquals(3, HashTable.getPrevPrimeNumber(5));
		assertEquals(3, HashTable.getPrevPrimeNumber(4));
		assertEquals(2, HashTable.getPrevPrimeNumber(3));
	}
	
	@Test
	public void testResizing() throws Exception
	{
		// Example
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 0.5);
		a.add(4);
		assertEquals (0.2, a.getLF(), 0.1);
		a.add(13);
		assertEquals (0.4, a.getLF(), 0.1);
		assertEquals ("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
        		
		a.add(24);
		assertEquals (0.27, a.getLF(), 0.1);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ", a.toString());

		a.add(3);
		
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - [5] (1) = 3 - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ", a.toString());
	}
		
	
	@Test
	public void testAddInteger() throws Exception
	{
		// Example
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);
		a.add(4);
		assertEquals (0.2, a.getLF(), 0.1);
		a.add(13);
		assertEquals (0.4, a.getLF(), 0.1);
		a.add(24);
		assertEquals (0.6, a.getLF(), 0.1);
		a.add(3);
		assertEquals (0.8, a.getLF(), 0.1);
		
		assertEquals("[0] (1) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		assertEquals(true, a.search(3));
		assertEquals(false, a.search(12));
		
		a.remove(24);
		assertEquals("[0] (2) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		
		assertEquals(true, a.search(3));
		a.add(15);
		assertEquals(true, a.search(3));
		assertEquals("[0] (1) = 15 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		
	
		
		// Example
		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 1.0);
		b.add(4);
		b.add(13);
		b.add(24);
		b.add(3);
		
		assertEquals("[0] (1) = 24 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
		assertEquals(true, b.search(3));
		assertEquals(false, b.search(12));
		
		b.remove(24);
		assertEquals("[0] (2) = 24 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
		
		assertEquals(true, b.search(3));
		b.add(15);
		assertEquals(true, b.search(3));
		assertEquals("[0] (1) = 15 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
		
		
		// Example
		HashTable<Integer> c = new HashTable<Integer>(5, HashTable.DOUBLE_HASHING, 1.0);
		c.add(4);
		c.add(13);
		c.add(24);
		c.add(3);
		
		assertEquals("[0] (0) = null - [1] (1) = 3 - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
		assertEquals(true, c.search(3));
		assertEquals(false, c.search(12));
		
		c.remove(24);
		assertEquals("[0] (0) = null - [1] (1) = 3 - [2] (2) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
		
		assertEquals(true, c.search(3));
		c.add(15);
		assertEquals(true, c.search(3));
		assertEquals("[0] (1) = 15 - [1] (1) = 3 - [2] (2) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
				
	}
	
	
    

    
   

}
