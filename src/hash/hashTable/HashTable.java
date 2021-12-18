package hash.hashTable;

import java.util.ArrayList;
import java.util.List;

public class HashTable<T> {

	private List<HashNode<T>> associativeArray;
	// Represents the maximum size of the associative array
	private int B;
	private int redispersionType;
	private double maxLF;
	private double minLF = NOT_ASSIGNED;
	private int R;

	// Stores the number of valid nodes in the associative array (nodes VALID
	// storing an element)
	private int validHashNodes;

	/* COLLISION STRATEGIES (REDISPERSION TYPE) in our hash table */
	public static final int LINEAR_PROBING = 0;
	public static final int QUADRATIC_PROBING = 1;
	public static final int DOUBLE_HASHING = 2;

	private static final int NOT_ASSIGNED = -1;

	public HashTable(int B, int redispersionType, double maxLF) {
		this.associativeArray = new ArrayList<HashNode<T>>();
		this.B = B;
		for (int i = 0; i < B; i++) {
			associativeArray.add(new HashNode<T>());
		}
		this.redispersionType = redispersionType;
		this.maxLF = maxLF;
		this.R = getPrevPrimeNumber(B);
		this.validHashNodes = 0;
	}

	public HashTable(int B, int redispersionType, double maxLF, double minLF) {
		this(B, redispersionType, maxLF);
		this.minLF = minLF;
	}

	/**
	 * Given an element it will be added into the hash table. After the insertion it
	 * will be checked if a dynamic resize is needed if the load factor is greater
	 * than the maximum load factor.
	 * 
	 * @param element
	 */
	public void add(T element) {
		if(element == null)
			throw new IllegalArgumentException("Null elements are not allowed");
		if(search(element))
			throw new IllegalArgumentException("Element already contained in the hash table");
		
		int currentAttempt = 0;
		int f = f(element, currentAttempt);
		// Change the value of f until we find a free node (NOT valid one)
		while (associativeArray.get(f).isValid()) {
			currentAttempt++;
			f = f(element, currentAttempt);
		}
		associativeArray.set(f, new HashNode<T>(element));
		validHashNodes++;

		// Dynamic resize if LF > maxLF
		if (getLF() > maxLF) {
			int newSize = getNextPrimeNumber(B * 2);
			dynamicResize(newSize);
		}
	}

	/**
	 * Searches an element in the table through all the valid nodes. If an empty
	 * node is found, the execution will be stopped as it is not possible that
	 * further elements exists from an empty position with a given f value
	 *
	 * @param element to be searched
	 * @return True if the elements is present in a valid node in the table. False
	 *         otherwise.
	 */
	public boolean search(T element) {
		if(element == null) 
			throw new IllegalArgumentException("Search of null elements is not allowed");
		
		boolean elementPresent = false;
		int currentAttempt = 0;
		int f = f(element, currentAttempt);

		while (!associativeArray.get(f).isEmpty() && !elementPresent) {
			// Once attempts exceed B, the search is iterating over all same f values
			if (currentAttempt >= B)
				break;

			// Only VALID nodes are taken into account
			if (associativeArray.get(f).isValid()) {
				if (associativeArray.get(f).getElement().equals(element))
					elementPresent = true;
			}
			currentAttempt++;
			f = f(element, currentAttempt);
		}

		return elementPresent;
	}

	/**
	 * Given an element it removes it from the table. Setting the hash node
	 * containing it as deleted. After the removal will determine if a dynamic
	 * resize is needed if the load factor is lowet than the minimum load factor
	 * 
	 * @param element to be removed
	 */
	public void remove(T element) {
		if(!search(element))
			throw new IllegalArgumentException("Element not contained in the hash table");
		
		int currentAttempt = 0;
		int f = f(element, currentAttempt);

		while (associativeArray.get(f).isValid()) {
			// Once attempts exceed B, the search is iterating over all same f values
			if (currentAttempt >= B)
				break;
			
			if (associativeArray.get(f).getElement().equals(element)) {
				associativeArray.get(f).setDeleted();
				validHashNodes--;
			}
			currentAttempt++;
			f = f(element, currentAttempt);
		}

		if (minLF != NOT_ASSIGNED && getLF() < minLF) {
			int newSize = getPrevPrimeNumber(B / 2);
			dynamicResize(newSize);
		}
	}

	/**
	 * Compute the current load factor of the hash table. It is calculated dividing
	 * the number of valid nodes in the table by B value (Max size)
	 * 
	 * @return load factor
	 */
	public double getLF() {
		return (double) validHashNodes / B;
	}

	/**
	 * Given an element and the attempt number return the f value for the element
	 * 
	 * @param element to be assigned an f value
	 * @param attempt number
	 * @return f value
	 */
	protected int f(T element, final int attempt) {
		int newHashCode = 0;

		if (redispersionType == LINEAR_PROBING)
			newHashCode = Math.abs(element.hashCode() + attempt) % B;
		else if (redispersionType == QUADRATIC_PROBING)
			newHashCode = Math.abs(element.hashCode() + (attempt * attempt)) % B;
		else if (redispersionType == DOUBLE_HASHING)
			newHashCode = Math.abs(element.hashCode() + (attempt * (R - element.hashCode() % R))) % B;

		return newHashCode;
	}

	/**
	 * Method to change the size of the hash table to a new one Will reassign
	 * variables B, R, validHashNodes and copy all elements in the previous hash
	 * tables to the new one resized
	 * 
	 * @param newSize to be assigned to the hash table
	 */
	private void dynamicResize(int newSize) {
		// Keep an instance of old table for passing elements to new table
		List<HashNode<T>> oldTable = associativeArray;
		// Assign all new variables for the table (associativeArray, B, recompute R and
		// current valid hash nodes)
		this.B = newSize;
		this.R = getPrevPrimeNumber(B);
		this.associativeArray = new ArrayList<HashNode<T>>();
		for (int i = 0; i < B; i++) {
			associativeArray.add(new HashNode<T>());
		}
		this.validHashNodes = 0;
		// Save the relevant elements from old table to new one
		for (int i = 0; i < oldTable.size(); i++) {
			if (oldTable.get(i).isValid()) {
				add(oldTable.get(i).getElement());
			}
		}
	}

	/* PRIME NUMBERS METHODS */

	/**
	 * Given an integer determines if it is prime number
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isPrime(int number) {
		if (number < 1)
			return false;

		if (number == 1)
			return true;

		for (int i = 2; i < number; i++)
			if (number % i == 0)
				return false;

		return true;
	}

	/**
	 * Given an integer returns its next primer number
	 * 
	 * @param number
	 * @return
	 */
	public static int getNextPrimeNumber(int number) {
		if (number < 1)
			return 1;

		if (number < 2)
			return 2;

		int prime = number;
		boolean found = false;

		while (!found) {
			prime++;
			if (isPrime(prime))
				found = true;
		}
		return prime;
	}

	/**
	 * Given an integer returns its previous primer number
	 * 
	 * @param number
	 * @return
	 */
	public static int getPrevPrimeNumber(int number) {
		if (number == 2)
			return 1;

		int prime = number;
		boolean found = false;

		while (!found) {
			prime--;
			if (isPrime(prime))
				found = true;
		}
		return prime;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < B; i++) {
			str += String.format("[%s] (%s) = %s - ", i, associativeArray.get(i).getStatus(),
					associativeArray.get(i).getElement());
		}
		return str;
	}
}
