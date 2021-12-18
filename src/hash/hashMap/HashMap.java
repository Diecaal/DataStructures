package hash.hashMap;

import java.util.ArrayList;
import java.util.List;

public class HashMap<K, V> {
		
	private List<HashMapNode<K, V>> associativeArray;
	// Represents the maximum size of the associative array
	private int B;
	private int redispersionType;
	private double maxLF;
	private double minLF = NOT_ASSIGNED;
	private int R;
	
	// Stores the number of valid nodes in the associative array (nodes VALID storing an element)
	private int validHashNodes;
	
	public static final int LINEAR_PROBING = 0;
	public static final int QUADRATIC_PROBING = 1;
	public static final int DOUBLE_HASHING = 2;
	
	private static final int NOT_ASSIGNED = -1;
	
	public HashMap(int B, int redispersionType, double maxLF) {
		this.associativeArray = new ArrayList<HashMapNode<K, V>>();
		this.B = B;
		for(int i = 0; i < B; i++) {
			associativeArray.add(new HashMapNode<K, V>());
		}
		this.redispersionType = redispersionType;
		this.maxLF = maxLF;
		this.R = getPrevPrimeNumber(B);
		this.validHashNodes = 0;
	}
	
	public HashMap(int B, int redispersionType, double maxLF, double minLF) {
		this(B, redispersionType, maxLF);
		this.minLF = minLF;
	}
	
	/**
	 * 
	 * @param element
	 */
	public void put(K key, V value) {
		int currentAttempt = 0;
		int f = f(key, currentAttempt);
		// Change the value of f until we find a free node (NOT valid one)
		while(associativeArray.get(f).isValid()) {
			currentAttempt++;
			f = f(key, currentAttempt);
		}
		associativeArray.set(f, new HashMapNode<K, V>(key, value));
		validHashNodes++;
		
		// Dynamic resize if LF > maxLF
		if(getLF() > maxLF) {
			int newSize = getNextPrimeNumber( B * 2 );
			dynamicResize(newSize);
		}
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	public boolean search(K key) {
		boolean elementPresent = false;
		int currentAttempt = 0;
		int f = f(key, currentAttempt);
		
		while(!associativeArray.get(f).isEmpty() && !elementPresent) {
			// Only VALID nodes are taken into account
			if(associativeArray.get(f).isValid()) {
				if(associativeArray.get(f).getKey().equals(key))
					elementPresent = true;	
			}
			currentAttempt++;
			f = f(key, currentAttempt);
		}
		
		return elementPresent;
	}
	
	/**
	 * 
	 * @param element
	 */
	public void remove(K key) {
		int currentAttempt = 0;
		int f = f(key, currentAttempt);
		
		while(associativeArray.get(f).isValid()) {
			if(associativeArray.get(f).getKey().equals(key)) {
				associativeArray.get(f).setDeleted();
				validHashNodes--;
			}
			currentAttempt++;
			f = f(key, currentAttempt);
		}
		
		if(minLF != NOT_ASSIGNED && getLF() < minLF) {
			int newSize = getPrevPrimeNumber( B / 2);
			dynamicResize(newSize); 
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public double getLF() {
		return (double) validHashNodes / B;
	}
	
	/**
	 * 
	 * @param element
	 * @param attempt
	 * @return
	 */
	protected int f(K element, final int attempt) {
		int newHashCode = 0;
		
		if(redispersionType == LINEAR_PROBING)
			newHashCode = Math.abs(element.hashCode() + attempt) % B;
		else if(redispersionType == QUADRATIC_PROBING)
			newHashCode = Math.abs( element.hashCode() + ( attempt * attempt ) ) % B;
		else if(redispersionType == DOUBLE_HASHING)
			newHashCode = Math.abs( element.hashCode() + ( attempt * (R - element.hashCode() % R) ) ) % B;
		
		return newHashCode;
	}

	/**
	 * 
	 * @param newSize
	 */
	private void dynamicResize(int newSize) {
		// Keep an instance of old table for passing elements to new table
		List<HashMapNode<K, V>> oldTable = associativeArray;
		// Assign all new variables for the table (associativeArray, B, recompute R and current valid hash nodes)
		this.B = newSize;
		this.R = getPrevPrimeNumber(B);
		this.associativeArray = new ArrayList<HashMapNode<K, V>>();
		for(int i = 0; i < B; i++) {
			associativeArray.add(new HashMapNode<K, V>());
		}
		this.validHashNodes = 0;
		// Save the relevant elements from old table to new one
		for(int i = 0; i < oldTable.size(); i++) {
			if(oldTable.get(i).isValid()) {
				put(oldTable.get(i).getKey(), oldTable.get(i).getValue());
			}
		}
	}
	
	/* PRIME NUMBERS METHODS */
	
	public static boolean isPrime(int number) {
		if(number < 1)
			return false;
		
		if(number == 1)
			return true;
		
        for (int i = 2; i < number; i++)
            if (number % i == 0)
                return false;
  
        return true;
	}
	
	public static int getNextPrimeNumber(int number) {
        if(number < 1)
        	return 1;
        
        if(number < 2)
        	return 2;
 
        int prime = number;
        boolean found = false;
     
        while (!found)
        {
            prime++;
            if (isPrime(prime))
                found = true;
        } 
        return prime;
    }
	
	public static int getPrevPrimeNumber(int number) {
        if(number == 2)
        	return 1;
 
        int prime = number;
        boolean found = false;
     
        while (!found)
        {
            prime--;
            if (isPrime(prime))
                found = true;
        } 
        return prime;
    }
	
	@Override
	public String toString() {
		String str = "";
		for(int i = 0; i < B; i++) {
			str += String.format("[%s] (%s) = %s : %s - ", i, 
					associativeArray.get(i).getStatus(), 
					associativeArray.get(i).getKey(),
					associativeArray.get(i).getValue());
		}
		return str;
	}	
}
